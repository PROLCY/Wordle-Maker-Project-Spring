package prolcy.wordle_maker_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prolcy.wordle_maker_spring.domain.Maker;
import prolcy.wordle_maker_spring.domain.Solver;

import java.util.List;

public interface SolverRepository extends JpaRepository<Solver, Long> {
    Solver findByNickname(String nickname);
    Solver findByNicknameAndMaker(String nickname, Maker maker);
    List<Solver> findSolversByMaker(Maker maker);
}
