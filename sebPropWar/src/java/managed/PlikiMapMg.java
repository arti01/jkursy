
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import abstr.AbstMg;
import encje.Plikimap;
import encje.PlikimapDane;
import encje.PlikimapFacade;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import pl.arti01.prop.ValidPropFile;

@SessionScoped
@Named
public class PlikiMapMg extends AbstMg<Plikimap, PlikimapFacade> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String maskaZ;
    private String maskaNa;

    @Inject
    private PlikimapFacade dcCM;
    boolean sprDuzeLit;
    Map<String, String> bledyPliku = new HashMap<>();
    Map<String, String> bledyPlikuWalid = new HashMap<>();

    public PlikiMapMg() throws InstantiationException, IllegalAccessException {
        super("/all/plikimap", new Plikimap());
    }

    @PostConstruct
    public void init() {
        setDcC(dcCM);
        refresh();
    }

    @Override
    public void refresh() {
        bledyPliku.clear();
        sprDuzeLit = true;
        System.err.println("ssssssssssssssssssssssssssss");
        super.refresh(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dodaj() {
        super.dodaj("dodano rekord pliku");
    }

    @Override
    public void edytuj() {
        super.edytuj("zmieniono rekord pliku");
    }

    public void edytujDane() {
        super.edytuj("zmieniono dane w pliku");
    }

    public void klonuj() {
        Plikimap plikRodzic = getObiekt();
        setObiekt(new Plikimap());
        getObiekt().setPlikimapRodzic(plikRodzic);
        getObiekt().setPlikimapDaneList(new ArrayList<PlikimapDane>());
        for (PlikimapDane pd : plikRodzic.getPlikimapDaneList()) {
            PlikimapDane pdN = new PlikimapDane();
            pdN.setNazwa(pd.getNazwa());
            pdN.setOpis(pd.getOpis());
            pdN.setDane(pd.getDane());
            pdN.setPlikimap(getObiekt());
            getObiekt().getPlikimapDaneList().add(pdN);
        }
    }

    public void zmienWgMaski() throws Exception {
        List<PlikimapDane> noweDane = new ArrayList<>();
        for (PlikimapDane pd : getObiekt().getPlikimapDaneList()) {
            String dane = pd.getDane();
            String daneNew = dane.replace(maskaZ, maskaNa);
            pd.setDane(daneNew);
            if (!daneNew.equals(dane)) {
                getBledyPliku().put(pd.getNazwa(), dane + "->" + daneNew);
            }
            noweDane.add(pd);
        }
        getObiekt().setPlikimapDaneList(noweDane);
        dcCM.edit(getObiekt());
    }

    public void waliduj() throws FileNotFoundException, IOException{
        bledyPlikuWalid.clear();
        Properties pr = new Properties();
        for (PlikimapDane pmd : getObiekt().getPlikimapDaneList()) {
            pr.put(pmd.getNazwa(), pmd.getDane());
        }

        FileOutputStream fos=new FileOutputStream("sss");
        pr.store(fos, maskaZ);
        bledyPlikuWalid = ValidPropFile.doIt(new BufferedReader(new FileReader("sss")), sprDuzeLit);
    }
    
    public void listenerLadujPlik(FileUploadEvent event) throws Exception {
        Properties plikMapy = new Properties();
        UploadedFile item = event.getUploadedFile();
        ByteArrayInputStream in = new ByteArrayInputStream(item.getData());
        BufferedReader bR = new BufferedReader(new InputStreamReader(in));

        //walidacja pliku;
        in.mark(0);
        bledyPliku = ValidPropFile.doIt(bR, sprDuzeLit);
        in.reset();
        if (!bledyPliku.isEmpty()) {
            return;
        }
        addFacesMessage("plik wydaje się OK");
        try {
            plikMapy.load(in);
            getObiekt().setPlikimapDaneList(new ArrayList<PlikimapDane>());
            Enumeration<?> e = plikMapy.propertyNames();
            for (; e.hasMoreElements();) {
                String klucz = e.nextElement().toString();
                String wartosc = plikMapy.getProperty(klucz);
                PlikimapDane pmd = new PlikimapDane();
                pmd.setDane(wartosc);
                pmd.setNazwa(klucz);
                pmd.setDataZmiany(new Date());
                pmd.setPlikimap(getObiekt());
                getObiekt().getPlikimapDaneList().add(pmd);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            String blad = " coś nie teges z plikiem";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, blad, blad);
            UIComponent input = zapisz.getParent().findComponent("upload");
            context.addMessage(input.getClientId(context), message);
            Logger.getLogger(AbstMg.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void download() {
        final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + getObiekt().getNazwa() + "\""); // or whatever type you're sending back
        //tworzenie pliku
        String plikTresc = "";
        Properties pr = new Properties();
        for (PlikimapDane pmd : getObiekt().getPlikimapDaneList()) {
            plikTresc += pmd.getNazwa() + "=" + pmd.getDane();
            pr.put(pmd.getNazwa(), pmd.getDane());
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            pr.store(bos, "plik dla środowiska: " + getObiekt().getSrodowisko());

            response.getOutputStream().write(bos.toByteArray()); // from the UploadDetails bean
            response.setContentLength(bos.size());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            Logger.getLogger(AbstMg.class.getName()).log(Level.SEVERE, null, e);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    public String getMaskaZ() {
        return maskaZ;
    }

    public void setMaskaZ(String maskaZ) {
        this.maskaZ = maskaZ;
    }

    public String getMaskaNa() {
        return maskaNa;
    }

    public void setMaskaNa(String maskaNa) {
        this.maskaNa = maskaNa;
    }

    public PlikimapFacade getDcCM() {
        return dcCM;
    }

    public void setDcCM(PlikimapFacade dcCM) {
        this.dcCM = dcCM;
    }

    public boolean isSprDuzeLit() {
        return sprDuzeLit;
    }

    public void setSprDuzeLit(boolean sprDuzeLit) {
        this.sprDuzeLit = sprDuzeLit;
    }

    public Map<String, String> getBledyPliku() {
        return bledyPliku;
    }

    public void setBledyPliku(Map<String, String> bledyPliku) {
        this.bledyPliku = bledyPliku;
    }

    public Map<String, String> getBledyPlikuWalid() {
        return bledyPlikuWalid;
    }

    public void setBledyPlikuWalid(Map<String, String> bledyPlikuWalid) {
        this.bledyPlikuWalid = bledyPlikuWalid;
    }

}
