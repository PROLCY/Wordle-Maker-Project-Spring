package prolcy.wordle_maker_spring.mongodb.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prolcy.wordle_maker_spring.mongodb.repository.WordRepository;

@SpringBootTest
@Log4j2
public class WordRepositoryTests {
    @Autowired
    private WordRepository wordRepository;
    @Test
    public void testFind() {
        log.info("---------testFind()------------");
        log.info(wordRepository.findByWord("AAAAA"));
    }
}
