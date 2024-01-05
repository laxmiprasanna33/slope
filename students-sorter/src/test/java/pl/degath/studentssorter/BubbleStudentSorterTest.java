package pl.degath.studentssorter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.degath.adapters.BubbleStudentSorter;
import pl.degath.studentssorter.port.StudentSorter;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BubbleStudentSorterTest {

    StudentSorter studentSorter;

    @BeforeEach
    void setUp() {
        studentSorter = new BubbleStudentSorter();
    }

    @Test
    public void shouldSortUnorderedStudentsByPerformance() {
        Student charlie = new Student("Charlie", 50.0);
        Student lucy = new Student("Lucy", 80.0);
        Student linus = new Student("Linus", 60.0);
        List<Student> students = new ArrayList<>(List.of(charlie, lucy, linus));

        List<Student> sortedStudents = studentSorter.sortByPerformance(students);

        assertThat(sortedStudents).containsExactly(charlie, linus, lucy);
    }

    @Test
    public void shouldReturnTheSameResultIfStudentsWereAlreadySorted() {
        Student charlie = new Student("Charlie", 50.0);
        Student linus = new Student("Linus", 60.0);
        Student lucy = new Student("Lucy", 80.0);
        List<Student> students = new ArrayList<>(List.of(charlie, lucy, linus));

        List<Student> sortedStudents = studentSorter.sortByPerformance(students);

        assertThat(sortedStudents).containsExactly(charlie, linus, lucy);
    }

    @Test
    public void shouldJustReturnListIfItsSingleStudent() {
        Student charlie = new Student("Charlie", 50.0);
        List<Student> students = new ArrayList<>(List.of(charlie));

        List<Student> sortedStudents = studentSorter.sortByPerformance(students);

        assertThat(sortedStudents).containsExactly(charlie);
    }

    @Test
    public void shouldWorksProperlyForDuplicatedStudentPerformances() {
        Student charlie = new Student("Charlie", 50.0);
        Student lucy = new Student("Lucy", 80.0);
        Student linus = new Student("Linus", 60.0);
        Student johny = new Student("Johny", 60.0);
        List<Student> students = new ArrayList<>(List.of(charlie, lucy, linus, johny));

        List<Student> sortedStudents = studentSorter.sortByPerformance(students);

        assertThat(sortedStudents).first().isEqualTo(charlie);
        assertThat(sortedStudents).last().isEqualTo(lucy);
    }

    @Test
    public void shouldNotThrowExceptionIfStudentsAreEmpty() {
        List<Student> students = new ArrayList<>(List.of());

        List<Student> sortedStudents = studentSorter.sortByPerformance(students);

        assertThat(sortedStudents).isEmpty();
    }

    @Test
    public void shouldNotThrowExceptionIfStudentsAreMissing() {
        List<Student> students = null;

        List<Student> sortedStudents = studentSorter.sortByPerformance(students);

        assertThat(sortedStudents).isEmpty();
    }
}