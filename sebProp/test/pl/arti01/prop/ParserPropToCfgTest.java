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
public class ParserPropToCfgTest {

    private static final String OS = System.getProperty("os.name").toLowerCase();

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
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testParsuj() throws Exception {
        System.out.println("parsujWgPliku, os: " + OS);
        String plikProp = "";
        String plikCfgWz = "";
        String plikCfg = "";
        if (OS.contains("linux")) {
            plikProp = "/home/arti01/tmp/test.prop";
            plikCfgWz = "/home/arti01/tmp/test_wzor.cfg";
            plikCfg = "/home/arti01/tmp/test.cfg";
        } else if (OS.contains("win")) {
            plikProp = "d:/tmp/1/test.prop";
            plikCfgWz = "d:/tmp/1/test_wzor.cfg";
            plikCfg = "d:/tmp/1/test.cfg";
        } else {
            throw new Exception("nieznany os: " + OS);
        }
        ParserPropToCfg.Parsuj(plikCfgWz, plikProp, plikCfg);
    }

}
