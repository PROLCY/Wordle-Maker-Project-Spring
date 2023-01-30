package prolcy.wordle_maker_spring.service;

import prolcy.wordle_maker_spring.dto.SolverDTO;

public interface SolverService {
    boolean isDuplicatedNickname(SolverDTO solverDTO);
    String register(SolverDTO solverDTO);
    SolverDTO getSolverDTOByNickname(SolverDTO solverDTO);
    void updateKeyState(SolverDTO solverDTO);
    void updateWordList(SolverDTO solverDTO);
}
