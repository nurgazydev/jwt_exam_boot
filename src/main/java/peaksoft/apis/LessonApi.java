package peaksoft.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.responses.LessonResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.services.LessonService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonApi {

    private final LessonService lessonService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public LessonResponse save(@RequestBody LessonRequest lessonRequest) {
        return lessonService.save(lessonRequest);
    }

    @GetMapping("/findById/{lessonId}")
    @PermitAll
    public LessonResponse findById(@PathVariable(name = "lessonId") Long id) {
        return lessonService.findById(id);
    }

    @GetMapping("/findAll")
    @PermitAll
    public List<LessonResponse> findAll() {
        return lessonService.getAll();
    }

    @PutMapping("/update/{lessonId}")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public LessonResponse update(@PathVariable Long lessonId, @RequestBody LessonRequest lessonRequest) {
        return lessonService.update(lessonId, lessonRequest);
    }

    @DeleteMapping("/delete/{lessonId}")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public SimpleResponse delete(@PathVariable Long lessonId) {
        return lessonService.delete(lessonId);
    }

}
