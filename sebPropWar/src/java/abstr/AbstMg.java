package abstr;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

public abstract class AbstMg<X extends AbstEncja, Y extends AbstKontroler<X>> {

    private DataModel<X> lista;
    private Y dcC;
    private X obiekt;
    private String error;
    private final String link;

    @SuppressWarnings({"unchecked", "unchecked"})
    public AbstMg(String s, X obiekt) throws InstantiationException, IllegalAccessException {
        link = s;
        this.lista = new ListDataModel<>();
        this.obiekt = obiekt;
    }

    @SuppressWarnings("unchecked")
    public void newObiekt() throws InstantiationException, IllegalAccessException {
        obiekt = (X) obiekt.getClass().newInstance();
    }

    public static void addFacesMessage(String messageText) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(messageText));
    }
    
    @SuppressWarnings("unchecked")
    public void refresh() {
        try {
            lista.setWrappedData(dcC.findEntities());
            obiekt = (X) obiekt.getClass().newInstance();
            error = null;
            //rejestracja.czyscFiltry();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dodaj() {
        obiekt.setDataZmiany(new Date());
        Map<String, String> errorMap=new HashMap<>();
        try {
            errorMap = dcC.create(obiekt);
        } catch (Exception ex) {
            errorMap.put("bladD", "bład zmiany/dodawania");
        }
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

    public void edytuj() {
        obiekt.setDataZmiany(new Date());
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        Map<String, String> errorMap=new HashMap<>();
        try {
            errorMap = dcC.edit(obiekt);
        } catch (Exception ex) {
            errorMap.put("bladD", "bład zmiany/dodawania");
        }
        if (!errorMap.isEmpty()) {
            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), entry.getValue());
                UIComponent input = zapisz.getParent().findComponent(entry.getKey());
                context.addMessage(input.getClientId(context), message);
            }
            lista.setWrappedData(dcC.findEntities());
        } else {
            refresh();
        }
    }

    public void usun() throws InstantiationException, IllegalAccessException {
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

    public Y getDcC() {
        return dcC;
    }

    public void setDcC(Y dcC) {
        this.dcC = dcC;
    }

}
