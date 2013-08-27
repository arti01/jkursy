/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.wydruki;

import java.io.StringReader;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FopFactory;


@ManagedBean(name = "wydruk")
@SessionScoped
public class Wydruk {
    private String test;
    private FopFactory fopFactory = FopFactory.newInstance();

    @PostConstruct
    public void init(){
        String myString="ddddddddddd";
        Source src = new StreamSource(new StringReader(myString));
        test="test wydrukow";
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

}
