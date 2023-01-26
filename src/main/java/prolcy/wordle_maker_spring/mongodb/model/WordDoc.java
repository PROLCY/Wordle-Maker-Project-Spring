package prolcy.wordle_maker_spring.mongodb.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("words")
@Getter
@ToString
public class WordDoc {
    @Id
    private String _id;
    private String word;
}
