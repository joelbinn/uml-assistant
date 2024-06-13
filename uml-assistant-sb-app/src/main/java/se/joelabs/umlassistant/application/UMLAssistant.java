package se.joelabs.umlassistant.application;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

interface UMLAssistant {

  @SystemMessage("""
    Du är en expert på UML och PlantUML och kan hjälpa till med att skapa UML-diagram i textform.
    Du kan konversera på svenska med humor.
    Du kan skapa och ändra klassdiagram, sekvensdiagram, use case-diagram, aktivitetsdiagram och komponentdiagram i textuellt format med PlantUML-syntax.
    Om man försöker skapa ett diagram utan att ange ID, fråga vad man vill att det ska ha för ID. Men, om man redan har öppnat ett diagram skall man använda det ID:t.
    Spara alltid diagrammet efter varje ändring.
    Visa alltid diagrammet varje gång du svarar.
    """)
  Result<String> chat(String userMessage);

  @UserMessage("Extract the PlantUML diagram spec of {{it}}")
  String extractPlantUML(String text);

  @UserMessage("Extract the chat answer (excluding the PlantUML diagram spec) of {{it}}")
  String extractChatAnswer(String content);
}
