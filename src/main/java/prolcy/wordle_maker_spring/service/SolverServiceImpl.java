package prolcy.wordle_maker_spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prolcy.wordle_maker_spring.domain.Maker;
import prolcy.wordle_maker_spring.domain.Solver;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.repository.SolverRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class SolverServiceImpl implements SolverService{
    private final SolverRepository solverRepository;
    private final ModelMapper modelMapper;
    @Override
    public boolean isDuplicatedNickname(SolverDTO solverDTO) {
        Maker maker = Maker.builder()
                .nickname(solverDTO.getMakerNickname())
                .build();
        Solver solver = solverRepository.findByNicknameAndMaker(solverDTO.getNickname(), maker);
        return solver != null;
    }
    @Override
    public String register(SolverDTO solverDTO) {
        Solver solver = modelMapper.map(solverDTO, Solver.class);
        Solver result = solverRepository.save(solver);
        return result.getNickname();
    }
}
