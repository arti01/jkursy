
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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

    @Inject
    private PlikimapFacade dcCM;
    boolean sprDuzeLit;
    Map<String, String> bledyPliku = new HashMap<>();

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

    public void downloadList(ActionEvent event){
        System.err.println("ssssssssssss");
	download();
 
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
            pr.store(bos, "plik dla środowiska: "+getObiekt().getSrodowisko());
            
            response.getOutputStream().write(bos.toByteArray()); // from the UploadDetails bean
            response.setContentLength(bos.size());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            Logger.getLogger(AbstMg.class.getName()).log(Level.SEVERE, null, e);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    /*    @PostConstruct
    @Override
    public void refresh() {
        getLista().setWrappedData(dcR.findEntities());
        try {
            setObiekt(getObiekt().getClass().newInstance());
        } catch (InstantiationException | IllegalAccessException ex1) {
            Logger.getLogger(PlikiMapMg.class.getName()).log(Level.SEVERE, null, ex1);
        }
        setError(null);
    }

    public String detale() {
        return "/dcarch/dokumentDetale?faces-redirect=true";
    }

    
    @Override
    public void dodaj() throws InstantiationException, IllegalAccessException {
        try {
            if (rejestracja.dodajAbst()) {
                refresh();
                rejestracja.refreshObiekt();
                //urzadzeniaMg.refresh();
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Dokumenty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edytuj() {
        if (rejestracja.edytujAbst()) {
            rejestracja.refreshObiekt();
            refresh();
        }
    }

    public DataModel<DcRodzaj> getRodzajLista() {
        rodzajLista.setWrappedData(dcR.findDcRodzajArch());
        return rodzajLista;
    }

    public void setRodzajLista(DataModel<DcRodzaj> rodzajLista) {
        this.rodzajLista = rodzajLista;
    }
     */
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

}
