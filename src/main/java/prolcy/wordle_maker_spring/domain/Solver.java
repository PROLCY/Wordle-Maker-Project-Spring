package prolcy.wordle_maker_spring.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "solvers")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Solver extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String nickname;
    @Column(name = "word_list")
    private String wordList;
    @Column(name = "key_state")
    private String keyState;
}
