package org.arti01.entit_old;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the _user_roles database table.
 * 
 */
//@Entity
@Table(name="_user_roles")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="_USER_ROLES_IDUSERROLES_GENERATOR", sequenceName="_USER_ROLES_ID_USER_ROLES_SEQ")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user_roles", unique=true, nullable=false)
	private Integer idUserRoles;

	//bi-directional many-to-one association to Role
    @ManyToOne
	@JoinColumn(name="role_name", nullable=false)
	private Role role;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="username", nullable=false)
	private User user;

    public UserRole() {
    }

	public Integer getIdUserRoles() {
		return this.idUserRoles;
	}

	public void setIdUserRoles(Integer idUserRoles) {
		this.idUserRoles = idUserRoles;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}