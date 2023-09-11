import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void throwExction_whenFindById_getsUnknownStudentID() {
        // Given
        StudentService studentService = new StudentService();
        Student student1 = studentService.addNewStudent(new Student(null, "Student1", "Math"));
        Student student2 = studentService.addNewStudent(new Student(null, "Student2", "History"));
        String newId = UUID.randomUUID().toString();
        while( newId.equals(student1.id()) || newId.equals(student2.id()) )
            newId = UUID.randomUUID().toString();
        String unknownId = newId;

        // When
        Executable action = () -> studentService.findById(unknownId);

        // Then
        assertThrows(StudentService.StudentNotFoundException.class, action);
    }

    @Test
    void returnStudent_whenFindById_getsKnownStudentID() {
        // Given
        StudentService studentService = new StudentService();
        Student student1 = studentService.addNewStudent(new Student(null, "Student1", "Math"));
        String id = student1.id();//  newId;

        // When
        Executable action = () -> studentService.findById(id);
        Student actual = null;
        try {
            actual = studentService.findById(id);
        } catch (StudentService.StudentNotFoundException ignored) {
        }

        // Then
        assertDoesNotThrow(action);
        assertEquals(student1, actual);
    }
}