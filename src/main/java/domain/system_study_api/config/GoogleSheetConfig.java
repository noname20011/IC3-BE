package domain.system_study_api.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import domain.system_study_api.exception.NotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;

@Configuration
public class GoogleSheetConfig {
    @Bean
    public Sheets getSheetsService() throws Exception {
        // Đường dẫn mặc định của Render cho Secret Files
        String secretPath = "/etc/secrets/service-account-key.json";
        File file = new File(secretPath);
        InputStream inputStream;

        if (file.exists()) {
            // Nếu chạy trên Render, đọc từ đường dẫn tuyệt đối
            inputStream = new FileInputStream(file);
        } else {
            // Nếu chạy ở Local, đọc từ resources (classpath)
            inputStream = new ClassPathResource("service-account-key.json").getInputStream();
        }

        GoogleCredential credential = GoogleCredential
                .fromStream(inputStream)
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                credential)
                .setApplicationName("IC3-Quiz-System")
                .build();
    }
}
