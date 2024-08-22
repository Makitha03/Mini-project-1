package org.user.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class User {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@NotBlank(message="Name is mandatory")
	@Column(name="UserFirstName")
	private String UserFirstName;
	
	@NotBlank(message="Name is mandatory")
	@Column(name="UserLastName")
	private String UserLastName;
	
	@NotBlank(message="Email is mandatory")
	@Column(name="UserEmailId")
	@Email(message="Incorrect email format")
	private String UserEmailId;
	

}
