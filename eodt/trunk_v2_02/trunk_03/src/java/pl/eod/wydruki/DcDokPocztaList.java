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
import javax.xml.bind.annotation.XmlSeeAlso;
import pl.eod2.encje.DcDokument;

@XmlRootElement(name="DcDokPocztaList")
@XmlSeeAlso({DcDokPoczta.class})
public class DcDokPocztaList {
    private List<DcDokPoczta> dokumentList=new ArrayList<DcDokPoczta>();

    DcDokPocztaList(List<DcDokument> lista){
        for(DcDokument doc: lista){
            dokumentList.add(new DcDokPoczta(doc));
        }
    }
    
    DcDokPocztaList(){
        
    }

    public void setDokumentList(List<DcDokPoczta> dokumentList) {
        this.dokumentList = dokumentList;
    }
    
    @XmlElementWrapper(name = "dokumentList")
    @XmlElement(name = "dcDokPoczta")
    public List<DcDokPoczta> getDokumentList() {
        return dokumentList;
    }
    
}
