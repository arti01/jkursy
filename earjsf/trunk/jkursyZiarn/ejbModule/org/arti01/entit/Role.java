package org.arti01.entit;

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
	public static final String ADMIN ="admin" ;
	public static final String WYKLADOWCA ="wykladowca" ;
	public static final String KURSANT ="kursant" ;
	public static final String NOWY ="nowy" ;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=2147483647)
	private String rola;

	@Column(nullable=false, length=255)
	private String opis;

	//bi-directional many-to-many association to User
    @ManyToMany(cascade=CascadeType.ALL)
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
	@ManyToMany(mappedBy="roles", cascade=CascadeType.ALL)
	public Set<User> getUsers() {
		return this.users;
	}
	@ManyToMany(mappedBy="roles", cascade=CascadeType.ALL)
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}