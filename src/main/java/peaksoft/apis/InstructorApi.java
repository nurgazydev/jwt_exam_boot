package peaksoft.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.InstructorAssignRequest;
import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.services.InstructorService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorApi {

    private final InstructorService instructorService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public InstructorResponse save(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.save(instructorRequest);
    }

    @GetMapping("/findById/{instructorId}")
    @PermitAll
    public InstructorResponse findById(@PathVariable("instructorId") Long id) {
        return instructorService.findById(id);
    }

    @GetMapping("/findAll")
    @PermitAll
    public List<InstructorResponse> findAll() {
        return instructorService.getAll();
    }

    @PutMapping("/update/{instructorId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public InstructorResponse update(@PathVariable Long instructorId,
                                     @RequestBody InstructorRequest instructorRequest) {
        return instructorService.update(instructorId, instructorRequest);
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return instructorService.removeById(id);
    }

    @PostMapping("/assign")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse assign(@RequestBody InstructorAssignRequest assignRequest) {
        return instructorService.assignInstructorToCourse(assignRequest);
    }

}
