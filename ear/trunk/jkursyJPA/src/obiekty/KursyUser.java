package obiekty;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kursy_users database table.
 * 
 */
@Entity
@Table(name="kursy_users")
public class KursyUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="KURSY_USERS_IDKURSYUSERS_GENERATOR", sequenceName="KURSY_USERS_IDKURSYUSERS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KURSY_USERS_IDKURSYUSERS_GENERATOR")
	private Integer idkursyusers;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username")
	private User user;

	//bi-directional many-to-one association to Kursy
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idkursy")
	private Kursy kursy;

    public KursyUser() {
    }

	public Integer getIdkursyusers() {
		return this.idkursyusers;
	}

	public void setIdkursyusers(Integer idkursyusers) {
		this.idkursyusers = idkursyusers;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Kursy getKursy() {
		return this.kursy;
	}

	public void setKursy(Kursy kursy) {
		this.kursy = kursy;
	}
	
}