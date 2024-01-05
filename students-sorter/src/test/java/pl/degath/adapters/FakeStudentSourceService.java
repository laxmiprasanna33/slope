package pl.degath.adapters;

import pl.degath.studentssorter.Student;
import pl.degath.studentssorter.port.StudentSourceApi;

import java.util.ArrayList;
import java.util.List;

public class FakeStudentSourceService implements StudentSourceApi {

    @Override
    public List<Student> findAll() {
        Student charlie = new Student("Charlie", 50.0);
        Student lucy = new Student("Lucy", 80.0);
        Student linus = new Student("Linus", 60.0);
        return new ArrayList<>(List.of(charlie, lucy, linus));
    }
}
