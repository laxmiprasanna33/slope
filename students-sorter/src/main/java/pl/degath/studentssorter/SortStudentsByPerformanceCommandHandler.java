package pl.degath.studentssorter;


import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.degath.infrastructure.CommandHandler;
import pl.degath.studentssorter.command.SortStudentsByPerformanceCommand;
import pl.degath.studentssorter.port.StudentSorter;

import java.util.List;

@Singleton
public class SortStudentsByPerformanceCommandHandler implements CommandHandler<SortStudentsByPerformanceCommand, SortStudentsByPerformanceResult> {

    private final Logger LOG = LoggerFactory.getLogger(SortStudentsByPerformanceCommandHandler.class);
    private final List<StudentSorter> studentSorters;

    public SortStudentsByPerformanceCommandHandler(List<StudentSorter> studentSorters) {
        this.studentSorters = studentSorters;
    }

    @Override
    public SortStudentsByPerformanceResult handle(SortStudentsByPerformanceCommand command) {
        LOG.info("Sorting students by performance by [algorithm: {}]", command.algorithmName());
        return studentSorters.stream()
                .filter(studentSorter -> command.algorithmName().equals(studentSorter.algorithmName()))
                .map(studentSorter -> toSortStudentsByPerformanceResult(command, studentSorter))
                .findFirst()
                .orElseThrow();
    }

    private SortStudentsByPerformanceResult toSortStudentsByPerformanceResult(SortStudentsByPerformanceCommand command, StudentSorter studentSorter) {
        long startTime = System.nanoTime();
        List<Student> students = studentSorter.sortByPerformance(command.students());
        long endTime = System.nanoTime();
        long elapsedTimeInNs = endTime - startTime;

        return new SortStudentsByPerformanceResult(students, students.size(), elapsedTimeInNs);
    }
}

