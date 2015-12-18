
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
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

@SessionScoped
@Named
public class PlikiMapMg extends AbstMg<Plikimap, PlikimapFacade> implements Serializable {

    @Inject
    private PlikimapFacade dcCM;

    public PlikiMapMg() throws InstantiationException, IllegalAccessException {
        super("/all/plikimap", new Plikimap());
    }

    @PostConstruct
    public void init() {
        setDcC(dcCM);
        refresh();
    }

    public void listenerLadujPlik(FileUploadEvent event) throws Exception {
        Properties plikMapy = new Properties();
        UploadedFile item = event.getUploadedFile();
        ByteArrayInputStream in = new ByteArrayInputStream(item.getData());

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

}
