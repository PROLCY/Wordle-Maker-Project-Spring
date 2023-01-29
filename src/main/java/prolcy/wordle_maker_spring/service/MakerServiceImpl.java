package prolcy.wordle_maker_spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prolcy.wordle_maker_spring.domain.Maker;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.repository.MakerRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class MakerServiceImpl implements MakerService{
    private final MakerRepository makerRepository;
    private final ModelMapper modelMapper;
    @Override
    public Map<String, String> getUrlAndCorrectWord(MakerDTO makerDTO) {
        Map<String, String> result = new HashMap<>();
        Maker maker = makerRepository.findByNickname(makerDTO.getNickname());
        result.put("url", maker.getUrl());
        result.put("correct_word", maker.getCorrectWord());
        return result;
    }
    @Override
    public boolean isRegisteredNickname(String nickname) {
        Maker maker = makerRepository.findByNickname(nickname);
        return maker != null;
    }
    @Override
    public String register(MakerDTO makerDTO) {
        Maker maker = modelMapper.map(makerDTO, Maker.class);
        Maker result = makerRepository.save(maker);
        return result.getNickname();
    }
    @Override
    public String getCorrectWord(String nickname) {
        return makerRepository.findByNickname(nickname).getCorrectWord();
    }
}
