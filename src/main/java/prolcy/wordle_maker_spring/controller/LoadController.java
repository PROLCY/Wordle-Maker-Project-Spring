package prolcy.wordle_maker_spring.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.dto.SolversResponseDTO;
import prolcy.wordle_maker_spring.service.MakerService;
import prolcy.wordle_maker_spring.service.SolverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
@Log4j2
public class LoadController {
    private final MakerService makerService;
    private final SolverService solverService;
    private final Map<String, Boolean> isClicked = new HashMap<>();
    @GetMapping("/init")
    public Map<String, String> initializeGet(HttpServletRequest request) {
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
    @PostMapping("/init")
    public Map<String, String> initializePost(HttpSession session, @RequestBody Map<String, String> body) {
        Map<String, String> response = new HashMap<>();
        String makerNickname = body.get("makerNickname");
        session.setAttribute("maker", makerNickname);

        MakerDTO makerDTO = MakerDTO.builder()
                .nickname(makerNickname)
                .build();

        List<SolversResponseDTO> solvers = solverService.getSolversByMaker(makerDTO);

        Gson gson = new Gson();

        response.put("solvers", gson.toJson(solvers));
        log.info(response.get("solvers"));
        return response;
    }
    @PostMapping("/exist")
    public Boolean isExistNickname(@RequestBody MakerDTO makerDTO) {
        log.info(makerDTO);
        return makerService.isRegisteredNickname(makerDTO.getNickname());
    }
    @DeleteMapping("/delete/{makerNickname}")
    public String deleteMaker(HttpSession session, @PathVariable String makerNickname) {
        if(isClicked.get(makerNickname) == null || !isClicked.get(makerNickname)) {
            isClicked.put(makerNickname, true);
            Timer timer = new Timer(3000, e -> isClicked.put(makerNickname, false));
            timer.setRepeats(false);
            timer.start();
            return "oneClick";
        } else {
            session.setAttribute("maker", null);
            makerService.deleteMaker(MakerDTO.builder().nickname(makerNickname).build());
            return "doubleClick";
        }
    }
}
