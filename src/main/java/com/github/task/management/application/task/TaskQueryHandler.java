package com.github.task.management.application.task;

import com.github.task.management.application.task.view.TaskDetailView;
import com.github.task.management.application.task.view.TaskSummaryView;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface TaskQueryHandler {

  Optional<TaskDetailView> getTask(String taskId);

  List<TaskSummaryView> listTasks(long offset, int limit);

  List<TaskSummaryView> searchTasks(String query, long offset, int limit);
}
