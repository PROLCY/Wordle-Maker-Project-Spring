package prolcy.wordle_maker_spring.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import prolcy.wordle_maker_spring.domain.Solver;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.repository.SolverRepository;

@SpringBootTest
@Log4j2
public class SolverServiceTests {
    @Autowired
    private SolverRepository solverRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Test
    @Transactional
    public void MappingTest() {
        SolverDTO solverDTO = SolverDTO.builder()
                .id(1L)
                .makerNickname("testMakerNickname")
                .nickname("testNickname")
                .wordList("testWordList")
                .keyState("testKeyState")
                .build();
        Solver solver = modelMapper.map(solverDTO, Solver.class);
        log.info("--------DTOToEntity------");
        log.info(solverDTO);
        log.info(solver);
        log.info(solver.getMaker());
        solver = solverRepository.findByNickname("AAAAA");
        log.info("-------EntityToDTO-------");
        solverDTO = modelMapper.map(solver, SolverDTO.class);
        log.info(solver);
        log.info(solver.getMaker());
        log.info(solverDTO);
    }
}
