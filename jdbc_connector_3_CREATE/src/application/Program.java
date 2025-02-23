package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "Gustavo Maia");
			ps.setString(2, "gustavo@gmail.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("08/04/1995").getTime()));
			ps.setDouble(4, 915.00);
			ps.setInt(5, 1);
			
			Integer rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					Integer id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No rows affected.");
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
		

	}

}
