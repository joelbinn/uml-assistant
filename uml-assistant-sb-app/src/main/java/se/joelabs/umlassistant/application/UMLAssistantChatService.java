package se.joelabs.umlassistant.application;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UMLAssistantChatService {
  private final UMLAssistant umlAssistant = UMLAssistantChat.create();

  public UMLResponse chat(String userMessage) {
    log.debug("User message: {}", userMessage);
    val response = umlAssistant.chat(userMessage);
    val diagramSpec = umlAssistant.extractPlantUML(response.content());
    val chatAnswer = umlAssistant.extractChatAnswer(response.content());
    log.debug("Response: {}; tokens: {}; sources: {}", response.content(), response.tokenUsage(), response.sources());
    return UMLResponse.createFor(chatAnswer, diagramSpec);
  }
}
