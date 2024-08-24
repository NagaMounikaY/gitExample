package com.crimsonlogic.sampleservletjspproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crimsonlogic.sampleservletjspproject.model.Todo;
import com.crimsonlogic.sampleservletjspproject.utils.JdbcUtils;
/**
 * This dao class provides CRUD database operation for todos table in the db
 * @author swapnar
 * @version 1.0
 */
public class TodoDaoImpl implements ITodoDao {
	
	
	private static final String INSERT_TODOS_SQL = "insert into todos"
			+ "(todos_title,todos_description,todos_username,"
			+ "todos_target_date,todos_is_done) VALUES (?,?,?,?,?)";
	private static final String SELECT_ALL_TODOS = "SELECT * from todos";
	private static final String SELECT_TODO_BY_ID = "SELECT * from todos WHERE todos_id = ?";
	private static final String DELETE_TODO_BY_ID = "DELETE from todos WHERE todos_id = ?";
	private static final String UPDATE_TODO = "UPDATE todos set todos_title=?,todos_username = ?,todos_description=?,"
			+ "												todos_target_date=?,todos_is_done=? where todos_id=?";

	@Override
	public void insertTodo(Todo todo){
		Connection conn  = null;
		PreparedStatement pstmt = null;
		System.out.println(INSERT_TODOS_SQL);
		try{
			conn = JdbcUtils.getConnection();
			
			pstmt = conn.prepareStatement(INSERT_TODOS_SQL);
			pstmt.setString(1, todo.getTodo_title());
			pstmt.setString(2, todo.getTodo_description());
			pstmt.setString(3, todo.getTodo_username());
			pstmt.setDate(4,JdbcUtils.getSQLDate(todo.getTodo_targetDate()));
			pstmt.setBoolean(5, todo.isTodo_status());
			System.out.println(pstmt);
			pstmt.executeUpdate();
		}catch(SQLException exception){
			JdbcUtils.printSQLException(exception);
		}finally{
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public Todo selectTodo(Long todoId) {
		Todo todo = null;
		Connection conn  = null;
		PreparedStatement pstmt = null;
		try{
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			pstmt.setLong(1, todoId);
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				long id =  rs.getLong("todos_id");
				String title =  rs.getString("todos_title");
				String description =  rs.getString("todos_description");
				String username =  rs.getString("todos_username");
				LocalDate targetDate = JdbcUtils.getUtilDate(rs.getDate("todos_target_date"));
				boolean isDone = rs.getBoolean("todos_is_done");
				todo =  new Todo(id,title, username, description, targetDate, isDone);
			}
		}catch(SQLException exception){
			JdbcUtils.printSQLException(exception);
		}finally{
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
		Connection conn  = null;
		PreparedStatement pstmt = null;
		List<Todo> todoList = null;
		try{
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(SELECT_ALL_TODOS);
			todoList =  new ArrayList<Todo>();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				long id =  rs.getLong("todos_id");
				String title =  rs.getString("todos_title");
				String description =  rs.getString("todos_description");
				String username =  rs.getString("todos_username");
				LocalDate targetDate = JdbcUtils.getUtilDate
						(rs.getDate("todos_target_date"));
				boolean isDone = rs.getBoolean("todos_is_done");
				todoList.add(new Todo(id,title, username, 
						description, targetDate, isDone));
				
			}
		}catch(SQLException exception){
			JdbcUtils.printSQLException(exception);
		}finally{
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todoList;
	}

	@Override
	public boolean deleteTodo(int id) throws SQLException {
		boolean rowDeleted = false;
		Connection conn  = null;
		PreparedStatement pstmt = null;
		try{
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(DELETE_TODO_BY_ID);
			pstmt.setLong(1, id);
			System.out.println(pstmt);
			rowDeleted = pstmt.executeUpdate() > 0; 
		}catch(SQLException exception){
			JdbcUtils.printSQLException(exception);
		}finally{
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowDeleted;
	}

	@Override
	public boolean updateTodo(Todo todo) throws SQLException {
		boolean rowUpdated = false;
		Connection conn  = null;
		PreparedStatement pstmt = null;
		try{
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(UPDATE_TODO);
			/*
			 * "UPDATE todos set todos_title=?,todos_username = ?,
			 * todos_description=?,"
			+ "todos_target_date=?,todos_is_done=? where todos_id=?";
			 */
			pstmt.setString(1,todo.getTodo_title());
			pstmt.setString(2,todo.getTodo_username());
			pstmt.setString(3,todo.getTodo_description());
			pstmt.setDate(4,JdbcUtils.getSQLDate(todo.getTodo_targetDate()));
			pstmt.setBoolean(5,todo.isTodo_status());
			pstmt.setLong(6,todo.getTodo_id());
			System.out.println(pstmt);
			rowUpdated = pstmt.executeUpdate() > 0; 
		}catch(SQLException exception){
			JdbcUtils.printSQLException(exception);
		}finally{
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowUpdated;
	}

}
