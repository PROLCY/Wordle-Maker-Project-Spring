package prolcy.wordle_maker_spring.service;

import prolcy.wordle_maker_spring.dto.MakerDTO;

public interface MakerService {
    boolean isDuplicatedNickname(MakerDTO makerDTO);
    String register(MakerDTO makerDTO);
    String getCorrectWord(String nickname);
}
