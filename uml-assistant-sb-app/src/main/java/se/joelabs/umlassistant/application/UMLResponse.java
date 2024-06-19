package se.joelabs.umlassistant.application;

import dev.langchain4j.service.Result;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.net.URI;

@Slf4j
public record UMLResponse(String answer, String id, URI plantUmlDiagramUri) {
  public static UMLResponse createFor(String chatAnswer, String id, String diagramSpec) {
    log.debug("Creating UMLResponse for response: {}, {}, {}", chatAnswer, id, diagramSpec);
    URI diagramUri = null;
    if (diagramSpec != null) {
      val encoder = new PlantUmlEncoder();
      val encodedDiagram = encoder.encode(diagramSpec);
      diagramUri = java.net.URI.create("https://www.plantuml.com/plantuml/svg/%s".formatted(encodedDiagram));
    }
    return new UMLResponse(chatAnswer, id, diagramUri);
  }
}
