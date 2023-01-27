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
public class MakerDTO {
    private Long id;
    private String nickname;
    private String url;
    private String correctWord;
    private LocalDateTime created_at;
}
