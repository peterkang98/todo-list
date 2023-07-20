package com.peterkang.todolist.service;

import com.peterkang.todolist.model.Task;
import com.peterkang.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(TaskRepository taskRepository){
    this.taskRepository = taskRepository;
  }

  public List<Task> getAllTasks(){
    return taskRepository.findAll();
  }

  public Optional<Task> getTaskById(long id){
    return taskRepository.findById(id);
  }

  public Task addTask(Task task) {
    return taskRepository.save(task);
  }

  public boolean updateTask(Task updatedTask){
    if (taskRepository.existsById(updatedTask.getId())){
      taskRepository.save(updatedTask);
      return true;
    }
    return false;
  }

  public boolean deleteTask(long id){
    if (taskRepository.existsById(id)){
      taskRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
