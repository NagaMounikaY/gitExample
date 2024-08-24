package com.crimsonlogic.sampleservletjspproject.dao;

import java.sql.SQLException;
import java.util.List;

import com.crimsonlogic.sampleservletjspproject.model.Todo;

public interface ITodoDao {
	void insertTodo(Todo todo) throws SQLException;
	Todo selectTodo(Long todoId);
	List<Todo> selectAllTodos();
	boolean deleteTodo(int id)throws SQLException;
	//boolean updateTodo(Todo updateTodo)throws SQLException;
	boolean updateTodo(Todo id) throws SQLException;

}
