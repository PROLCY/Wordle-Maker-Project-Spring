package prolcy.wordle_maker_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolverDTO {
    private Long id;
    private String makerNickname;
    private String nickname;
    private String wordList;
    private String keyState;
    private LocalDateTime created_at;
}
