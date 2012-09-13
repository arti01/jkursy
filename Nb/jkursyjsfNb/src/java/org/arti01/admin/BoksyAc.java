package org.arti01.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Boksy;
import org.arti01.entit.Boksycfg;
import org.arti01.sesBean.BoksyImp;
import org.arti01.sesBean.BoksycfgImp;
import org.arti01.utility.ResizeJpg;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

@ManagedBean(name = "adminBoksyAc")
@SessionScoped
public class BoksyAc implements Serializable {

    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(BoksyAc.class);
    private DataModel<Boksycfg> all = new ListDataModel<Boksycfg>();
    private DataModel<Boksy> allBoks = new ListDataModel<Boksy>();
    private Boksycfg boxCfg;
    private Boksy boks;
    @EJB
    BoksycfgImp boxcfgImp;
    @EJB
    BoksyImp boksImp;
    private String infoText;
    private UploadedFile item;
    private static int DLUGOSC = 120;
    private static int WYSOKOSC = 90;

    public String list() {
        boxCfg = new Boksycfg();
        all.setWrappedData(boxcfgImp.getAll());
        infoText = "";
        return "boksycfgForm";
    }

    public void edytujForm() {
        boxCfg = all.getRowData();
        all.setWrappedData(boxcfgImp.getAll());
        infoText = "";
    }

    public String edytujBoksForm() {
        boks = allBoks.getRowData();
        boks = boksImp.find(boks);
        infoText = "";
        return "boksyForm";
    }

    public String usunBoks() {
        boks = allBoks.getRowData();
        logger.info("usuanie");
        try {
            //boksImp.delete(boks);
            logger.info(boxCfg.getBoksy().indexOf(boks));
            boxCfg.getBoksy().remove(boks);
            boxcfgImp.update(boxCfg);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        allBoks.setWrappedData(boxCfg.getBoksy());
        boks = new Boksy();
        infoText = "";
        return "boksyForm";
    }

    public void zmien() {
        boxcfgImp.update(boxCfg);
        boxCfg = new Boksycfg();
        infoText = "";
        all.setWrappedData(boxcfgImp.getAll());
    }

    public String usunFote() {
        if (boks.getIdboksy() != null) {
            boks.setObraz(null);
            boksImp.update(boks);
            logger.info("ssssssssss");
            boxCfg = boxcfgImp.find(boxCfg);
        }
        logger.info(item);
        item = null;
        return "boksyForm";
    }

    public String dodajBoks() {
        logger.info(boks.getNaglowek());
        boks.setBoksycfg(boxCfg);
        if (boks.getIdboksy() == null) {
            //logger.info("dodanie--"+tk.getNazwa()+"--"+tk.getOpis());
            //boksImp.insert(boks);
            boxCfg.getBoksy().add(boks);
            boxcfgImp.update(boxCfg);
        } else {
            logger.info("eeeeeeeeeeeeeeeeeeeeeeeeedycja");
            boksImp.update(boks);
            logger.info(boks.getBoksycfg().getIdboksycfg());
            boxCfg = boxcfgImp.find(boxCfg);
        }
        boks = new Boksy();
        allBoks.setWrappedData(boxCfg.getBoksy());
        infoText = "";
        item = null;
        return "boksyForm";
    }

    public String listaZawartosci() {
        boxCfg = all.getRowData();
        boks = new Boksy();
        allBoks.setWrappedData(boxCfg.getBoksy());
        infoText = "";
        return "boksyForm";
    }

    public void listenerFoty(FileUploadEvent event) {
        item = event.getUploadedFile();
        //logger.info(item.getName());
        //boks.setObraz(new ResizeJpg().zrobB(DLUGOSC, WYSOKOSC, item.getData()));
        boks.setObraz(item.getData());
        logger.info(boks.getObraz().length);
        if (boks.getIdboksy() != null) {
            boksImp.update(boks);
        }

    }

    public void paintBazy(OutputStream stream, Object object) throws IOException {
        //logger.info(object);
        //logger.info((Integer)object);
        //logger.info(item);
        if (object != null) {
            boks.setIdboksy((Integer) object);
            boks = boksImp.find(boks);
            stream.write(boks.getObraz());
        } else {
            stream.write(item.getData());
        }
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public Boksycfg getBoxCfg() {
        return boxCfg;
    }

    public void setBoxCfg(Boksycfg boxCfg) {
        this.boxCfg = boxCfg;
    }

    public DataModel<Boksycfg> getAll() {
        return all;
    }

    public void setAll(DataModel<Boksycfg> all) {
        this.all = all;
    }

    public DataModel<Boksy> getAllBoks() {
        return allBoks;
    }

    public void setAllBoks(DataModel<Boksy> allBoks) {
        this.allBoks = allBoks;
    }

    public Boksy getBoks() {
        return boks;
    }

    public void setBoks(Boksy boks) {
        this.boks = boks;
    }

    public UploadedFile getItem() {
        return item;
    }

    public void setItem(UploadedFile item) {
        this.item = item;
    }
}