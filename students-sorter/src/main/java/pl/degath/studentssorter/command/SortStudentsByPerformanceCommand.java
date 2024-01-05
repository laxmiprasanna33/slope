package pl.degath.studentssorter.command;

import pl.degath.infrastructure.Command;
import pl.degath.studentssorter.Student;

import java.util.List;

public record SortStudentsByPerformanceCommand(String algorithmName, List<Student> students) implements Command {
}
