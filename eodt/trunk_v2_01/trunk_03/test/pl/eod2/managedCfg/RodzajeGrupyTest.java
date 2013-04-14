/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedCfg;

import javax.faces.model.DataModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.eod2.encje.DcRodzajGrupa;

/**
 *
 * @author arti01
 */
public class RodzajeGrupyTest {
    
    public RodzajeGrupyTest() {
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
     * Test of init method, of class RodzajeGrupy.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        RodzajeGrupy instance = new RodzajeGrupy();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refresh method, of class RodzajeGrupy.
     */
    @Test
    public void testRefresh() {
        System.out.println("refresh");
        RodzajeGrupy instance = new RodzajeGrupy();
        instance.refresh();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dodaj method, of class RodzajeGrupy.
     */
    @Test
    public void testDodaj() {
        System.out.println("dodaj");
        RodzajeGrupy instance = new RodzajeGrupy();
        instance.dodaj();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of test method, of class RodzajeGrupy.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        RodzajeGrupy instance = new RodzajeGrupy();
        instance.test();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class RodzajeGrupy.
     */
    @Test
    public void testList() {
        System.out.println("list");
        RodzajeGrupy instance = new RodzajeGrupy();
        String expResult = "";
        String result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLista method, of class RodzajeGrupy.
     */
    @Test
    public void testGetLista() {
        System.out.println("getLista");
        RodzajeGrupy instance = new RodzajeGrupy();
        DataModel expResult = null;
        DataModel result = instance.getLista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLista method, of class RodzajeGrupy.
     */
    @Test
    public void testSetLista() {
        System.out.println("setLista");
        DataModel<DcRodzajGrupa> lista = null;
        RodzajeGrupy instance = new RodzajeGrupy();
        instance.setLista(lista);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRodzajGrupa method, of class RodzajeGrupy.
     */
    @Test
    public void testGetRodzajGrupa() {
        System.out.println("getRodzajGrupa");
        RodzajeGrupy instance = new RodzajeGrupy();
        DcRodzajGrupa expResult = null;
        DcRodzajGrupa result = instance.getRodzajGrupa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRodzajGrupa method, of class RodzajeGrupy.
     */
    @Test
    public void testSetRodzajGrupa() {
        System.out.println("setRodzajGrupa");
        DcRodzajGrupa rodzajGrupa = null;
        RodzajeGrupy instance = new RodzajeGrupy();
        instance.setRodzajGrupa(rodzajGrupa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}