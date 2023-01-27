package prolcy.wordle_maker_spring.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prolcy.wordle_maker_spring.repository.MakerRepository;

@SpringBootTest
@Log4j2
public class MakerServiceTests {
    @Autowired
    private MakerRepository makerRepository;
    @Autowired
    private ModelMapper modelMapper;

}
