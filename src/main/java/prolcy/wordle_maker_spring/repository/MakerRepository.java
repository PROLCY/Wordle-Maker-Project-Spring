package prolcy.wordle_maker_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prolcy.wordle_maker_spring.domain.Maker;

public interface MakerRepository extends JpaRepository<Maker, Long> {
    public Maker findByNickname(String nickname);
}
