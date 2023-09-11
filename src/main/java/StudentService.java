import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentService {

    private final StudentRepo repo = new StudentRepo();

    public Student addNewStudent(Student newStudent) {
        Student studentToSave = newStudent.withId(UUID.randomUUID().toString());
        return repo.save(studentToSave);
    }

    public List<Student> getAllStudents(){
        return repo.getAllStudents();
    }

    public Student findById( String id ) throws StudentNotFoundException {
        return repo.findStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException("Can't find a student with id \"%s\"".formatted(id)));
    }

    public static class StudentNotFoundException extends Exception {
        public StudentNotFoundException(String message) {
            super(message);
        }
    }
}
