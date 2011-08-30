package org.arti.all;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

@ManagedBean(name="indexAc")
@SessionScoped
public class IndexAc implements Serializable {
	private static final long serialVersionUID = 1L;
	Logger l = Logger.getLogger(IndexAc.class);
	
}