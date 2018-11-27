package Modele;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arceus-San
 */
public class DAO {
    
    protected final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
        
        
        public List<CustomerEntity> listeClients() throws DAOException{
            String sql ="SELECT CUSTOMER_ID,EMAIL FROM CUSTOMER";
            List<CustomerEntity> clients = new ArrayList<>();
            
             try (Connection connection = myDataSource.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql)) {
		try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) { 
			String id =rs.getString("CUSTOMER_ID");
                        String mel = rs.getString("EMAIL");
                        int code  = Integer.parseInt(id);				
			clients.add(new CustomerEntity(mel,code));
                    }
		}
                return clients;
            }
            
            catch (SQLException ex) {
		Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
		throw new DAOException(ex.getMessage());
            }    
        }
        
        
        public void supprCode(String code) throws DAOException{
            String sql ="DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE=?";
            
            try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, code);

			stmt.executeUpdate();
			}
			
		catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
            
        }
        
        public void addCode(String code, float val) throws DAOException{
            String sql ="iNSERT INTO DISCOUNT_CODE (DISCOUNT_CODE,RATE) VALUES (?,?)";
            
            try (Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, code);
                        stmt.setFloat(2, val);

			stmt.executeUpdate();
			
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
            
        }
    
}
