package org.arti01.obiekty;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the _user_roles database table.
 * 
 */
@Entity
@Table(name="_user_roles")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="_USER_ROLES_IDUSERROLES_GENERATOR", sequenceName="_USER_ROLES_ID_USER_ROLES_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="_USER_ROLES_IDUSERROLES_GENERATOR")
	@Column(name="id_user_roles")
	private Integer idUserRoles;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_name")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username")
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