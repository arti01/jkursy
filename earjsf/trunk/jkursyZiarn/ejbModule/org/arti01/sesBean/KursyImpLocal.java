package org.arti01.sesBean;

import java.util.List;

import javax.ejb.Local;

import org.arti01.entit.Kursy;

@Local
public interface KursyImpLocal {

	public abstract List<Kursy> findNiezakonczone();
	public String Test();

}