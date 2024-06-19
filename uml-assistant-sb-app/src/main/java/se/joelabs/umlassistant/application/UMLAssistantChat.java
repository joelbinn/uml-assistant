package se.joelabs.umlassistant.application;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UMLAssistantChat {
  private static final Logger logger = LoggerFactory.getLogger(UMLAssistantChat.class);

  static UMLAssistant create() {
    logger.info("Creating PlantUML Assistant");
//    List<Document> documents = FileSystemDocumentLoader.loadDocuments("./rules");
//    InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
//    EmbeddingStoreIngestor.ingest(documents, embeddingStore);

    String apiKey = System.getenv("OPENAI_API_KEY");
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(apiKey)
      .modelName("gpt-4o")
      .responseFormat("json_object")
      .logRequests(true)
      .logResponses(true)
      .build();

    UMLAssistant umllAssistant = AiServices.builder(UMLAssistant.class)
      .chatLanguageModel(model)
      .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
//      .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
      .tools(new Tools())
      .build();

    return umllAssistant;
  }
}
