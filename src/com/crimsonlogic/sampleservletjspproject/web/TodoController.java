package com.crimsonlogic.sampleservletjspproject.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.sampleservletjspproject.dao.ITodoDao;
import com.crimsonlogic.sampleservletjspproject.dao.TodoDaoImpl;
import com.crimsonlogic.sampleservletjspproject.model.Todo;

/**
 * Servlet implementation class TodoController
 */
@WebServlet("/")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ITodoDao todoDAO;
	
	public void init(){
		todoDAO = new TodoDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		try{
			switch (action) {
			case "/new":
				showNewForm(req,resp);
				break;
			case "/insert":
				insertTodo(req,resp);
				break;
			case "/delete":
				deleteTodo(req,resp);
				break;
			case "/edit":
				showEditForm(req,resp);
				break;
			case "/update" :
				updateTodo(req,resp);
				break;
			case "/list" :
				listTodo(req,resp);
				break;
			default:
				RequestDispatcher rd = req.getRequestDispatcher("login/login.jsp");
				rd.forward(req, resp);
				break;
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
	}

	private void listTodo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,SQLException {
		List<Todo> todoList = todoDAO.selectAllTodos();
		req.setAttribute("todoList", todoList);
		RequestDispatcher rd = req.getRequestDispatcher("todo/todo-list.jsp");
		rd.forward(req, resp);
	}

	private void updateTodo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,SQLException {
		Long id = Long.parseLong(req.getParameter("id"));
		String title = req.getParameter("title");
		String username = req.getParameter("username");
		String description = req.getParameter("description");
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(req.getParameter("targetDate"));
		boolean isDone = Boolean.valueOf(req.getParameter("isDone"));
		Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);
		todoDAO.updateTodo(updateTodo);
		resp.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,SQLException {
		Long id = Long.parseLong(req.getParameter("id"));
		Todo existingTodo = todoDAO.selectTodo(id);
		RequestDispatcher rd = req.getRequestDispatcher("todo/todo-form.jsp");
		req.setAttribute("existingTodo", existingTodo);
		rd.forward(req, resp);
	}

	private void deleteTodo(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException,SQLException {
		int id = Integer.parseInt(req.getParameter("id"));
		todoDAO.deleteTodo(id);
		resp.sendRedirect("list");
	}

	private void insertTodo(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException,SQLException {
		String title =  req.getParameter("title");
		String username =  req.getParameter("username");
		String description = req.getParameter("description");
		boolean isDone =  Boolean.valueOf(req.getParameter("isDone"));
		Todo newTodoObj = new Todo(title,username,description,LocalDate.now(),isDone);
		todoDAO.insertTodo(newTodoObj);
		resp.sendRedirect("list");
	}

	private void showNewForm(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException,SQLException {
		RequestDispatcher rd = req.getRequestDispatcher("todo/todo-form.jsp");
		rd.forward(req, resp);	
	}
  
}
