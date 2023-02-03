package prolcy.wordle_maker_spring.websocket;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import prolcy.wordle_maker_spring.dto.MakerDTO;
import prolcy.wordle_maker_spring.dto.SolversResponseDTO;
import prolcy.wordle_maker_spring.service.SolverService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class WebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> makers = new HashMap<>();
    private final SolverService solverService;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("-----------websocket connected------------");
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("-----------websocket disconnected---------");
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String makerNickname = message.getPayload();
        if(makerNickname.startsWith("#")) {
            makers.put(makerNickname.substring(1), session);
        } else {
            WebSocketSession maker = makers.get(makerNickname);
            if(maker == null)
                return;
            MakerDTO makerDTO = MakerDTO.builder()
                    .nickname(makerNickname)
                    .build();

            List<SolversResponseDTO> solvers = solverService.getSolversByMaker(makerDTO);

            Gson gson = new Gson();
            TextMessage textMessage = new TextMessage(gson.toJson(solvers));
            maker.sendMessage(textMessage);
        }
    }
}
