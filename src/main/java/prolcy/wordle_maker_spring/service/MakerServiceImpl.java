package prolcy.wordle_maker_spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prolcy.wordle_maker_spring.repository.MakerRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class MakerServiceImpl implements MakerService{
    private final MakerRepository makerRepository;
    private final ModelMapper modelMapper;
}
