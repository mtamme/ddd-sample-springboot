package com.github.task.management.domain.task;

public class TaskOpened extends TaskEvent {

  public TaskOpened(final TaskId taskId) {
    super(taskId);
  }
}
