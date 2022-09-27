package peaksoft.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.responses.TaskResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.services.TaskService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskApi {

    private final TaskService taskService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public TaskResponse save(@RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }

    @GetMapping("/findById")
    @PermitAll
    public TaskResponse findById(Long id) {
        return taskService.findById(id);
    }

    @GetMapping("/getAll")
    @PermitAll
    public List<TaskResponse> findAll() {
        return taskService.getAll();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,INSTRUCTOR')")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return taskService.remove(id);
    }

}
