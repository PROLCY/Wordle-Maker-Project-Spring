package prolcy.wordle_maker_spring.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prolcy.wordle_maker_spring.domain.Maker;

@SpringBootTest
@Log4j2
public class MakerRepositoryTests {
    @Autowired
    private MakerRepository makerRepository;

    @Test
    public void testSelect() {
        String nickname = "AAAAA";
        log.info("-------testSelect()--------");
        Maker maker = makerRepository.findByNickname(nickname);
        log.info(maker);
    }
    @Test
    public void testInsert() {
        Maker maker = Maker.builder()
                .id(6L)
                .nickname("testNickname")
                .url("testUrl")
                .correctWord("testCorrectWord")
                .build();
        Maker result = makerRepository.save(maker);
        log.info(result);
    }
    @Test
    public void testDelete() {
        String nickname = "BBBBB";
        log.info("-------testDelect()---------");
        makerRepository.deleteByNickname(nickname);
        log.info(makerRepository.findByNickname(nickname));
    }
}
