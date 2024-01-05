package pl.degath.adapters;

import jakarta.inject.Singleton;
import pl.degath.studentssorter.Student;
import pl.degath.studentssorter.port.StudentSorter;

import java.util.ArrayList;
import java.util.List;

/**
 * generated via chat openai
 */
@Singleton
public class MergeStudentSorter implements StudentSorter {
    @Override
    public String algorithmName() {
        return "merge";
    }

    @Override
    public List<Student> sortByPerformance(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return List.of();
        }
        if (students.size() == 1) {
            return students;
        }
        List<Student> temp = new ArrayList<>(students);
        mergeSort(students, temp, 0, students.size() - 1);
        return students;
    }

    private static void mergeSort(List<Student> students, List<Student> temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(students, temp, left, mid);
            mergeSort(students, temp, mid + 1, right);
            merge(students, temp, left, mid, right);
        }
    }

    private static void merge(List<Student> students, List<Student> temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (students.get(i).performance() < students.get(j).performance()) {
                temp.set(k, students.get(i));
                i++;
            } else {
                temp.set(k, students.get(j));
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp.set(k, students.get(i));
            k++;
            i++;
        }
        while (j <= right) {
            temp.set(k, students.get(j));
            k++;
            j++;
        }
        for (int l = left; l <= right; l++) {
            students.set(l, temp.get(l));
        }
    }
}
