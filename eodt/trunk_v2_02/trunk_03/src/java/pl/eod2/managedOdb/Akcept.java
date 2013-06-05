package pl.eod2.managedOdb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import pl.eod.managed.Login;
import pl.eod2.encje.DcDokument;
import pl.eod2.encje.DcDokumentJpaController;
import pl.eod2.encje.DcDokumentKrok;
import pl.eod2.encje.DcDokumentKrokUzytkownik;

@ManagedBean(name = "AkceptOdb")
@SessionScoped
public class Akcept {

    private DcDokument obiekt;
    private DcDokumentJpaController dcC;
    @ManagedProperty(value = "#{login}")
    private Login login;
    private String akceptOpis = "";

    @PostConstruct
    void init() {
        dcC = new DcDokumentJpaController();
    }

    public String list() {
        return "/dcodb/akcList";
    }

    public String detale() {
        return "/dcodb/akcDetale?faces-redirect=true";
    }

    public String akceptuj() {
        DcDokumentKrokUzytkownik dkuDoZmiany = new DcDokumentKrokUzytkownik();
        for (DcDokumentKrok dk : obiekt.getDcDokKrok()) {
            if (dk.getAkcept().getId() == 2 || dk.getAkcept().getId() == 3) {
                for (DcDokumentKrokUzytkownik dku : dk.getDcKrokUzytkownikaList()) {
                    if (dku.getIdUser() == login.getZalogowany().getUserId()) {
                        dkuDoZmiany = dku;
                    }
                }
            }
        }
        try {
            dkuDoZmiany.setInformacja(akceptOpis);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        dcC.akceptuj(dkuDoZmiany);
        akceptOpis = "";
        return "/dcodb/akcList?faces-redirect=true";
    }

    public DcDokument getObiekt() {
        return obiekt;
    }

    public void setObiekt(DcDokument obiekt) {
        this.obiekt = obiekt;
    }

    public Login getLogin() {
        login.refresh();
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getAkceptOpis() {
        return akceptOpis;
    }

    public void setAkceptOpis(String akceptOpis) {
        this.akceptOpis = akceptOpis;
    }
}
