package se.joelabs.umlassistant.application;

import net.sourceforge.plantuml.code.TranscoderSmart;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PlantUmlEncoder {
  String encode(String uml) {
    try {
      return new TranscoderSmart().encode(uml);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
