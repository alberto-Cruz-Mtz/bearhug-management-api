package site.bearhug.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import site.bearhug.management.service.implementation.InventoryProductServiceImpl;

@SpringBootApplication
public class BearhugManagementApiApplication implements CommandLineRunner {

    private final InventoryProductServiceImpl service;

    public BearhugManagementApiApplication(InventoryProductServiceImpl service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(BearhugManagementApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
