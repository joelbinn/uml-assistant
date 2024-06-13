package se.joelabs.umlassistant.application;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlantUmlEncoderTest {
  @Test
  void testEncode() {
    // GIVEN
    val diagram = """
      @startuml
      class Kaka
      @enduml
      """;
    val encoder = new PlantUmlEncoder();
    // WHEN
    val encoded = encoder.encode(diagram);
    // THEN
    assertThat(encoded).isEqualTo("Iyv9B2vMy4xCJWG0");
  }
}
