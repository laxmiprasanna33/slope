package pl.degath.studentssorter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.degath.adapters.StudentSourceFromFileBytesService;
import pl.degath.studentssorter.port.StudentSourceApi;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class StudentSourceFromFileBytesServiceTest {

    private StudentSourceApi studentSourceApi;

    @BeforeEach
    void setUp() {
        studentSourceApi = new StudentSourceFromFileBytesService(new ByteArrayInputStream("Charlie,50.0\nLucy,80.0\nLinus,60\n".getBytes()));
    }

    @Test
    void shouldGetStudentsFromFile() {
        List<Student> result = studentSourceApi.findAll();

        assertThat(result).hasSize(3);
    }
}