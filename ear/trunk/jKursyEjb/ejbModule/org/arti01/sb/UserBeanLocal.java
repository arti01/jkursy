package org.arti01.sb;
import javax.ejb.Local;

@Local
public interface UserBeanLocal {
	public String test(String test);
}
