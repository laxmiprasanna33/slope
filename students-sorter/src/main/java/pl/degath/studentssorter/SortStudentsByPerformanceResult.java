package pl.degath.studentssorter;

import java.util.List;

public record SortStudentsByPerformanceResult(List<Student> students, int studentsCount, long elapsedTime) {
}