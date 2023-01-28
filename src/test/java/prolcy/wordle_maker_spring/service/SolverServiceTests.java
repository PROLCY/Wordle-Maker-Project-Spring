package prolcy.wordle_maker_spring.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prolcy.wordle_maker_spring.dto.SolverDTO;

@SpringBootTest
@Log4j2
public class SolverServiceTests {
    @Autowired
    private SolverService solverService;
    @Test
    public void isDuplicatedNicknameTest() {
        SolverDTO solverDTO = SolverDTO.builder()
                .nickname("AAAAA")
                .makerNickname("AAAAA")
                .build();
        log.info(solverService.isDuplicatedNickname(solverDTO));
    }
    @Test
    public void registerTest() {
        SolverDTO solverDTO = SolverDTO.builder()
                .nickname("testNickname")
                .makerNickname("AAAAA")
                .build();
        String nickname = solverService.register(solverDTO);
        log.info(nickname);
    }
}
