package prolcy.wordle_maker_spring.service;

import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.dto.SolversResponseDTO;

import java.util.List;

public interface SolverService {
    boolean isDuplicatedNickname(SolverDTO solverDTO);
    String register(SolverDTO solverDTO);
    SolverDTO getSolverDTOByNickname(SolverDTO solverDTO);
    void updateWordListAndKeyState(SolverDTO solverDTO);
    List<SolversResponseDTO> getSolversByMaker(MakerDTO makerDTO);
}
