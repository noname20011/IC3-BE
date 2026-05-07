package domain.system_study_api.listener;

import java.util.UUID;

public record QuizSubmittedEvent(UUID resultId) {}
