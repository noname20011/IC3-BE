package domain.system_study_api.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Collections;

@Configuration
public class GoogleSheetConfig {
    @Bean
    public Sheets getSheetsService() throws Exception {
        GoogleCredential credential = GoogleCredential
                .fromStream(new ClassPathResource("service-account-key.json").getInputStream())
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                credential)
                .setApplicationName("IC3-Quiz-System")
                .build();
    }
}
