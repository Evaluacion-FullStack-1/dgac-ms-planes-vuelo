package cl.dgac.planesvuelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DgacMsPlanesVueloApplication {

    public static void main(String[] args) {
        SpringApplication.run(DgacMsPlanesVueloApplication.class, args);
    }

}