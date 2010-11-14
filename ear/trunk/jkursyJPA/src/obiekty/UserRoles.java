package obiekty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name = "_user_roles")
public class UserRoles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "_user_roles_id__user_roles_seq")
	@SequenceGenerator(name = "_user_roles_id__user_roles_seq", sequenceName = "_user_roles_id__user_roles_seq")
	@Column(name = "id_user_roles")
	private Integer id_user_roles;
	
	@ManyToOne()
	@JoinColumn(name = "username")
	private User user;
	
	@Column(name = "role_name")
	private String rolename;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRolename() {
		return rolename;
	}
	
	@RequiredStringValidator(message="rola nie może być pusta")
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getId_user_roles() {
		return id_user_roles;
	}

	public void setId_user_roles(Integer id_user_roles) {
		this.id_user_roles = id_user_roles;
	}

}