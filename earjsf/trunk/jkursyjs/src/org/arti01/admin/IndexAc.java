package org.arti01.admin;

import org.apache.log4j.Logger;
import org.arti01.abstrakt.Akcja;

public class IndexAc extends Akcja {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(IndexAc.class);

	public String execute() throws Exception{
		return SUCCESS;
	}

}