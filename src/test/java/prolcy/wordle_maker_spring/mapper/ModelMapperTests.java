package prolcy.wordle_maker_spring.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import prolcy.wordle_maker_spring.domain.Maker;
import prolcy.wordle_maker_spring.domain.Solver;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.repository.MakerRepository;
import prolcy.wordle_maker_spring.repository.SolverRepository;

@SpringBootTest
@Log4j2
public class ModelMapperTests {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MakerRepository makerRepository;
    @Autowired
    private SolverRepository solverRepository;
    @Test
    @Transactional
    public void MakerMappingTest() {
        MakerDTO makerDTO = MakerDTO.builder()
                .id(1L)
                .nickname("testNickname")
                .url("testUrl")
                .correctWord("testCorrectWord")
                .build();
        Maker maker = modelMapper.map(makerDTO, Maker.class);
        log.info("--------DTOToEntity------");
        log.info(makerDTO);
        log.info(maker);
        maker = makerRepository.findByNickname("AAAAA");
        log.info("-------EntityToDTO-------");
        makerDTO = modelMapper.map(maker, MakerDTO.class);
        log.info(maker);
        log.info(makerDTO);
    }
    @Test
    @Transactional
    public void SolverMappingTest() {
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
        solver = solverRepository.findByNickname("BBBBA");
        log.info("-------EntityToDTO-------");
        solverDTO = modelMapper.map(solver, SolverDTO.class);
        log.info(solver);
        log.info(solver.getMaker());
        log.info(solverDTO);
    }
}
