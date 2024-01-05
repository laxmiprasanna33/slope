package pl.degath.adapters;

import jakarta.inject.Singleton;
import pl.degath.studentssorter.Student;
import pl.degath.studentssorter.port.StudentSourceApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class StudentSourceFromFileBytesService implements StudentSourceApi {

    private final InputStream inputStream;

    public StudentSourceFromFileBytesService(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                students.add(new Student(row[0], Double.valueOf(row[1])));
            }
            return students;
        } catch (IOException ioException) {
            throw new RuntimeException();
        }
    }
}