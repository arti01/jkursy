package pl.eod.abstr;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


public abstract class MgAbst<X extends EncjaAbst, Y extends AbstKontroler> {

    private DataModel<X> lista;
    private final Y dcC;
    private X obiekt;
    private String error;
    private final String link;

    public MgAbst(String s, AbstKontroler ak, X obiekt) throws InstantiationException, IllegalAccessException {
        link=s;
        this.lista = new ListDataModel<>();
        this.dcC=(Y) ak.getClass().newInstance();
        this.obiekt= (X) obiekt;
    }
    

    void refresh() throws InstantiationException, IllegalAccessException {
        lista.setWrappedData(dcC.findEntities());
        obiekt = (X) obiekt.getClass().newInstance();
        error = null;
    }

    public void dodaj() throws InstantiationException, IllegalAccessException {
        error = dcC.create(obiekt);
        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
        } else {
            refresh();
        }
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
