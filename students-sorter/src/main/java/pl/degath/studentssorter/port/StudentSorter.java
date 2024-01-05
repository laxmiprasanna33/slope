package pl.degath.studentssorter.port;

import pl.degath.studentssorter.Student;

import java.util.List;

public interface StudentSorter {

    String algorithmName();

    List<Student> sortByPerformance(List<Student> students);
}
