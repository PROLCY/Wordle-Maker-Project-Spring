package prolcy.wordle_maker_spring.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "solvers")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "maker")
public class Solver extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker")
    private Maker maker;
    @Column(length = 20, nullable = false)
    private String nickname;
    @Column(name = "word_list")
    private String wordList;
    @Column(name = "key_state")
    private String keyState;
    public void setKeyState(String keyState) {
        this.keyState = keyState;
    }
    public void setWordList(String wordList) {
        this.wordList = wordList;
    }
}
