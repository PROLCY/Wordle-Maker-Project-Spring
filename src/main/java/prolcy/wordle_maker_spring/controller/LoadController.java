package prolcy.wordle_maker_spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.service.MakerService;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
@Log4j2
public class LoadController {
    private final MakerService makerService;
    @PostMapping("/exist")
    public Boolean isExistNickname(@RequestBody MakerDTO makerDTO) {
        log.info(makerDTO);
        return makerService.isDuplicatedNickname(makerDTO);
    }
}
