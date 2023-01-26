package prolcy.wordle_maker_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prolcy.wordle_maker_spring.domain.Solver;

public interface SolverRepository extends JpaRepository<Solver, Long> {
    Solver findByNickname(String nickname);
}
