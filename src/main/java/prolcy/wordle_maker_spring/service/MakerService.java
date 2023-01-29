package prolcy.wordle_maker_spring.service;

import prolcy.wordle_maker_spring.dto.MakerDTO;

import java.util.Map;

public interface MakerService {
    Map<String, String> getUrlAndCorrectWord(MakerDTO makerDTO);
    boolean isRegisteredNickname(String nickname);
    String register(MakerDTO makerDTO);
    String getCorrectWord(String nickname);
}
