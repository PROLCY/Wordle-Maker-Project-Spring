package prolcy.wordle_maker_spring.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.mongodb.model.WordDoc;
import prolcy.wordle_maker_spring.mongodb.service.WordService;
import prolcy.wordle_maker_spring.service.MakerService;
import prolcy.wordle_maker_spring.service.SolverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/solve")
@RequiredArgsConstructor
@Log4j2
public class SolverController {
    public final MakerService makerService;
    public final SolverService solverService;
    public final WordService wordService;
    @GetMapping("/{makerNickname}/init")
    public Map<String, String> initialize(HttpServletRequest request, @PathVariable String makerNickname) {
        Map<String, String> response = new HashMap<>();
        if(!makerService.isRegisteredNickname(makerNickname)) {
            log.info("------Maker Not Found---------");
            response.put("nickname", "NotFound");
            return response;
        }
        HttpSession session = request.getSession();
        String nickname = (String) session.getAttribute("solver");

        if(nickname == null) {
            log.info("------no session -solver- --------");
            response.put("session", "null");
            return response;
        }
        //로직 추가
        MakerDTO makerDTO = makerService.getMakerDTOByNickname(MakerDTO.builder()
                .nickname(makerNickname)
                .build());

        SolverDTO solverDtoOnlyNickname = SolverDTO.builder()
                .nickname(nickname)
                .makerNickname(makerNickname)
                .build();
        SolverDTO solverDTO = solverService.getSolverDTOByNickname(solverDtoOnlyNickname);

        int listIndex;
        List<Map<String, String>> lastWord;

        List<List<Map<String, String>>> wordList = parseWordList(solverDTO.getWordList());
        Map<String, String> keyState = parseKeyState(solverDTO.getKeyState());

        if(wordList == null) {
            wordList = new ArrayList<>();
            wordList.add(new ArrayList<>());
        }

        if(keyState == null)
            keyState = new HashMap<>();

        if(wordList.get(wordList.size() - 1).size() == 0) {
            listIndex = wordList.size() - 1;
            lastWord = new ArrayList<>();
        } else if(wordList.get(wordList.size() - 1).get(0).get("state").equals("filled")) {
            listIndex = wordList.size() - 1;
            lastWord = wordList.get(wordList.size() - 1);
        } else {
            listIndex = wordList.size();
            lastWord = new ArrayList<>();
        }

        Gson gson = new Gson();

        response.put("wordCorrect", makerDTO.getCorrectWord());
        response.put("wordList", gson.toJson(wordList));
        response.put("keyState", gson.toJson(keyState));
        response.put("lastWord", gson.toJson(lastWord));
        response.put("nickname", nickname);
        response.put("listIndex", gson.toJson(listIndex));
        return response;
    }
    private List<List<Map<String, String>>> parseWordList(String json) {
        if(json == null)
            return null;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<List<Map<String, String>>>>() {}.getType();
        return gson.fromJson(json, listType);
    }
    private Map<String, String> parseKeyState(String json) {
        if(json == null)
            return null;
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        return gson.fromJson(json, mapType);
    }
    private List<Map<String, String>> parseWord(String json) {
        if(json == null)
            return null;
        Gson gson = new Gson();
        Type mapType = new TypeToken<List<Map<String, String>>>() {}.getType();
        return gson.fromJson(json, mapType);
    }
    @PostMapping("/{makerNickname}/duplicated")
    public String duplicatedNickname(@RequestBody SolverDTO solverDTO, @PathVariable String makerNickname) {
        solverDTO.setMakerNickname(makerNickname);
        log.info(solverDTO);
        return solverService.isDuplicatedNickname(solverDTO) ? "duplicated" : "not-duplicated";
    }
    @PostMapping("/exist")
    public Map<String, Boolean> isExistWord(@RequestBody WordDoc wordDoc) {
        log.info(wordDoc);
        Map<String, Boolean> response = new HashMap<>();
        if(wordService.isExistWord(wordDoc.getWord()))
            response.put("exist", true);
        else
            response.put("exist", false);
        return response;
    }
    @PostMapping("/{makerNickname}/register")
    public Map<String, String> register(HttpSession session, @RequestBody SolverDTO solverDTO, @PathVariable String makerNickname) {
        MakerDTO makerDTO = MakerDTO.builder()
                .nickname(makerNickname)
                .build();
        String correctWord = makerService.getMakerDTOByNickname(makerDTO).getCorrectWord();
        solverDTO.setMakerNickname(makerNickname);
        String nickname = solverService.register(solverDTO);
        log.info("------SOLVER " + nickname + " is registered----");
        //세션 등록
        session.setAttribute("solver", nickname);
        //웹소켓 등록
        Map<String, String> response = new HashMap<>();
        response.put("wordCorrect", correctWord);
        return response;
    }
    @PostMapping("/{makerNickname}/enter")
    public ResponseEntity<Object> enter(HttpServletRequest request, @RequestBody Map<String, String> body, @PathVariable String makerNickname) {
        String solverNickname = (String) request.getSession().getAttribute("solver");
        String keyState = body.get("keyState");
        SolverDTO solverDTO = SolverDTO.builder()
                .nickname(solverNickname)
                .makerNickname(makerNickname)
                .keyState(keyState)
                .build();
        log.info("controller-----" + keyState);
        solverService.updateKeyState(solverDTO);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/{makerNickname}/typing")
    public ResponseEntity<Object> typing(HttpServletRequest request, @RequestBody Map<String, String> body, @PathVariable String makerNickname) {
        String solverNickname = (String) request.getSession().getAttribute("solver");
        SolverDTO solverDTO = SolverDTO.builder()
                .nickname(solverNickname)
                .makerNickname(makerNickname)
                .build();
        solverDTO = solverService.getSolverDTOByNickname(solverDTO);

        String newWord = body.get("newWord");
        int listIndex = Integer.parseInt(body.get("listIndex"));

        List<List<Map<String, String>>> wordList = parseWordList(solverDTO.getWordList());
        List<Map<String, String>> word = parseWord(newWord);
        if(wordList == null) {
            wordList = new ArrayList<>();
        }

        if(wordList.size() == listIndex)
            wordList.add(word);
        else if(wordList.size() > listIndex)
            wordList.set(listIndex, word);
        Gson gson = new Gson();

        String newWordList = gson.toJson(wordList);
        solverDTO.setWordList(newWordList);

        log.info("--------solverDTO--------");
        log.info(solverDTO);
        solverService.updateWordList(solverDTO);

        //웹소켓 추가

        return ResponseEntity.ok(null);
    }
}
