package com.peterkang.todolist;

import com.peterkang.todolist.model.Task;
import com.peterkang.todolist.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(TaskRepository taskRepository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Task task1 = new Task(1L, "Vacuum the House", "Living room needs to be mopped as well", LocalDate.of(2023, 7, 24));
				taskRepository.save(task1);

				Task task2 = new Task(2L, "Take Out the Trash", "Recyclables and food garbage as well", LocalDate.of(2023, 7, 24));
				taskRepository.save(task2);
			}
		};
	}
}
