/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.wydruki;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import pl.eod.encje.Struktura;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentKrok;

@XmlRootElement(name="DcDokPoczta")
public class DcDokPoczta {
    int id;
    String nazwa;
    String kontrahentNazwa;
    String kontrahentAdres;
    String dataWydruku;
    private List<DcDokKrokWydr> dcDokKrokWydrList=new ArrayList<DcDokKrokWydr>();

    public DcDokPoczta(DcDokument doc){
        id=doc.getId();
        nazwa=doc.getNazwa();
        kontrahentNazwa=doc.getKontrahentId().getNazwa();
        kontrahentAdres =doc.getKontrahentId().getAdres();
        
        List<DcDokumentKrok>krokiSort=doc.getDcDokKrok();
        
        Collections.sort(krokiSort, new Comparator<DcDokumentKrok>() {
            @Override
            public int compare(DcDokumentKrok o1, DcDokumentKrok o2) {
                int wynik;
                try {
                    if(o1.getLp()<=o2.getLp()){
                        wynik=-1;
                    }
                    else wynik=1;
                } catch (NullPointerException mpe) {
                    wynik = 0;
                }
                return wynik;
            }
        });
        
        for(DcDokumentKrok krok:krokiSort){
            DcDokKrokWydr dKr=new DcDokKrokWydr(krok);
            System.err.println(krok.getLp());
            System.err.println(dKr.getLp());
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

    public String getDataWydruku() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date());
    }

    public void setDataWydruku(String dataWydruku) {
        this.dataWydruku = dataWydruku;
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
