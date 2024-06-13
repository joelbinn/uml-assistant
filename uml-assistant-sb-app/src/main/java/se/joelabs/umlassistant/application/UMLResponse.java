package se.joelabs.umlassistant.application;

import dev.langchain4j.service.Result;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.net.URI;

@Slf4j
public record UMLResponse(String answer, URI plantUmlDiagramUri) {
  public static UMLResponse createFor(String chatAnswer, String diagram) {
    log.debug("Creating UMLResponse for response: {}; diagram: {}", chatAnswer, diagram);
    val encoder = new PlantUmlEncoder();
    val encodedDiagram = encoder.encode(diagram);
    return new UMLResponse(chatAnswer, URI.create("https://www.plantuml.com/plantuml/svg/%s".formatted(encodedDiagram)));
  }
}
