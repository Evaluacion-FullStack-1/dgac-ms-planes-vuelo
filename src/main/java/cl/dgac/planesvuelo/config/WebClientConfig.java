package cl.dgac.planesvuelo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // Lee la URL desde el application.properties para la conexión interna
    @Value("${restricciones-aereas.base-url:http://dgac-ms-restricciones-aereas}")
    private String restriccionesAereasBaseUrl;

    @Bean
    @LoadBalanced // Fundamental para que Eureka intercepte y resuelva la IP dinámica
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClientRestriccionesAereas(WebClient.Builder builder) {
        // Construye el cliente apuntando al microservicio de Restricciones Aéreas
        return builder.baseUrl(restriccionesAereasBaseUrl).build();
    }
}