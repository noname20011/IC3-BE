package domain.system_study_api.service.classroom;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import domain.system_study_api.dto.classroom.ClassroomResponseDTO;
import domain.system_study_api.entity.Classroom;
import domain.system_study_api.entity.School;
import domain.system_study_api.mapper.ClassroomMapper;
import domain.system_study_api.repository.ClassroomRepository;
import domain.system_study_api.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final Sheets sheetsService;
    private final SchoolRepository schoolRepository;
    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;

    @Transactional
    @Override
    public void scanAndUpdateClassrooms(UUID schoolId) throws IOException {
        School school = schoolRepository.findByIdOrThrow(schoolId);

        // 1. Lấy danh sách tên Tab hiện tại từ Google Sheet (Danh sách A)
        Spreadsheet spreadsheet = sheetsService.spreadsheets()
                .get(school.getSpreadsheetId())
                .execute();

        List<String> currentTabsOnSheet = spreadsheet.getSheets().stream()
                .map(s -> s.getProperties().getTitle().trim().toUpperCase())
                .filter(name -> name.matches("^[0-9]+[a-zA-Z].*")) // Lọc định dạng lớp
                .toList();

        // 2. Lấy danh sách lớp hiện có trong DB (Danh sách B)
        List<Classroom> existingClassesInDb = classroomRepository.findBySchoolId(schoolId);
        List<String> existingClassNamesInDb = existingClassesInDb.stream()
                .map(Classroom::getClassName)
                .toList();

        // --- XỬ LÝ XÓA (Delete if not in Sheet) ---
        List<Classroom> classesToDelete = existingClassesInDb.stream()
                .filter(c -> !currentTabsOnSheet.contains(c.getClassName()))
                .toList();

        if (!classesToDelete.isEmpty()) {
            classroomRepository.deleteAll(classesToDelete);
            log.info("Đã xóa {} lớp không còn tồn tại trên Sheet", classesToDelete.size());
        }

        // --- XỬ LÝ THÊM MỚI (Insert if not in DB) ---
        for (String tabName : currentTabsOnSheet) {
            if (!existingClassNamesInDb.contains(tabName)) {
                Classroom newClass = new Classroom();
                newClass.setClassName(tabName);
                newClass.setSchool(school);
                classroomRepository.save(newClass);
                log.info("Đã thêm lớp mới: {}", tabName);
            }
        }
    }

    @Override
    public List<ClassroomResponseDTO> getClassrooms(UUID schoolId) {
        List<Classroom> data = classroomRepository.findBySchoolIdOrderByClassNameAsc(schoolId);

        return classroomMapper.mapToListResponseDtos(data);
    }

}
