/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author arti01
 */
public class DcRodzajGrupaJpaControllerTest {
    
    public DcRodzajGrupaJpaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, 
                "org.apache.naming");            
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:comp");
            ic.createSubcontext("java:comp/env");
            ic.createSubcontext("java:comp/env/jdbc");
           
            // Construct DataSource
            org.postgresql.ds.PGSimpleDataSource ds=new PGSimpleDataSource();
            ds.setServerName("127.0.0.1");
            ds.setUser("eod");
            ds.setPassword("eod");
            ds.setDatabaseName("eod_v2");
            
            ic.bind("java:comp/env/jdbc/eod_v2", ds);
        } catch (NamingException ex) {
            Logger.getLogger(DcRodzajGrupaJpaControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class DcRodzajGrupaJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        DcRodzajGrupa dcRodzajGrupa = null;
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        instance.create(dcRodzajGrupa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class DcRodzajGrupaJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        DcRodzajGrupa dcRodzajGrupa = null;
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        instance.edit(dcRodzajGrupa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class DcRodzajGrupaJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findDcRodzajGrupaEntities method, of class DcRodzajGrupaJpaController.
     */
    @Test
    public void testFindDcRodzajGrupaEntities_0args() {
        System.out.println("findDcRodzajGrupaEntities");
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        int expResult =2 ;
        List result = instance.findDcRodzajGrupaEntities();
        assertEquals(expResult, result.size());
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
    }

    /**
     * Test of findDcRodzajGrupaEntities method, of class DcRodzajGrupaJpaController.
     */
    @Test
    public void testFindDcRodzajGrupaEntities_int_int() {
        System.out.println("findDcRodzajGrupaEntities");
        int maxResults = 1;
        int firstResult = 1;
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        int expResult = 1;
        List result = instance.findDcRodzajGrupaEntities(maxResults, firstResult);
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findDcRodzajGrupa method, of class DcRodzajGrupaJpaController.
     */
    @Test
    public void testFindDcRodzajGrupa() {
        System.out.println("findDcRodzajGrupa");
        Integer id = 1;
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        DcRodzajGrupa expResult = new DcRodzajGrupa(1);
        DcRodzajGrupa result = instance.findDcRodzajGrupa(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDcRodzajGrupaCount method, of class DcRodzajGrupaJpaController.
     */
    @Test
    public void testGetDcRodzajGrupaCount() {
        System.out.println("getDcRodzajGrupaCount");
        DcRodzajGrupaJpaController instance = new DcRodzajGrupaJpaController();
        int expResult = 3;
        int result = instance.getDcRodzajGrupaCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}