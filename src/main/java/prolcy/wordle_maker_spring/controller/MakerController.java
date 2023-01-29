package prolcy.wordle_maker_spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.mongodb.model.WordDoc;
import prolcy.wordle_maker_spring.mongodb.service.WordService;
import prolcy.wordle_maker_spring.service.MakerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/make")
@RequiredArgsConstructor
@Log4j2
public class MakerController {
    private final WordService wordService;
    private final MakerService makerService;
    @GetMapping("/init")
    public Map<String, String> initialize(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, String> response = new HashMap<>();
        String makerNickname = (String) session.getAttribute("maker");
        if(makerNickname == null) {
            log.info("------no session -maker- --------");
            response.put("session", "null");
        } else {
            MakerDTO makerDTO = MakerDTO.builder()
                    .nickname(makerNickname)
                    .build();
            response = makerService.getUrlAndCorrectWord(makerDTO);
        }
        return response;
    }
    @PostMapping("/duplicated")
    public String duplicatedNickname(@RequestBody MakerDTO makerDTO) {
        log.info(makerDTO);
        return makerService.isRegisteredNickname(makerDTO.getNickname()) ? "duplicated" : "not-duplicated";
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
    @PostMapping("/register")
    public String register(@RequestBody MakerDTO makerDTO, HttpSession session) {
        String url = "testUrl"; // url 생성 추가 필요
        makerDTO.setUrl(url);
        String nickname = makerService.register(makerDTO);

        //세션 등록 추가
        session.setAttribute("maker", nickname);

        log.info("------MAKER " + nickname + " is registered----");
        return url;
    }
}
