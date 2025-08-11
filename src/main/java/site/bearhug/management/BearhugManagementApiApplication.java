package site.bearhug.management;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import site.bearhug.management.util.jwt.JwtUtil;

@SpringBootApplication
@RequiredArgsConstructor
public class BearhugManagementApiApplication {
    private final JwtUtil jwtUtil;

    public static void main(String[] args) {
        SpringApplication.run(BearhugManagementApiApplication.class, args);
    }
}
