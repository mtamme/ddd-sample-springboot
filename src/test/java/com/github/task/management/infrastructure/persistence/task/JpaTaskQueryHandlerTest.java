package com.github.task.management.infrastructure.persistence.task;

import com.github.seedwork.infrastructure.persistence.PersistenceTest;
import com.github.task.management.application.task.TaskQueryHandler;
import com.github.task.management.domain.project.ProjectRepository;
import com.github.task.management.domain.project.Projects;
import com.github.task.management.domain.task.TaskRepository;
import com.github.task.management.domain.task.Tasks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class JpaTaskQueryHandlerTest extends PersistenceTest {

  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  private TaskQueryHandler taskQueryHandler;
  @Autowired
  private TransactionTemplate transactionTemplate;

  @Test
  void getTaskShouldReturnTask() {
    // Arrange
    transactionTemplate.executeWithoutResult(ts -> {
      final var project = Projects.newProject("00000000000");

      projectRepository.save(project);
      final var task = Tasks.newOpenTask("00000000000", "00000000001");

      taskRepository.save(task);
    });

    // Act
    final var task = taskQueryHandler.getTask("00000000001");

    // Assert
    assertTrue(task.isPresent());
    assertEquals("00000000001", task.get().taskId());
    assertEquals("OPEN", task.get().status());
    assertEquals(Instant.EPOCH.minusSeconds(3L), task.get().openedAt());
    assertNull(task.get().startedAt());
    assertNull(task.get().closedAt());
    assertEquals("TestSummary", task.get().summary());
    assertEquals("TestDescription", task.get().description());
    final var assignee = task.get().assignee();

    assertNull(assignee);
    final var project = task.get().project();

    assertNotNull(project);
    assertEquals("00000000000", project.projectId());
    assertEquals("TestName", project.name());
  }

  @Test
  void listTasksShouldReturnTasks() {
    // Arrange
    transactionTemplate.executeWithoutResult(ts -> {
      final var project = Projects.newProject("00000000000");

      projectRepository.save(project);
      final var task = Tasks.newOpenTask("00000000000", "00000000001");

      taskRepository.save(task);
    });

    // Act
    final var tasks = taskQueryHandler.listTasks(0L, 1);

    // Assert
    final var task = tasks.getFirst();

    assertEquals("00000000001", task.taskId());
    assertEquals("OPEN", task.status());
    assertEquals("TestSummary", task.summary());
    final var assignee = task.assignee();

    assertNull(assignee);
    final var project = task.project();

    assertNotNull(project);
    assertEquals("00000000000", project.projectId());
    assertEquals("TestName", project.name());
  }

  @Test
  void searchTasksShouldReturnTasks() {
    // Arrange
    transactionTemplate.executeWithoutResult(ts -> {
      final var project = Projects.newProject("00000000000");

      projectRepository.save(project);
      final var task = Tasks.newOpenTask("00000000000", "00000000001");

      taskRepository.save(task);
    });

    // Act
    final var tasks = taskQueryHandler.searchTasks("test summary", 0L, 1);

    // Assert
    final var task = tasks.getFirst();

    assertEquals("00000000001", task.taskId());
    assertEquals("OPEN", task.status());
    assertEquals("TestSummary", task.summary());
    final var assignee = task.assignee();

    assertNull(assignee);
    final var project = task.project();

    assertNotNull(project);
    assertEquals("00000000000", project.projectId());
    assertEquals("TestName", project.name());
  }
}
