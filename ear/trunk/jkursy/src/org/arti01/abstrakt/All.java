package org.arti01.abstrakt;

import java.util.List;

import org.arti01.entit.Statyczne;

@SuppressWarnings("serial")
public abstract class All extends Akcja {
	private List<Statyczne> statyczne;

	public List<Statyczne> getStatyczne() {
		return statyczne;
	}

	public void setStatyczne(List<Statyczne> statyczne) {
		this.statyczne = statyczne;
	}



}
