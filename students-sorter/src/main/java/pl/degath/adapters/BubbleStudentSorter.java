package pl.degath.adapters;

import jakarta.inject.Singleton;
import pl.degath.studentssorter.Student;
import pl.degath.studentssorter.port.StudentSorter;

import java.util.List;

/**
 * generated via chat openai
 */
@Singleton
public class BubbleStudentSorter implements StudentSorter {
    @Override
    public String algorithmName() {
        return "bubble";
    }

    @Override
    public List<Student> sortByPerformance(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return List.of();
        }
        if (students.size() == 1) {
            return students;
        }

        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).performance() > students.get(j + 1).performance()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        return students;
    }
}
