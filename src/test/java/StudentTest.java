import org.example.FakeGradeChecker;
import org.example.Student;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    @DisplayName("Добавление и получение списка оценок")
    public void testValidGradesAreAdded() {
        Student student = new Student("Ivan", new FakeGradeChecker());

        student.addGrade(3);
        student.addGrade(5);

        assertEquals(2, student.getGrades().size());
        assertEquals(List.of(3, 5), student.getGrades());
    }

    @Test
    @DisplayName("Невозможно добавление оценки ниже допустимой границы")
    public void testAddInvalidLowGrade() {
        Student student = new Student("David", new FakeGradeChecker());

        assertThrows("1 is wrong grade", IllegalArgumentException.class, () -> student.addGrade(1));
        assertTrue(student.getGrades().isEmpty());
    }

    @Test
    @DisplayName("Невозможно добавление оценки выше допустимой границы")
    public void testAddInvalidHighGrade() {
        Student student = new Student("Eva", new FakeGradeChecker());

        assertThrows("6 is wrong grade", IllegalArgumentException.class, () -> student.addGrade(6));
        assertTrue(student.getGrades().isEmpty());
    }
}