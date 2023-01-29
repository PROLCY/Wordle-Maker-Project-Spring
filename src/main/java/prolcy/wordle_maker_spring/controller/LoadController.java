package prolcy.wordle_maker_spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.service.MakerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
@Log4j2
public class LoadController {
    private final MakerService makerService;
    @GetMapping("/init")
    public Map<String, String> initialize(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, String> response = new HashMap<>();
        String makerNickname = (String) session.getAttribute("maker");
        if(makerNickname == null) {
            log.info("------no session -load- --------");
            response.put("session", "null");
        } else {
            response.put("maker", makerNickname);
        }
        return response;
    }
    @PostMapping("/exist")
    public Boolean isExistNickname(@RequestBody MakerDTO makerDTO) {
        log.info(makerDTO);
        return makerService.isRegisteredNickname(makerDTO.getNickname());
    }
}
