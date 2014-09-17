package pl.eod.abstr;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod2.encje.exceptions.IllegalOrphanException;
import pl.eod2.encje.exceptions.NonexistentEntityException;

public abstract class AbstMg<X extends AbstEncja, Y extends AbstKontroler> {

    private DataModel<X> lista;
    private final Y dcC;
    private X obiekt;
    private String error;
    private final String link;

    public AbstMg(String s, AbstKontroler ak, X obiekt) throws InstantiationException, IllegalAccessException {
        link = s;
        this.lista = new ListDataModel<>();
        this.dcC = (Y) ak.getClass().newInstance();
        this.obiekt = (X) obiekt;
    }

     public void newObiekt() throws InstantiationException, IllegalAccessException {
        obiekt = (X) obiekt.getClass().newInstance();
    }
    
    void refresh() throws InstantiationException, IllegalAccessException {
        lista.setWrappedData(dcC.findEntities());
        obiekt = (X) obiekt.getClass().newInstance();
        error = null;
    }

    public void dodaj() throws InstantiationException, IllegalAccessException {
        Map<String, String> errorMap = dcC.create(obiekt);
        if (!errorMap.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            //przycisk zapisz/dodaj
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), entry.getValue());
                UIComponent input = zapisz.getParent().findComponent(entry.getKey());
                context.addMessage(input.getClientId(context), message);
            }
        } else {
            refresh();
        }
    }

    public void edytuj() throws IllegalOrphanException, NonexistentEntityException, Exception {
        Map<String, String> errorMap = dcC.edit(obiekt);
        if (!errorMap.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            //przycisk zapisz/dodaj
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), entry.getValue());
                UIComponent input = zapisz.getParent().findComponent(entry.getKey());
                context.addMessage(input.getClientId(context), message);
                lista.setWrappedData(dcC.findEntities());
            }
        } else {
            refresh();
        }
    }
    
    public void usun() throws IllegalOrphanException, NonexistentEntityException, InstantiationException, IllegalAccessException {
        //rodzajGrupa=lista.getRowData();
        dcC.destroy(obiekt);
        refresh();
    }

    public String list() throws InstantiationException, IllegalAccessException {
        refresh();
        return link;
    }

    public DataModel<X> getLista() {
        return lista;
    }

    public void setLista(DataModel<X> lista) {
        this.lista = lista;
    }

    public X getObiekt() {
        return obiekt;
    }

    public void setObiekt(X obiekt) {
        this.obiekt = obiekt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
