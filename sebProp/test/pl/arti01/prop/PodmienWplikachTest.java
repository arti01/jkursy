/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.prop;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author arti01
 */
public class PodmienWplikachTest {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    public PodmienWplikachTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of zmienWartosci method, of class PodmienWplikach.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testZmienWartosci() throws Exception {
        System.out.println("zmienWartosci");
        String klucz = "testWartosc";
        String wartosc = "tttttt";

        PodmienWplikach instance = new PodmienWplikach(new CzytajConfig().doList(new CzytajConfig().getConfig().getProperty("plikiProperties")));
        instance.zmienWartosci(klucz, wartosc);

        //fail("The test case is a prototype.");
    }

    /**
     * Test of podmienWgPliku method, of class PodmienWplikach.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPodmienWgPliku() throws Exception {
        System.out.println("podmienWgPliku, os: "+OS);
        String plikProp="";
        if (OS.contains("linux")) {
            plikProp = "/home/arti01/tmp/nowe.prop";
        }else if (OS.contains("win")) {
            plikProp = "d:/tmp/1/test.prop";
        }
        else{
           throw new Exception("nieznany os: "+OS);
        }
        PodmienWplikach instance = new PodmienWplikach(new CzytajConfig().doList(new CzytajConfig().getConfig().getProperty("plikiProperties")));
        instance.podmienWgPliku(plikProp);
    }

}
