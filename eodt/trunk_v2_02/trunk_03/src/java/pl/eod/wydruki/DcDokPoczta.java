/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.wydruki;

import pl.eod2.encje.DcDokument;

/**
 *
 * @author 103039
 */
public class DcDokPoczta {
    int id;
    String nazwa;
    String kontrahentNazwa;
    String kontrahentAdres;

    DcDokPoczta(DcDokument doc){
        id=doc.getId();
        nazwa=doc.getNazwa();
        kontrahentNazwa=doc.getKontrahentId().getNazwa();
        kontrahentAdres =doc.getKontrahentId().getAdres();
    }
    
    DcDokPoczta(){
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKontrahentNazwa() {
        return kontrahentNazwa;
    }

    public void setKontrahentNazwa(String kontrahentNazwa) {
        this.kontrahentNazwa = kontrahentNazwa;
    }

    public String getKontrahentAdres() {
        return kontrahentAdres;
    }

    public void setKontrahentAdres(String kontrahentAdres) {
        this.kontrahentAdres = kontrahentAdres;
    }
    
}
