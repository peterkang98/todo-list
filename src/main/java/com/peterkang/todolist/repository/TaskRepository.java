package com.peterkang.todolist.repository;

import com.peterkang.todolist.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
  @Override
  List<Task> findAll();
}
