package prolcy.wordle_maker_spring.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import prolcy.wordle_maker_spring.domain.Maker;
import prolcy.wordle_maker_spring.domain.Solver;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class SolverRepositoryTests {
    @Autowired
    private MakerRepository makerRepository;
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
    public void testSelectByNicknameAndMakerNickname() {
        String nickname = "QQQQQ";
        String makerNickname = "ZZZZZ";
        Maker maker = Maker.builder().nickname(makerNickname).build();
        Solver solver = solverRepository.findByNicknameAndMaker(nickname, maker);
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
    @Test
    public void isDuplicatedNicknameTest() {
        Maker maker = Maker.builder().nickname("AAAAA").build();
        Solver solver = solverRepository.findByNicknameAndMaker("AAAAA", maker);
        log.info(solver);
    }
    @Test
    public void testInsert() {
        Maker maker = makerRepository.findByNickname("AAAAA");
        Solver solver = Solver.builder()
                .nickname("testNickname")
                .maker(maker)
                .build();
        Solver result = solverRepository.save(solver);
        log.info(result);
    }
    @Test
    public void testSelectSolvers() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Solver solver = Solver.builder()
                    .nickname("TESTA")
                    .maker(makerRepository.findByNickname("MMMMM"))
                    .build();
            solverRepository.save(solver);
        });
        Maker maker = Maker.builder()
                .nickname("MMMMM")
                .build();
        solverRepository.findSolversByMaker(maker).forEach(log::info);
    }
}
