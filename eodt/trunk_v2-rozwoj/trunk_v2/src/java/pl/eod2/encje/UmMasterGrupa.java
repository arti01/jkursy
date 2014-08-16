/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "UM_MASTERGRUPA")
public class UmMasterGrupa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUMGRUPA")
    @SequenceGenerator(name = "SEQUMGRUPA", sequenceName = "SEQUMGRUPA")
    private Long id;
    @Size(min = 1, max = 256)
    @Column(name = "nazwa", nullable = false, length = 256, unique = true)
    private String nazwa;
    @OneToMany(mappedBy = "masterGrp", cascade = CascadeType.MERGE)
    private List<UmGrupa> grupaList;

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

    public List<UmGrupa> getGrupaList() {
        return grupaList;
    }

    public void setGrupaList(List<UmGrupa> grupaList) {
        this.grupaList = grupaList;
    }
    
    
}
