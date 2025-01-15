package com.github.task.management.domain.task;

public class TaskStarted extends TaskEvent {

  public TaskStarted(final TaskId taskId) {
    super(taskId);
  }
}
