package prolcy.wordle_maker_spring.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import prolcy.wordle_maker_spring.domain.Maker;
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
    @Test
    @Transactional
    public void testSelectWithMaker() {
        String nickname = "AAAAA";
        log.info("--------testSelectWithMaker()---------");
        Maker maker = solverRepository.findByNickname(nickname).getMaker();
        log.info(maker);
    }
}
