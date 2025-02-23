package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DB.getConnection();
			ps = conn
					.prepareStatement("UPDATE seller " + "SET BaseSalary = BaseSalary + ? " + "WHERE " + "(Email = ?)");

			ps.setDouble(1, 7000.00);
			ps.setString(2, "gustavo@gmail.com");

			Integer rowsAffected = ps.executeUpdate();

			System.out.println("Done! Rows Affected: " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}

	}

}
