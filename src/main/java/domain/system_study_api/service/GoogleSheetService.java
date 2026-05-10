package domain.system_study_api.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import domain.system_study_api.entity.Classroom;
import domain.system_study_api.entity.QuizResult;
import domain.system_study_api.entity.Student;
import domain.system_study_api.repository.QuizSubmitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleSheetService {

    private final Sheets sheetsService;
    private final QuizSubmitRepository quizResultRepository;

//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    @Async
//    public void updateScoreOnSheetAsync(QuizResult result) {
//        try {
//            Student student = result.getStudent();
//            Classroom classroom = student.getClassroom();
//            String spreadsheetId = classroom.getSchool().getSpreadsheetId();
//
//            // Tìm dòng của học sinh dựa trên STT (externalId)
//            // Vì Sheet bắt đầu từ dòng 2 (A2), dòng của học sinh sẽ là: STT + 1
//            int rowIndex = student.getExternalId() + 3;
//
//            // OT 1: Điểm cột D (col 4),
//            // OT 2: Điểm cột E (col 5),
//            // OT 3: Điểm cột F (col 6),
//            String scoreCol = getColumnLetter(result.getPart().getSortOrder() + 3);
//
//            String range = String.format("%s!%s%d",
//                    classroom.getClassName(), scoreCol, rowIndex);
//
//            List<List<Object>> values = List.of(List.of(result.getScore()));
//
//            ValueRange body = new ValueRange().setValues(values);
//            sheetsService.spreadsheets().values()
//                    .update(spreadsheetId, range, body)
//                    .setValueInputOption("USER_ENTERED") // Để format đúng kiểu số
//                    .execute();
//
//            log.info("Ghi điểm thành công: Học sinh {}, Lớp {}, Cột {}, Dòng {}",
//                    student.getFirstName(), classroom.getClassName(), scoreCol, rowIndex);
//
//        } catch (Exception e) {
//            log.error("Lỗi khi ghi điểm lên Sheet: {}", e.getMessage());
//        }
//    }


    public void updateScoreOnSheetAsync(UUID resultId) {
        try {
            // Tìm lại kết quả trong Session mới của Thread Async
            // Sử dụng hàm truy vấn đã Fetch Join đầy đủ
            QuizResult result = quizResultRepository.findByIdWithDetails(resultId)
                    .orElseThrow(() -> new RuntimeException("Entity not found: " + resultId));

            Student student = result.getStudent();
            Classroom classroom = student.getClassroom();
            String spreadsheetId = classroom.getSchool().getSpreadsheetId();

            // Tính toán Row: STT 1 -> Dòng 4 (theo ảnh image_b74e38.png)
            int rowIndex = student.getExternalId() + 3;

            // Tính toán Column: OT 1 (sortOrder 1) -> Cột D (4)
            int colIndex = result.getPart().getSortOrder() + 3;
            String scoreCol = getColumnLetter(colIndex);

            String range = String.format("%s!%s%d", classroom.getName(), scoreCol, rowIndex);

            // Dùng USER_ENTERED để Google Sheet hiểu đây là số, không phải text
            ValueRange body = new ValueRange().setValues(List.of(List.of(result.getScore())));
            sheetsService.spreadsheets().values()
                    .update(spreadsheetId, range, body)
                    .setValueInputOption("USER_ENTERED")
                    .execute();

            log.info("Record score successfully: StudentName {}, Class {}, Column {}, Row {}",
                    student.getFirstName(), classroom.getName(), scoreCol, rowIndex);

        } catch (Exception e) {
            log.error("Execute fail at GoogleSheetService: ", e);
        }
    }


    /**
     * Chuyển đổi số thứ tự cột thành chữ cái (1->A, 2->B, 4->D...)
     */
    private String getColumnLetter(int columnNumber) {
        StringBuilder columnLetter = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--; // Giảm 1 để đưa về 0-based index
            columnLetter.append((char) ('A' + (columnNumber % 26)));
            columnNumber /= 26;
        }
        return columnLetter.reverse().toString(); // Đảo ngược chuỗi ở cuối
    }
}
