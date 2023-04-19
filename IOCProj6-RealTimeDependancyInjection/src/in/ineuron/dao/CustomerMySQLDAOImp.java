package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import in.ineuron.bo.CustomerBO;

public class CustomerMySQLDAOImp implements ICustomerDAO {

	private DataSource dataSource;
	
	PreparedStatement pstmt = null;
	 
	Integer count = null;

	static {
		System.out.println("CustomerMySQLDAOImpl.class file is loading...");
	}

	public CustomerMySQLDAOImp(DataSource dataSource) {
		System.out.println("CustomerMySQLDAOImp:: 1 param constructor...");
		this.dataSource = dataSource;
	}

	@Override
	public int insert(CustomerBO bo) {
		 String customerAddress = bo.getCustomerAddress();
		 String customerName = bo.getCustomerName();
		 Float interestAmt = bo.getInterestAmt();
		 Float pamt = bo.getPamt();
		 Float rate = bo.getRate();
		 Float time = bo.getTime();
         String sqlInsertQuery = "insert into customerbo values(?,?,?,?,?,?)";
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("connection established succesfully....");
			
			pstmt = connection.prepareStatement(sqlInsertQuery);
			pstmt.setString(1, customerName);
			pstmt.setString(2, customerAddress);
			pstmt.setInt(3,(int)Math.ceil(pamt) );
			pstmt.setInt(4, (int)Math.ceil(rate) );
			pstmt.setInt(5,(int)Math.ceil(time) );
			pstmt.setInt(6, (int)Math.ceil(interestAmt) );
			
			count = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

}
