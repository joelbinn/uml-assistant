package se.joelabs.umlassistant.application;

import dev.langchain4j.service.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UMLAssistantChatService {
  private final UMLAssistant umlAssistant = UMLAssistantChat.create();
  private static final Pattern ID_PATTERN = Pattern.compile("##ID:(.*?)##");

  private static String extractId(String text) {
    Matcher matcher = ID_PATTERN.matcher(text);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }
  public UMLResponse chat(String userMessage) {
    log.debug("User message: {}", userMessage);
    Result<UMLAssistantChatResponse> response = umlAssistant.chat(userMessage);
    log.debug("Response: {}; tokens: {}; sources: {}", response.content(), response.tokenUsage(), response.sources());
    UMLAssistantChatResponse content = response.content();
    return UMLResponse.createFor(response.content().chatAnswer(), response.content().id(), response.content().diagramSpec());
  }
}
