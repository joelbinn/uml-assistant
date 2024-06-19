package se.joelabs.umlassistant.application;

public record UMLAssistantChatResponse(
  String chatAnswer,
  String diagramSpec,
  String id) {
}
