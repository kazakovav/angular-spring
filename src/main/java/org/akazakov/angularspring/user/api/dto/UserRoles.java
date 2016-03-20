package org.akazakov.angularspring.user.api.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class UserRoles implements Serializable {
	private static final long serialVersionUID = 5846867865257089407L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "role", length = 20)
	private String role;

	public UserRoles() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
