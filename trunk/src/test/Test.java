package test;

import org.arti01.baza.Baza;
import org.arti01.obiekty.User;
import org.hibernate.Query;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("test");
		Baza baza=new Baza();
		Query query = baza.przygotuj("select r.users from Rola r");
		for(Object o:baza.select(query)){
			User u=(User) o;
			System.out.println(u.getUsername());
			
		}
	}

}
