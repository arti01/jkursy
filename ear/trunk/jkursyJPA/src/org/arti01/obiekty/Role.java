package org.arti01.obiekty;

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
	@SequenceGenerator(name="_ROLES_ROLA_GENERATOR", sequenceName="_ROLES_ROLA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="_ROLES_ROLA_GENERATOR")
	private String rola;

	private String opis;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role")
	private Set<UserRole> userRoles;

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

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
}