package pl.degath.rest;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Part;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.views.View;
import jakarta.inject.Singleton;
import pl.degath.adapters.StudentSourceFromFileBytesService;
import pl.degath.studentssorter.SortStudentsByPerformanceCommandHandler;
import pl.degath.studentssorter.SortStudentsByPerformanceResult;
import pl.degath.studentssorter.Student;
import pl.degath.studentssorter.command.SortStudentsByPerformanceCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Singleton
@Controller
public class StudentPerformanceController {

    private final SortStudentsByPerformanceCommandHandler commandHandler;
    private List<Student> sortedStudents = new ArrayList<>();

    public StudentPerformanceController(SortStudentsByPerformanceCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @View("index")
    @Get
    HttpResponse<SortResponse> homeScreen() {
        return HttpResponse.ok(new SortResponse(List.of(), 0, 0));
    }

    @View("index")
    @Post
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public HttpResponse<SortResponse> sort(@Part("file") CompletedFileUpload file, @Part("algorithm") String algorithm) throws IOException {
        List<Student> students = new StudentSourceFromFileBytesService(file.getInputStream()).findAll();

        SortStudentsByPerformanceCommand command = new SortStudentsByPerformanceCommand(algorithm, students);
        SortStudentsByPerformanceResult result = commandHandler.handle(command);
        sortedStudents = result.students();

        return HttpResponse.ok(SortResponse.from(result));
    }

    @Get("/downloadSortedStudents")
    public MutableHttpResponse<String> downloadStudents() {
        return HttpResponse.ok(DownloadResponse.from(sortedStudents))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sortedStudents.txt")
                .contentType(MediaType.APPLICATION_OCTET_STREAM);
    }
}