package peaksoft.dto.mappers;

import org.springframework.stereotype.Component;
import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.responses.TaskResponse;
import peaksoft.models.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public Task convertToEntity(TaskRequest taskRequest) {
        if (taskRequest == null) {
            return null;
        }

        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        task.setCreatedAt(LocalDate.now());
        return task;
    }

    public TaskResponse convertToView(Task task) {
        if (task == null) {
            return null;
        }
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskText(task.getTaskText());
        taskResponse.setDeadline(task.getDeadline());
        taskResponse.setCreatedAt(LocalDate.now());
        return taskResponse;
    }

    public List<TaskResponse> convertAllTasksToView(List<Task> tasks) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(convertToView(task));
        }
        return taskResponses;
    }

    public Task update(Task task, TaskRequest taskRequest) {
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        task.setCreatedAt(LocalDate.now());
        return task;
    }


}
