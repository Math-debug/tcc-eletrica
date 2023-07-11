package br.unip.tcc.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import br.unip.tcc.converver.AnomalyConverter;
import br.unip.tcc.entity.Anomaly;

@Controller
public class WebSocketController {
	@Autowired
	private SimpMessageSendingOperations sendingOperations;
	
	@MessageMapping("/new")
	public void newAnomaly(Anomaly anomaly) {
		sendingOperations.convertAndSend("/topic/new", AnomalyConverter.convertTo(anomaly));
	}
	@MessageMapping("/update")
	public void updateAnomaly(Anomaly anomaly) {
		sendingOperations.convertAndSend("/topic/update", AnomalyConverter.convertTo(anomaly));
	}
}
