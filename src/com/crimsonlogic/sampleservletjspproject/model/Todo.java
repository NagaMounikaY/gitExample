package com.crimsonlogic.sampleservletjspproject.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/**
 * this is a model clas that represents the todo entity 
 */
public class Todo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long todo_id;
	private String todo_title;
	private String todo_username;
	private String todo_description;
	private LocalDate todo_targetDate;
	private boolean todo_status;
	public Todo(Long todo_id,String todo_title, String todo_username, String todo_description, LocalDate todo_targetDate,
			boolean todo_status) {
		super();
		this.todo_id=todo_id;
		this.todo_title = todo_title;
		this.todo_username = todo_username;
		this.todo_description = todo_description;
		this.todo_targetDate = todo_targetDate;
		this.todo_status = todo_status;
	}
	public Todo(String todo_title, String todo_username, String todo_description, LocalDate todo_targetDate,
			boolean todo_status) {
		super();
		this.todo_title = todo_title;
		this.todo_username = todo_username;
		this.todo_description = todo_description;
		this.todo_targetDate = todo_targetDate;
		this.todo_status = todo_status;
	}
	
	
	

}
