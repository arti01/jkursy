package org.arti01.all;

import javax.ejb.EJB;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.arti01.entit.Statyczne;
import org.arti01.sesBean.StatyczneImp;

public class InterStat {
	Logger logger = Logger.getLogger(InterStat.class);
	@EJB StatyczneImp statyczne;
	private ListDataModel<Statyczne> statyczneModel=new ListDataModel<Statyczne>() ;

	public void setStatyczneModel(ListDataModel<Statyczne> statyczneModel) {
		this.statyczneModel = statyczneModel;
	}

	public ListDataModel<Statyczne> getStatyczneModel() {
		logger.info(statyczneModel.getRowIndex());
		if(statyczneModel.getRowIndex()==-1) statyczneModel.setWrappedData(statyczne.findAll());
		return statyczneModel;
	}

}
