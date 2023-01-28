package prolcy.wordle_maker_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import prolcy.wordle_maker_spring.domain.Maker;

public interface MakerRepository extends JpaRepository<Maker, Long> {
    Maker findByNickname(String nickname);
    @Transactional
    void deleteByNickname(String nickname);
}
