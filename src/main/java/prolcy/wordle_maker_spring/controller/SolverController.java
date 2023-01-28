package prolcy.wordle_maker_spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.mongodb.model.WordDoc;
import prolcy.wordle_maker_spring.mongodb.service.WordService;
import prolcy.wordle_maker_spring.service.MakerService;
import prolcy.wordle_maker_spring.service.SolverService;

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
    public Map<String, String> register(@RequestBody SolverDTO solverDTO, @PathVariable String makerNickname) {
        String correctWord = makerService.getCorrectWord(makerNickname);
        solverDTO.setMakerNickname(makerNickname);
        String nickname = solverService.register(solverDTO);
        log.info("------SOLVER " + nickname + " is registered----");
        //세션 등록
        //웹소켓 등록
        Map<String, String> response = new HashMap<>();
        response.put("wordCorrect", correctWord);
        return response;
    }
}
