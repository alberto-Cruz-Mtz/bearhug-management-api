package site.bearhug.management.configuration.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class SupabaseConfiguration {

    @Bean
    public S3Client s3Client(
            @Value("${supabase.secret.key}") String secretKey,
            @Value("${supabase.access.key}") String accessKey,
            @Value("${supabase.bucket.url}") String bucketUrl

    ) {
        AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .serviceConfiguration(c -> c.pathStyleAccessEnabled(true)) // importante en Supabase
                .endpointOverride(URI.create(bucketUrl)) // 👈 muy importante
                .region(Region.US_EAST_1)
                .build();
    }
}
