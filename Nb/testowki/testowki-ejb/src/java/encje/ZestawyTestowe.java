/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encje;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAZWA"})})
public class ZestawyTestowe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQZestawyTestowe")
    @SequenceGenerator(name = "SEQZestawyTestowe", sequenceName = "SEQZestawyTestowe")
    private Long id;
    @NotNull
    @Size(min = 1, max = 200)
    private String nazwa;
    @Size(min = 1, max = 200)
    private String funkcja;
    @Size(max = 2048)
    @Column(nullable = true, length = 2048)
    @Lob
    private String infodod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zestawyTestowe", orphanRemoval = true)
    private List<Instancje> instancjeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Instancje> getInstancjeList() {
        return instancjeList;
    }

    public void setInstancjeList(List<Instancje> instancjeList) {
        this.instancjeList = instancjeList;
    }

    public String getFunkcja() {
        return funkcja;
    }

    public void setFunkcja(String funkcja) {
        this.funkcja = funkcja;
    }

    public String getInfodod() {
        return infodod;
    }

    public void setInfodod(String infodod) {
        this.infodod = infodod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZestawyTestowe)) {
            return false;
        }
        ZestawyTestowe other = (ZestawyTestowe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "encje.ZestawyTestowe[ id=" + id + " ]";
    }
}
