package peaksoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.mappers.TaskMapper;
import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.responses.TaskResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Lesson;
import peaksoft.models.Task;
import peaksoft.repositories.LessonRepository;
import peaksoft.repositories.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    private final TaskMapper taskMapper;




    public List<TaskResponse> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.convertAllTasksToView(tasks);
    }

    public TaskResponse findById(Long id) {
        Task task = getById(id);
        return taskMapper.convertToView(task);
    }

    private Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("task with id: " + id + " not found!"));
    }


//    public List<Task> findTasksByLessonId(Long lessonId) {
//        return taskRepository.findTasksByLessonId(lessonId);
//    }


    public TaskResponse save(TaskRequest taskRequest) {
        Task task = taskMapper.convertToEntity(taskRequest);
        Lesson lesson = lessonRepository.findById(taskRequest.getLessonId())
                .orElseThrow(() -> new NotFoundException("lesson with id: " + taskRequest.getLessonId() + " does not exists"));
        lesson.setTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
        return taskMapper.convertToView(task);
    }


    public SimpleResponse remove(Long id) {
        boolean exists = taskRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("task with id: " + id + " does not exists!");
        }
        taskRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "task with id: " + id + " does not exists"
        );
    }

    @Transactional
    public TaskResponse update(Long id, TaskRequest taskRequest) {
        Task task = getById(id);
        Task update = taskMapper.update(task, taskRequest);
        taskRepository.save(update);
        return taskMapper.convertToView(update);
    }
}
