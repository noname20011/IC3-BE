package domain.system_study_api.listener;

import domain.system_study_api.service.GoogleSheetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoogleSheetListener {

    private final GoogleSheetService googleSheetService;

    // phase = AFTER_COMMIT: Chỉ chạy khi DB đã lưu xong 100%
    // @Async: Chạy ở một Thread khác để không làm học sinh phải chờ
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleQuizSubmitted(QuizSubmittedEvent event) {
        log.info("Transaction committed. Start record score to Sheet has ID: {}", event.resultId());
        googleSheetService.updateScoreOnSheetAsync(event.resultId());
    }
}
