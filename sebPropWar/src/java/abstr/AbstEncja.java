/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstr;

import java.util.Date;

public abstract class AbstEncja {

    private String nazwa;
    private Integer id;
    private Date dataZmiany;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataZmiany() {
        return dataZmiany;
    }

    public void setDataZmiany(Date dataZmiany) {
        this.dataZmiany = dataZmiany;
    }

}
