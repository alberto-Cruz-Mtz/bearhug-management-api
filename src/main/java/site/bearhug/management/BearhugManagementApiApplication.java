package site.bearhug.management;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BearhugManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BearhugManagementApiApplication.class, args);
    }
}
