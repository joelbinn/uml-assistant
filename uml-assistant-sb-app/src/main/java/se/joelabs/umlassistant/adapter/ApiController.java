package se.joelabs.umlassistant.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.joelabs.umlassistant.application.UMLAssistantChatService;
import se.joelabs.umlassistant.application.UMLResponse;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiController {
  private final UMLAssistantChatService umlAssistantChatService;

  @PostMapping("chat")
  public UMLResponse chat(@RequestBody String userMessage) {
    return umlAssistantChatService.chat(userMessage);
  }
}
