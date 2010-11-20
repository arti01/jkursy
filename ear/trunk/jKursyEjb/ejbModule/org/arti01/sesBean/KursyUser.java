package org.arti01.sesBean;

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
	@Column(unique=true, nullable=false)
	private Integer idkursyusers;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="username", nullable=false)
	private User user;

	//bi-directional many-to-one association to Kursy
    @ManyToOne
	@JoinColumn(name="idkursy", nullable=false)
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