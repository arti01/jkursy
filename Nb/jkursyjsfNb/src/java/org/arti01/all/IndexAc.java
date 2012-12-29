package org.arti01.all;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.log4j.Logger;
import org.arti01.entit.Boksy;
import org.arti01.entit.Boksycfg;
import org.arti01.entit.Newsy;
import org.arti01.entit.Poziomyzaawansowania;
import org.arti01.entit.Statyczne;
import org.arti01.entit.Typykursu;
import org.arti01.entit.User;
import org.arti01.sesBean.BoksyImp;
import org.arti01.sesBean.BoksycfgImp;
import org.arti01.sesBean.PoziomyZaawansowaniaImp;
import org.arti01.sesBean.StatyczneImp;
import org.arti01.sesBean.TypyKursuImp;

@ManagedBean(name = "indexAc")
@SessionScoped
public class IndexAc implements Serializable {

    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(IndexAc.class);
    User user;
    Statyczne strona;
    private Newsy news;
    private DataModel<Boksy> allBoks = new ListDataModel<Boksy>();
    @EJB
    BoksyImp boksImp;
    private Boksycfg boxCfg = new Boksycfg();
    @EJB
    BoksycfgImp boxcfgImp;
    private Boksy boks;
    private Boksy baner;
    private Boksy vbaner;
    private ListDataModel<Statyczne> statyczneModel = new ListDataModel<Statyczne>();
    @EJB
    StatyczneImp statyczneImp;
    private ListDataModel<Poziomyzaawansowania> pzModel = new ListDataModel<Poziomyzaawansowania>();
    @EJB
    PoziomyZaawansowaniaImp pzImp;
    private Poziomyzaawansowania pz;
    private ListDataModel<Typykursu> tkModel = new ListDataModel<Typykursu>();
    @EJB
    TypyKursuImp tkImp;
    private Typykursu tk;

    public String index() {
        return "index.xhtml?faces-redirect=false";
    }

    public String newsWiecej() {
        //logger.info(news);
        return "news";
    }

    public String statyczna() throws Exception {
        strona = this.getStatyczneModel().getRowData();
        //logger.info(strona.getTytul());
        return "statyczneDetale";
    }

    public String pz() {
        return "pzDetale";
    }

    public String tk() {
        return "tkDetale";
    }

    public void paintBazy(OutputStream stream, Object object) throws IOException {
        Boksy b1 = new Boksy();
        b1.setIdboksy((Integer) object);
        b1 = boksImp.find(b1);
        stream.write(b1.getObraz());
    }

    public String zalozKonto() throws Exception {
        user = new User();
        user.setDataZmiany(new Date());
        //logger.info(this.getZalogowany());
        return "zalozKonto";
    }
    /*public String dodajKonto() throws Exception{
     UsersAc ua=new UsersAc();
     ua.setUser(user);
     ua.dodaj();
     setInfoText(ua.getInfoText()+" teraz czekaj az dostaniesz maila");
     //UserAc=new UsersAc().dodaj();
     //logger.info(this.getZalogowany());
     return "info";
     }
	
     public String logout() throws Exception {
     HttpServletRequest request = ServletActionContext.getRequest();
     request.getSession().invalidate();
     setLogin(null);
     return "logout";
     }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Statyczne getStrona() {
        return strona;
    }

    public void setStrona(Statyczne strona) {
        this.strona = strona;
    }

    public ListDataModel<Statyczne> getStatyczneModel() {
        if (statyczneModel.getRowIndex() == -1) {
            statyczneModel.setWrappedData(statyczneImp.getFindForAll());
        }
        return statyczneModel;
    }

    public void setStatyczneModel(ListDataModel<Statyczne> statyczneModel) {
        this.statyczneModel = statyczneModel;
    }

    public Newsy getNews() {
        return news;
    }

    public void setNews(Newsy news) {
        this.news = news;
    }

    public ListDataModel<Poziomyzaawansowania> getPzModel() {
        if (pzModel.getRowIndex() == -1) {
            pzModel.setWrappedData(pzImp.getAll());
        }
        return pzModel;
    }

    public void setPzModel(ListDataModel<Poziomyzaawansowania> pzModel) {
        this.pzModel = pzModel;
    }

    public Poziomyzaawansowania getPz() {
        return pz;
    }

    public void setPz(Poziomyzaawansowania pz) {
        this.pz = pz;
    }

    public void setTkModel(ListDataModel<Typykursu> tkModel) {
        this.tkModel = tkModel;
    }

    public ListDataModel<Typykursu> getTkModel() {
        if (tkModel.getRowIndex() == -1) {
            tkModel.setWrappedData(tkImp.getAll());
        }
        return tkModel;
    }

    public void setTk(Typykursu tk) {
        this.tk = tk;
    }

    public Typykursu getTk() {
        return tk;
    }

    public DataModel<Boksy> getAllBoks() {
        if (allBoks.getRowIndex() == -1) {
            allBoks.setWrappedData(getBoxCfg().getBoksy());
        }
        return allBoks;
    }

    public void setAllBoks(DataModel<Boksy> allBoks) {
        this.allBoks = allBoks;
    }

    public Boksycfg getBoxCfg() {
        boxCfg.setIdboksycfg(1);
        boxCfg = boxcfgImp.find(boxCfg);
        return boxCfg;
    }

    public void setBoxCfg(Boksycfg boxCfg) {
        this.boxCfg = boxCfg;
    }

    public Boksy getBoks() {
        return boks;
    }

    public void setBoks(Boksy boks) {
        this.boks = boks;
    }

    public Boksy getBaner() {
        Boksycfg banerCfg = new Boksycfg();
        banerCfg.setIdboksycfg(2);
        banerCfg = boxcfgImp.find(banerCfg);
        try {
            baner = banerCfg.getBoksy().iterator().next();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return baner;
        }
        return baner;
    }

    public void setBaner(Boksy baner) {
        this.baner = baner;
    }

    public Boksy getVbaner() {
        Boksycfg banerCfg = new Boksycfg();
        banerCfg.setIdboksycfg(3);
        banerCfg = boxcfgImp.find(banerCfg);
        try {
            vbaner = banerCfg.getBoksy().iterator().next();
        } catch (Exception e) {
            // TODO: handle exception
            return vbaner;
        }
        return vbaner;
    }

    public void setVbaner(Boksy vbaner) {
        this.vbaner = vbaner;
    }
}