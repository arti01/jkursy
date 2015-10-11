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
        String klucz = "test";
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
        System.out.println("podmienWgPliku");
        String plikProp = "/home/arti01/tmp/nowe.prop";
        PodmienWplikach instance = new PodmienWplikach(new CzytajConfig().doList(new CzytajConfig().getConfig().getProperty("plikiProperties")));
        instance.podmienWgPliku(plikProp);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
