package pl.degath.adapters;

import pl.degath.studentssorter.Student;
import pl.degath.studentssorter.port.StudentSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FakeStudentSorterService implements StudentSorter {
    @Override
    public String algorithmName() {
        return "fake";
    }

    @Override
    public List<Student> sortByPerformance(List<Student> studentsToSort) {
        var students = new ArrayList<>(studentsToSort);
        students.sort(Comparator.comparing(Student::performance));
        return students;
    }
}
