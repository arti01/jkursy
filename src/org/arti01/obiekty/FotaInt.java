package org.arti01.obiekty;

import java.util.List;

public interface FotaInt {
	public List<Fota> findAll();

	public boolean save(Fota fota) throws Exception;//zwraca czy dodal czu usunal

	public void remove(Fota fota) throws Exception;

	public Fota find(Fota fota);


}
