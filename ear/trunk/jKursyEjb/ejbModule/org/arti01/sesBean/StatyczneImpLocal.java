package org.arti01.sesBean;

import java.util.List;
import javax.ejb.Local;

@Local
public interface StatyczneImpLocal {

	public abstract List<Statyczne> findAll();

	public abstract void insert(Statyczne statyczne);

}