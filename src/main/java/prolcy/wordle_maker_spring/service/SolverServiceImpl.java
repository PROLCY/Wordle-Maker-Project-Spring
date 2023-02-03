package prolcy.wordle_maker_spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prolcy.wordle_maker_spring.domain.Maker;
import prolcy.wordle_maker_spring.domain.Solver;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.dto.SolverDTO;
import prolcy.wordle_maker_spring.dto.SolversResponseDTO;
import prolcy.wordle_maker_spring.gson.Parser;
import prolcy.wordle_maker_spring.repository.SolverRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class SolverServiceImpl implements SolverService{
    private final SolverRepository solverRepository;
    private final ModelMapper modelMapper;
    private final Parser parser = new Parser();
    @Override
    public boolean isDuplicatedNickname(SolverDTO solverDTO) {
        Maker maker = Maker.builder()
                .nickname(solverDTO.getMakerNickname())
                .build();
        Solver solver = solverRepository.findByNicknameAndMaker(solverDTO.getNickname(), maker);
        return solver != null;
    }
    @Override
    public String register(SolverDTO solverDTO) {
        Solver solver = modelMapper.map(solverDTO, Solver.class);
        Solver result = solverRepository.save(solver);
        return result.getNickname();
    }
    @Override
    public SolverDTO getSolverDTOByNickname(SolverDTO solverDTO) {
        Maker maker = Maker.builder().nickname(solverDTO.getMakerNickname()).build();
        Solver solver = solverRepository.findByNicknameAndMaker(solverDTO.getNickname(), maker);
        return modelMapper.map(solver, SolverDTO.class);
    }
    @Override
    public void updateWordListAndKeyState(SolverDTO solverDTO) {
        Maker maker = Maker.builder()
                .nickname(solverDTO.getMakerNickname())
                .build();
        Solver solver = solverRepository.findByNicknameAndMaker(solverDTO.getNickname(), maker);
        if(solverDTO.getKeyState() != null) {
            solver.setKeyState(solverDTO.getKeyState());
        }
        solver.setWordList(solverDTO.getWordList());
        solverRepository.save(solver);
    }
    @Override
    public List<SolversResponseDTO> getSolversByMaker(MakerDTO makerDTO) {
        Maker maker = Maker.builder()
                .nickname(makerDTO.getNickname())
                .build();
        List<SolverDTO> solverDTOS = solverRepository.findSolversByMaker(maker).stream()
                .map(solver -> modelMapper.map(solver, SolverDTO.class))
                .collect(Collectors.toList());

        List<SolversResponseDTO> solversResponseDTOS = new ArrayList<>();
        solverDTOS.forEach(solverDTO -> {
            String[] nicknameLetters = solverDTO.getNickname().split("");
            String wordList = solverDTO.getWordList();

            List<String> nicknameLettersList = new ArrayList<>(Arrays.asList(nicknameLetters));

            List<Map<String, String>> nicknameLettersObjectList = nicknameLettersList.stream().map(letter -> {
                Map<String, String> map = new HashMap<>();
                map.put("text", letter);
                map.put("state", "filled");
                return map;
            }).collect(Collectors.toList());

            List<List<Map<String, String>>> parsedNickname = new ArrayList<>();
            parsedNickname.add(nicknameLettersObjectList);

            List<List<Map<String, String>>> parsedWordList = wordList == null ? parser.parseWordList("[]") :parser.parseWordList(wordList);

            SolversResponseDTO solversResponseDTO = SolversResponseDTO.builder()
                    .nickname(parsedNickname)
                    .wordList(parsedWordList)
                    .build();
            solversResponseDTOS.add(solversResponseDTO);
        });
        return solversResponseDTOS;
    }
}
