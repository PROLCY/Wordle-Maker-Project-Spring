package prolcy.wordle_maker_spring.mongodb.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class WordServiceTests {
    @Autowired
    private WordService wordService;
    @Test
    public void testFindExistWord() {
        log.info("-------testFindExistWord()----------");
        log.info(wordService.isExistWord("CLOTH"));
    }
    @Test
    public void testFindNotExistWord() {
        log.info("-------testFindNotExistWord()----------");
        log.info(wordService.isExistWord("AAAAA"));
    }
}