package se.joelabs.umlassistant.application;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Tools {
  private static final Logger LOG = LoggerFactory.getLogger(Tools.class);
  private final File diagramDir;

  enum PlantUMLType {
    CLASS, SEQUENCE, USECASE, ACTIVITY, COMPONENT
  }

  Map<String, String> diagrams = new HashMap<>();

  public Tools() {
    diagramDir = new File(".umlassistant");
    diagramDir.mkdirs();
    Arrays.stream(diagramDir.listFiles()).forEach(diagramFile -> {
      try(var lineNumberRead = new LineNumberReader(new FileReader(diagramFile))) {
        var content = lineNumberRead.lines().collect(Collectors.joining("\n"));
        diagrams.put(diagramFile.getName(), content);
      } catch (IOException e) {
        LOG.error("Failed to read diagram file: {}",diagramFile.getName(), e);
      }
    });
  }

  @Tool("Sparar UML-diagrammet i PlantUML-format.")
  public void writePlantUMLDiagram(
    @P("ID på diagrammet") String id,
    @P("Typ av diagram") PlantUMLType type,
    @P("Den textuella specifikationen i PlantUML-syntax för diagrammet") String plantUmlText
  ) {
    System.out.println("Sparar UML-diagrammet av typen %s med ID: %s PlantUML-format: %s".formatted(id, type, plantUmlText));
    this.diagrams.put(id, plantUmlText);
    new File(diagramDir, id).renameTo(new File(diagramDir, id + ".bak"));
    try (var printStream = new PrintStream( new FileOutputStream(new File(diagramDir, id)))) {
      printStream.println(plantUmlText);
      new File(diagramDir, id + ".bak").delete();
    } catch (IOException e) {
      LOG.error("Failed to write diagram file: {}", id, e);
    }
  }

  @Tool("Läser upp UML-diagrammet med angivet ID i PlantUML-format.")
  public String readPlantUMLDiagram(@P("ID på diagrammet") String id) {
    return diagrams.getOrDefault(id, "");
  }

  @Tool("Läser upp ID:s för alla UML-diagram som finns sparade.")
  public Collection<String> listSavedPlantUMLDiagrams() {
    return diagrams.keySet();
  }
}

