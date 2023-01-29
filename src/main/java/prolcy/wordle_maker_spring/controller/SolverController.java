package prolcy.wordle_maker_spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.mongodb.model.WordDoc;
import prolcy.wordle_maker_spring.mongodb.service.WordService;
import prolcy.wordle_maker_spring.service.MakerService;
import prolcy.wordle_maker_spring.service.SolverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
        if(session.getAttribute("solver") == null) {
            log.info("------no session -solver- --------");
            response.put("session", "null");
            return response;
        }
        //로직 추가
        return null;
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
        String correctWord = makerService.getCorrectWord(makerNickname);
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
}
