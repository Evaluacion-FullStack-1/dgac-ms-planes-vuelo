package cl.dgac.planesvuelo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI dgacPlanesVueloOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API DGAC - Microservicio de Planes de Vuelo")
                        .description("Documentación oficial de los endpoints para la gestión, evaluación y autorización de planes de vuelo del sistema DGAC.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo DGAC")
                                .email("soporte@dgac.cl")
                                .url("https://www.dgac.cl")));
    }
}

