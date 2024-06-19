package se.joelabs.umlassistant.application;

import dev.langchain4j.service.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UMLAssistantApplication {
  private static final Logger logger = LoggerFactory.getLogger(UMLAssistantApplication.class);

  public static void main(String[] args) {
    logger.info("Starting PlantUML Assistant Chat");
    doConversationWith(UMLAssistantChat.create());
  }

  private static void doConversationWith(UMLAssistant UMLAssistant) {
    Scanner scanner = new Scanner(System.in);
    String command;
    System.out.println("Hej, dax att rita PlantUML-diagram! Skriv /q för att avsluta.");
    while (true) {
      System.out.print("> ");
      command = scanner.nextLine();
      if ("/q".equals(command)) {
        System.out.println("Tack för samtalet\nHej då :-)!");
        break;
      }
      Result<UMLAssistantChatResponse> answer = UMLAssistant.chat(command);
      UMLAssistantChatResponse resp = answer.content();
      System.out.println("Svar: %s".formatted(answer.content()));
    }
    scanner.close();
  }
}
