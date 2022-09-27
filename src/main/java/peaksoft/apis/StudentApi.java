package peaksoft.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.StudentResponse;
import peaksoft.dto.responses.StudentResponseView;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.services.StudentService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService studentService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @GetMapping("/findById/{studentId}")
    @PermitAll
    public StudentResponse findById(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @GetMapping("/findAll")
    @PermitAll
    public List<StudentResponse> findAll() {
        return studentService.getAll();
    }

    @PutMapping("/update/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse update(@PathVariable Long studentId,
                                  @RequestBody StudentRequest studentRequest) {
        return studentService.update(studentId, studentRequest);
    }

    @DeleteMapping("/delete/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteBuId(@PathVariable Long studentId) {
        return studentService.remove(studentId);
    }

    @GetMapping("/getAllPagination")
    @PermitAll
    public StudentResponseView getAllPagination(@RequestParam(name = "text", required = false) String text,
                                                @RequestParam int page,
                                                @RequestParam int size) {

        return studentService.getAllStudentsPagination(text,page,size);
    }
}
