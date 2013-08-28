/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.wydruki;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FopFactory;
import pl.eod2.encje.DcDokument;
import pl.eod2.managedRej.Rejestracja;


@ManagedBean(name = "wydruk")
@SessionScoped
@XmlRootElement(name="TestData")
@XmlSeeAlso({DcDokument.class})
public class Wydruk {
    private String test;
    @ManagedProperty(value = "#{RejestracjaRej}")
    private Rejestracja rej;
    private List<DcDokument> dokumentList;

    @PostConstruct
    public void init(){
        String myString="ddddddddddd";
        test="test wydrukow";
        
        String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
        System.err.println(absolutePath);
        String templateFilePath = "./web/resources/wydruki/";
        dokumentList=(List<DcDokument>) getRej().getLista().getWrappedData();
        
        PDFHandler handler = new PDFHandler();

        try {
            ByteArrayOutputStream streamSource = handler.getXMLSource(this);

            handler.createPDFFile(streamSource, templateFilePath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Rejestracja getRej() {
        return rej;
    }

    public void setRej(Rejestracja rej) {
        this.rej = rej;
    }

    public List<DcDokument> getDokumentList() {
        return dokumentList;
    }

    public void setDokumentList(List<DcDokument> dokumentList) {
        this.dokumentList = dokumentList;
    }

}
