package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DB.getConnection();
			ps = conn
					.prepareStatement("DELETE FROM department "
							+ "WHERE "
							+ "Id = ?");

			ps.setInt(1, 2);
			

			Integer rowsAffected = ps.executeUpdate();

			System.out.println("Done! Rows Affected: " + rowsAffected);

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}

	}

}
