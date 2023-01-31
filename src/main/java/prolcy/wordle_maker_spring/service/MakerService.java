package prolcy.wordle_maker_spring.service;

import prolcy.wordle_maker_spring.dto.MakerDTO;

public interface MakerService {
    MakerDTO getMakerDTOByNickname(MakerDTO makerDTO);
    boolean isRegisteredNickname(String nickname);
    String register(MakerDTO makerDTO);
    void deleteMaker(MakerDTO makerDTO);
}
