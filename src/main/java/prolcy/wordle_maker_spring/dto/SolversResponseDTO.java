package prolcy.wordle_maker_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolversResponseDTO {
    private List<List<Map<String, String>>> nickname;
    private List<List<Map<String, String>>> wordList;
}
