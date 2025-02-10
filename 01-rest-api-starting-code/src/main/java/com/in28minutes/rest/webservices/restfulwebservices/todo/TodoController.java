package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "/users/{username}/todos")
	public List<Todo> findTodosByUsername(@PathVariable String username) {
		return todoService.findByUsername(username);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping(path = "/users/{username}/todos/{id}")
	public void deleteTodoById(@PathVariable int id) {
		todoService.deleteById(id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "users/{username}/todos/{id}")
	public Todo findTodoById(@PathVariable int id) {
		return todoService.findById(id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(path = "/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable int id, @RequestBody Todo todo) {
		todoService.updateTodo(todo);
		return todoService.findById(todo.getId());
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/users/{username}/todos")
	public Todo createTodo(@RequestBody Todo todo) {
		return todoService.addTodo(todo.getUsername(), todo.getDescription(), todo.getDate(), false);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "/basicauth")
	public String basicAuth() {
		return "Basic Auth";
	}

}
