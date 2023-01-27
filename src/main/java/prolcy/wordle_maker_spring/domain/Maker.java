package prolcy.wordle_maker_spring.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "makers")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Maker extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Id
    @Column(length = 20, nullable = false)
    private String nickname;
    @Column(length = 200, nullable = false)
    private String url;
    @Column(name = "correct_word", length = 20, nullable = false)
    private String correctWord;
}
