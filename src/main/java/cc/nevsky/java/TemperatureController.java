package cc.nevsky.java;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Контроллер для чтения температуры с датчика ds18b20.
 *
 * @author Aleksandr Nevsky
 */
@Path("/temperature")
public class TemperatureController {
    @ConfigProperty(name = "temperature.file.address")
    String tempFile;

    /**
     * Получаем температуру из файла датчика.
     *
     * @return отформатированная температура.
     */
    @Path("/ds18b20")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTemp() throws IOException {

        String temperature = Files.readString(Paths.get(tempFile));
        return String.format("%s.%s", temperature.substring(0, 2), temperature.charAt(2));
    }
}
