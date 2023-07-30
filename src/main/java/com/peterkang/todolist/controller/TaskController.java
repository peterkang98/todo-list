package com.peterkang.todolist.controller;

import com.peterkang.todolist.model.Task;
import com.peterkang.todolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService){
    this.taskService = taskService;
  }

  @ModelAttribute(name = "task")
  public Task task() {
    return new Task();
  }

  @ModelAttribute(name = "tasks")
  public List<Task> tasks() {
    List<Task> tasks = taskService.getAllTasks();
    return tasks;
  }

  @GetMapping
  public String getAllTasks(Model model){
    return "list";
  }

  @PostMapping("/new")
  public String addTask(@Valid Task task, Errors errors){
    if(errors.hasErrors()){
      return "list";
    }
    taskService.addTask(task);
    return "redirect:/tasks";
  }

  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model){
    Task task = taskService.getTaskById(id).orElseThrow(()-> new IllegalArgumentException("Invalid task Id: " + id));
    model.addAttribute("task", task);
    return "edit";
  }

  @PostMapping("/{id}/edit")
  public String editTask(@Valid Task task, Errors errors){
    if(errors.hasErrors()){
      return "edit";
    }
    taskService.updateTask(task);
    return "redirect:/tasks";
  }

  @GetMapping("/{id}/delete")
  public String deleteTask(@PathVariable Long id){
    taskService.deleteTask(id);
    return "redirect:/tasks";
  }
}
