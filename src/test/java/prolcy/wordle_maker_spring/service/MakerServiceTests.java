package prolcy.wordle_maker_spring.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prolcy.wordle_maker_spring.dto.MakerDTO;

@SpringBootTest
@Log4j2
public class MakerServiceTests {
    @Autowired
    private MakerService makerService;
    @Test
    public void getMakerDTOByNicknameTest() {
        MakerDTO makerDTO = MakerDTO.builder()
                .nickname("AAAAA")
                .build();
        log.info(makerService.getMakerDTOByNickname(makerDTO));
    }
    @Test
    public void registerTest() {
        MakerDTO makerDTO = MakerDTO.builder()
                .nickname("testNickname")
                .url("testUrl")
                .correctWord("testCorrectWord")
                .build();
        log.info(makerService.register(makerDTO));
    }
}
