/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Properties;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

//@SessionScoped
//@Named
public class FileUploadBean implements Serializable{
//private ArrayList<UploadedImage> files = new ArrayList<UploadedImage>();
 
    /*public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData());
        stream.close();
    }*/
 
    public void listenerLadujPlik(FileUploadEvent event) throws Exception {
        Properties plikMapy=new Properties();
        UploadedFile item = event.getUploadedFile();
        ByteArrayInputStream in = new ByteArrayInputStream(item.getData());
        plikMapy.load(in);
        /*UploadedFile item = event.getUploadedFile();
        UploadedImage file = new UploadedImage();
        file.setLength(item.getData().length);
        file.setName(item.getName());
        file.setData(item.getData());
        files.add(file);*/
    }
 
    /*public String clearUploadData() {
        files.clear();
        return null;
    }*/
 
    /*public int getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }*/
 
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }
 /*
    public ArrayList<UploadedImage> getFiles() {
        return files;
    }
 
    public void setFiles(ArrayList<UploadedImage> files) {
        this.files = files;
    }    */
}
