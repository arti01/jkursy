package arti01.jobiady.beany;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the _roles database table.
 * 
 */
@Entity
@Table(name="roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ADMIN ="admin" ;
	public static final String WYKLADOWCA ="User" ;

	@Id
	private String rola;

	@Column(nullable=false, length=255)
	private String opis;

	//bi-directional many-to-many association to User
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="roles")
	@OrderBy(value="imieNazwisko")
	private List<Uzytkownik> users;	
        
        public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Uzytkownik> getUsers() {
        return users;
    }

    public void setUsers(List<Uzytkownik> users) {
        this.users = users;
    }

}