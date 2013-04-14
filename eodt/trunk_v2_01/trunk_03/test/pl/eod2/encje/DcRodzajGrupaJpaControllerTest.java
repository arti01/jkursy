/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arti01
 */
public class DcRodzajGrupaJpaControllerTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public DcRodzajGrupaJpaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("eodtPU2");
        em = emf.createEntityManager();
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
     * Test of getEntityManager method, of class DcRodzajGrupaJpaController.
     */
    
    @Test
    public void testFindDcRodzajGrupa() {
        System.out.println("findDcRodzajGrupa");
        Integer id = null;
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        DcRodzajGrupa expResult = null;
        DcRodzajGrupa result = instance.findDcRodzajGrupa(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}