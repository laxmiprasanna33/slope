package pl.degath.studentssorter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.degath.adapters.FakeStudentSorterService;
import pl.degath.studentssorter.command.SortStudentsByPerformanceCommand;
import pl.degath.studentssorter.port.StudentSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SortStudentsByPerformanceCommandHandlerTest {

    private SortStudentsByPerformanceCommandHandler sortStudentsByPerformanceCommandHandler;

    @BeforeEach
    void setUp() {
        StudentSorter fakeStudentSorterService = new FakeStudentSorterService();
        sortStudentsByPerformanceCommandHandler = new SortStudentsByPerformanceCommandHandler(List.of(fakeStudentSorterService));
    }

    @Test
    public void shouldSortUnorderedStudentsByPerformance() {
        Student charlie = new Student("Charlie", 50.0);
        Student lucy = new Student("Lucy", 80.0);
        Student linus = new Student("Linus", 60.0);
        SortStudentsByPerformanceResult sortedStudents = sortStudentsByPerformanceCommandHandler.handle(
                new SortStudentsByPerformanceCommand(
                        "fake",
                        new ArrayList<>(List.of(charlie, lucy, linus))));

        assertThat(sortedStudents.students()).isSortedAccordingTo(Comparator.comparing(Student::performance));
    }
}