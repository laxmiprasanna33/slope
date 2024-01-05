package pl.degath.studentssorter.port;

import pl.degath.studentssorter.Student;

import java.util.List;

public interface StudentSourceApi {

    List<Student> findAll();
}
