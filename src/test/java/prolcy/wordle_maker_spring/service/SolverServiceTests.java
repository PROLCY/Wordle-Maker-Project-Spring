package prolcy.wordle_maker_spring.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.dto.SolversResponseDTO;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


@SpringBootTest
@Log4j2
public class SolverServiceTests {
    @Autowired
    private SolverService solverService;
    @Test
    public void isDuplicatedNicknameTest() {
        SolverDTO solverDTO = SolverDTO.builder()
                .nickname("AAAAA")
                .makerNickname("AAAAA")
                .build();
        log.info(solverService.isDuplicatedNickname(solverDTO));
    }
    @Test
    public void registerTest() {
        SolverDTO solverDTO = SolverDTO.builder()
                .nickname("testNickname")
                .makerNickname("AAAAA")
                .build();
        String nickname = solverService.register(solverDTO);
        log.info(nickname);
    }
    @Test
    public void gsonTest() {
        SolverDTO solverDTO = solverService.getSolverDTOByNickname(SolverDTO.builder().nickname("FFFFF").build());

        log.info(solverDTO.getWordList());
        Gson gson = new Gson();
        Type listType = new TypeToken<List<List<Map<String, String>>>>() {}.getType();
        List<List<Map<String, String>>> list = gson.fromJson(solverDTO.getWordList(), listType);
        log.info(list);
        log.info(list.get(0));
        log.info(list.get(0).get(0));
        log.info(list.get(0).get(0).get("state"));

        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> map = gson.fromJson(solverDTO.getKeyState(), mapType);
        log.info(map);
    }
    @Test
    public void testUpdate() {
        SolverDTO solverDTO = SolverDTO.builder()
                .nickname("FFFFF")
                .makerNickname("AAAAA")
                .keyState("testKeyState4")
                .wordList("testWordList")
                .build();
        solverService.register(solverDTO);
        solverService.updateWordListAndKeyState(solverDTO);
    }
    @Test
    public void testGetSolvers() {
        MakerDTO makerDTO = MakerDTO.builder()
                .nickname("MMMMM")
                .build();
        List<SolversResponseDTO> solverDTOS = solverService.getSolversByMaker(makerDTO);
        solverDTOS.forEach(log::info);
    }
}
