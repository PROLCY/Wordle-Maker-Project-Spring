package prolcy.wordle_maker_spring.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prolcy.wordle_maker_spring.domain.Solver;

@SpringBootTest
@Log4j2
public class SolverRepositoryTests {
    @Autowired
    private SolverRepository solverRepository;
    @Test
    public void testSelect() {
        String nickname = "AAAAA";
        log.info("-------testSelect()-------");
        Solver solver = solverRepository.findByNickname(nickname);
        log.info(solver);
    }
}
