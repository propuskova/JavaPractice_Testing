import org.example.Student;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StudentTest {

    @Test
    @DisplayName("Добавление и получение списка оценок")
    public void testAddAndGetGradesTest() {
        Student student = new Student("Anastasia");
        student.addGrade(4);
        student.addGrade(5);

        List<Integer> grades = student.getGrades();
        assertEquals(2, grades.size());
        assertEquals(List.of(4, 5), grades);
    }

//    @Test
//    @DisplayName("Невозможность изменения списка оценок (инкапсулирован)")
//    public void testGradesEncapsulation() {
//        Student student = new Student("Ivan");
//        student.addGrade(2);
//        List<Integer> grades = student.getGrades();

//        assertThrows("Should not allow external modification",
//                UnsupportedOperationException.class, () -> grades.add(5));
//    }

    @Test
    @DisplayName("Невозможно изменение оригинала списка оценок при изменении копии (инкапсуляция)")
    public void testGradesEncapsulationWithCopyTest() {
        Student student = new Student("Ivan");
        student.addGrade(2);
        List<Integer> grades = student.getGrades();

        grades.add(5); // Изменение копии писка оценок
        //оригинал списка не будет изменен
        assertEquals("Original list must not change",1, student.getGrades().size());
    }

    @Test
    @DisplayName("Невозможно добавление оценки ниже допустимой границы")
    public void testAddGradeInvalidLowTest() {
        Student student = new Student("David");
        Exception e = assertThrows(IllegalArgumentException.class, () -> student.addGrade(1));
        assertEquals("1 is wrong grade", e.getMessage());
    }

    @Test
    @DisplayName("Невозможно добавление оценки выше допустимой границы")
    public void testAddGradeInvalidHighTest() {
        Student student = new Student("Eva");
        Exception e = assertThrows(IllegalArgumentException.class, () -> student.addGrade(6));
        assertEquals("6 is wrong grade", e.getMessage());
    }

    @Test
    @DisplayName("Сравнение ссылок и хэш-кодов")
    public void testEqualsAndHashCodeTest() {
        Student s1 = new Student("Tom");
        Student s2 = new Student("Tom");
        s1.addGrade(5);
        s2.addGrade(5);

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    @DisplayName("Проверка вывода строки в ожидаемом формате")
    public void testToStringTest() {
        Student student = new Student("Alex");
        student.addGrade(3);
        String expected = "Student{name=Alex, marks=[3]}";
        assertEquals(expected, student.toString());
    }
}
