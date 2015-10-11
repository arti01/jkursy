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
import static org.junit.Assert.*;

/**
 *
 * @author arti01
 */
public class ParserPropToCfgTest {
    
    public ParserPropToCfgTest() {
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
     * Test of parsuj method, of class ParserPropToCfg.
     * @throws java.lang.Exception
     */
    @Test
    public void testParsuj() throws Exception {
        System.out.println("parsuj");
        ParserPropToCfg.Parsuj("/home/arti01/tmp/test_wzor.cfg", "/home/arti01/tmp/test.prop", "/home/arti01/tmp/test.cfg");
        //instance.parsuj();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
