package org.arti01.sesBean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import org.arti01.entit.Kursy;

@Local
public interface KursyImpRemote {

	@SuppressWarnings("unchecked")
	public abstract List<Kursy> findAll();

}