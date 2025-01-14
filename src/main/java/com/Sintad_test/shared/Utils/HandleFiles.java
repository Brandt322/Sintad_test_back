package com.Sintad_test.shared.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
@Component
public class HandleFiles {
    private static final Logger log = LoggerFactory.getLogger(HandleFiles.class);
    public String getImageType(byte[] imageBytes) {
        log.info("Determinando el tipo de imagen: " + imageBytes);
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            var bufferedImage = ImageIO.read(bais);
            if (bufferedImage != null) {
                return String.valueOf(bufferedImage.getType());
            } else {
                throw new RuntimeException("No se pudo determinar el tipo de imagen, la imagen puede estar corrupta o en un formato no soportado.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al determinar el tipo de imagen", e);
        }
    }

    public byte[] stringToByteArray(String file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String base64Image = file.split(",")[1];
        return Base64.getDecoder().decode(base64Image);
    }
}
