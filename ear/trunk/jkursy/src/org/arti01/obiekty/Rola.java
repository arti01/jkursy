package org.arti01.obiekty;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "_roles")
public class Rola {
	public static final String ADMIN ="admin" ;
	public static final String WYKLADOWCA ="wykladowca" ;
	public static final String KURSANT ="kursant" ;
	public static final String NOWY ="nowy" ;
	@Id
	@Column(name="rola")
	private String rola;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="_user_roles", joinColumns = @JoinColumn( name="role_name"),inverseJoinColumns = @JoinColumn( name="username"))
	private Set <Rola> users;
	
	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	@Column(name="opis")
	private String opis;

	public String getOpis() {
		return opis;
	}
	
	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<Rola> getUsers() {
		return users;
	}

	public void setUsers(Set<Rola> users) {
		this.users = users;
	}

}