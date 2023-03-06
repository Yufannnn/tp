package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ExamTest {
    @Test
    public void constructor_validInputs_success() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 31, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 31, 14, 59);
        Grade grade = new Grade(80, 100);
        Exam exam = new Exam("Midterm", startTime, endTime, 0.4, Exam.ExamStatus.Finished, grade);
        assertEquals("Midterm", exam.getDescription());
        assertEquals(80, exam.getGrade().getScore());
        assertEquals(100, exam.getGrade().getFullMarks());
        assertEquals(80.0, exam.getGrade().getPercentageScore());
    }

    @Test
    public void constructor_startTimeAfterEndTime_throwsIllegalArgumentException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 31, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 31, 12, 59);
        assertThrows(IllegalArgumentException.class, () -> new Exam("Midterm", startTime, endTime, 0.4,
                Exam.ExamStatus.Finished, null));
    }

    @Test
    public void constructor_invalidWeightage_throwsIllegalArgumentException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 31, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 31, 14, 59);
        assertThrows(IllegalArgumentException.class, () -> new Exam("Midterm", startTime, endTime, 1.4,
                Exam.ExamStatus.Finished, null));
    }

    @Test
    public void constructor_invalidWeightage2_throwsIllegalArgumentException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 31, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 31, 14, 59);
        assertThrows(IllegalArgumentException.class, () ->
                new Exam("Midterm", startTime, endTime, -0.4, Exam.ExamStatus.Finished, null));
    }

    @Test
    public void constructor_upcomingExamWithGrade_throwsIllegalArgumentException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 31, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 31, 14, 59);
        Grade grade = new Grade(80, 100);
        assertThrows(IllegalArgumentException.class, () ->
                new Exam("Midterm", startTime, endTime, 0.4, Exam.ExamStatus.Upcoming, grade));
    }

    @Test
    public void constructor_absentExamWithGrade_throwsIllegalArgumentException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 31, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 31, 14, 59);
        Grade grade = new Grade(80, 100);
        assertThrows(IllegalArgumentException.class, () ->
                new Exam("Midterm", startTime, endTime, 0.4, Exam.ExamStatus.Absent, grade));
    }

    @Test
    public void constructor_finishedExamWithoutGrade_throwsIllegalArgumentException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 31, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 31, 14, 59);
        assertThrows(IllegalArgumentException.class, () ->
                new Exam("Midterm", startTime, endTime, 0.4, Exam.ExamStatus.Finished, null));
    }

    @Test
    public void constructor_finishedExamWithGrade_success() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 31, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 31, 14, 59);
        Grade grade = new Grade(80, 100);
        Exam exam = new Exam("Midterm", startTime, endTime, 0.4, Exam.ExamStatus.Finished, grade);
        assertNotNull(exam.getGrade());
    }
}