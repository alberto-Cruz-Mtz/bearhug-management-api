package site.bearhug.management.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SupabaseStorage {

    @Value("${supabase.bucket.name}")
    public String bucketName;
    private final S3Client client;

    public String uploadFile(MultipartFile file, String uid) throws IOException {
        String key = "pictures/" + uid + "-" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        client.putObject(request, RequestBody.fromBytes(file.getBytes()));

        // URL pública si el bucket es público
        return "https://llorhplkztuqwqheaspi.supabase.co/storage/v1/object/public/" + bucketName + "/" + key;
    }
}
