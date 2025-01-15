package com.github.task.management.domain.task;

import com.github.seedwork.domain.Event;

import java.util.Objects;

public abstract class TaskEvent implements Event {

  private final TaskId taskId;

  protected TaskEvent(final TaskId taskId) {
    this.taskId = Objects.requireNonNull(taskId);
  }

  public TaskId taskId() {
    return taskId;
  }
}
