package org.arti01.entit_new;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the _roles database table.
 * 
 */
@Entity
@Table(name="_roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=2147483647)
	private String rola;

	@Column(nullable=false, length=255)
	private String opis;

	//bi-directional many-to-many association to User
    @ManyToMany
	@JoinTable(
		name="_user_roles"
		, joinColumns={
			@JoinColumn(name="role_name", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="username", nullable=false)
			}
		)
	private Set<User> users;

    public Role() {
    }

	public String getRola() {
		return this.rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}