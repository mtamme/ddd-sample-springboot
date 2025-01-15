package com.github.task.management.domain.task;

public class TaskClosed extends TaskEvent {

  public TaskClosed(final TaskId taskId) {
    super(taskId);
  }
}
