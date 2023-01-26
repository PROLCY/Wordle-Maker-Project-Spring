package prolcy.wordle_maker_spring.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import prolcy.wordle_maker_spring.mongodb.model.WordDoc;

public interface WordRepository extends MongoRepository<WordDoc, String> {
    WordDoc findByWord(String word);
}
