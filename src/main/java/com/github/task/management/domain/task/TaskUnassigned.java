package com.github.task.management.domain.task;

public class TaskUnassigned extends TaskEvent {

  public TaskUnassigned(final TaskId taskId) {
    super(taskId);
  }
}
