package com.crimsonlogic.sampleservletjspproject.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JavaBean class used in jsp action tags
 * @author nagamounikay
 *
 */
@Data
@NoArgsConstructor
public class User implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -8085357226529655427L;
	
	private String user_firstName;
	private String user_lastName;
	private String user_userName;
	private String user_password;

}
