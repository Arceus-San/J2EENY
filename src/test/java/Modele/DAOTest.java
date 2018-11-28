/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime
 */
public class DAOTest {
    
    private DAO myDAO; // L'objet à tester
    private DataSource myDataSource; // La source de données à utiliser
    
    @Before
    public void setUp() {
        myDataSource = DataSourceFactory.getDataSource();
	myDAO = new DAO(myDataSource);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of listeClients method, of class DAO.
     * @throws Modele.DAOException
     */
    @Test
    public void testListeClients() throws DAOException {
        List<CustomerEntity> clients = myDAO.listeClients();
        assertEquals(13, clients.size());
    }
    
    /**
     * Test of listeClients method, of class DAO.
     * @throws Modele.DAOException
     */
    @Test
    public void testmapProductCode() throws DAOException {
        HashMap<String,Float> clients = myDAO.mapProductCode();
        assertEquals(9987.5f,clients.get("BK"),0.0f);
        assertEquals(3065.05f,clients.get("CB"),0.0f);
    }

    
}