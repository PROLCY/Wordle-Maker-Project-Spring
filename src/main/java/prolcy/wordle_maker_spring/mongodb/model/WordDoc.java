package prolcy.wordle_maker_spring.mongodb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Document("words")
@Getter
@Setter
@ToString
public class WordDoc {
    @Id
    private String _id;
    @Column(name = "word")
    private String word;
}
