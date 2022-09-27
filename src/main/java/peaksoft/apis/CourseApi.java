package peaksoft.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.responses.CourseResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.services.CourseService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CourseResponse save(@RequestBody CourseRequest courseRequest) {
        return courseService.save(courseRequest);
    }

    @GetMapping("/findById/{courseId}")
    @PermitAll
    public CourseResponse findById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

    @GetMapping("/findAll")
    @PermitAll
    public List<CourseResponse> findAll() {
        return courseService.findAll();
    }

    @PutMapping("/update/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CourseResponse courseResponse(@PathVariable Long courseId, @RequestBody CourseRequest courseRequest) {
        return courseService.update(courseId, courseRequest);
    }

    @DeleteMapping("/delete/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long courseId) {
        return courseService.removeById(courseId);
    }
}
