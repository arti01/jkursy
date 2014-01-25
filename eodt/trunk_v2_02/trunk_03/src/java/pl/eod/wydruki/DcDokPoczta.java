/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.wydruki;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentKrok;

@XmlRootElement(name="DcDokPoczta")
public class DcDokPoczta {
    int id;
    String nazwa;
    String kontrahentNazwa;
    String kontrahentAdres;
    private List<DcDokKrokWydr> dcDokKrokWydrList=new ArrayList<DcDokKrokWydr>();;

    public DcDokPoczta(DcDokument doc){
        id=doc.getId();
        nazwa=doc.getNazwa();
        kontrahentNazwa=doc.getKontrahentId().getNazwa();
        kontrahentAdres =doc.getKontrahentId().getAdres();
        for(DcDokumentKrok krok:doc.getDcDokKrok()){
            DcDokKrokWydr dKr=new DcDokKrokWydr(krok);
            dcDokKrokWydrList.add(dKr);
        }
        //dcDokKrokWydrList=doc.getDcDokKrok();
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

    @XmlElementWrapper(name = "krokList")
    @XmlElement(name = "dcDokKrokWyd")
    public List<DcDokKrokWydr> getDcDokKrokWydrList() {
        return dcDokKrokWydrList;
    }

    public void setDcDokKrokWydrList(List<DcDokKrokWydr> dcDokKrokWydrList) {
        this.dcDokKrokWydrList = dcDokKrokWydrList;
    }

}
