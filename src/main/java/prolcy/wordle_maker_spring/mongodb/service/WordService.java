package prolcy.wordle_maker_spring.mongodb.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prolcy.wordle_maker_spring.mongodb.model.WordDoc;
import prolcy.wordle_maker_spring.mongodb.repository.WordRepository;
@Service
@AllArgsConstructor
public class WordService {
    private final WordRepository wordRepository;
    public boolean isExistWord(String word) {
        WordDoc wordDoc = wordRepository.findByWord(word);
        if(wordDoc == null)
            return false;
        return true;
    }
}