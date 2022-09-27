package peaksoft.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.requests.VideoRequest;
import peaksoft.dto.responses.TaskResponse;
import peaksoft.dto.responses.VideoResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.services.VideoService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/video")
public class VideoApi {

    private final VideoService videoService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public VideoResponse save(@RequestBody VideoRequest videoRequest) {
        return videoService.save(videoRequest);
    }

    @GetMapping("/findById")
    @PermitAll
    public VideoResponse findById(Long id) {
        return videoService.findById(id);
    }

    @GetMapping("/getAll")
    @PermitAll
    public List<VideoResponse> findAll() {
        return videoService.findAll();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public VideoResponse update(@PathVariable Long id, @RequestBody VideoRequest videoRequest) {
        return videoService.update(id, videoRequest);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return videoService.deleteById(id);
    }
}
