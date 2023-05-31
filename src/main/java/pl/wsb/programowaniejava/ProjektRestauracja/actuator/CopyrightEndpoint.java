package pl.wsb.programowaniejava.ProjektRestauracja.actuator;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import java.time.Year;
import java.util.Map;

@Component
@Endpoint("copyright")
public class CopyrightEndpoint {

    @ReadOperation
    public Map<String, String> copyright() {

        return Map.of("author", "Magdalena Niewiadoma", "year", Year.now().toString());
    }
}
