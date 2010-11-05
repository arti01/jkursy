package org.arti01.abstrakt;

import java.util.List;
import org.arti01.obiekty.Statyczne;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class All extends ActionSupport {
	private List<Statyczne> statyczne;

	public List<Statyczne> getStatyczne() {
		return statyczne;
	}

	public void setStatyczne(List<Statyczne> statyczne) {
		this.statyczne = statyczne;
	}



}
