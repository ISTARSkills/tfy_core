/**
 * 
 */
package istarcore;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * @author ISTAR-SERVER-PU-1
 *
 */
public class CreateIndex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// CREATE INDEX ON address USING btree (id);
		HashMap<String, List<String>> tablename = new HashMap<>();
		Connection conn;

		Statement st;
		// Step 1: Load the JDBC driver.
		try {
			Class.forName("org.postgresql.Driver");
			// //ViksitLogger.logMSG(this.getClass().getName(),"Driver Loaded.");
			// Step 2: Establish the connection to the database.
			String url = "jdbc:postgresql://elt.talentify.in:5432/talentify";

			conn = DriverManager.getConnection(url, "postgres", "4a626021-e55a");
			// //ViksitLogger.logMSG(this.getClass().getName(),"Got Connection.");

			st = conn.createStatement();
			String[] types = { "TABLE"};
			Vector<String> columnNames = new Vector<String>();

			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", types);
			while (rs.next()) {
				// //ViksitLogger.logMSG(this.getClass().getName(),rs.getString("TABLE_NAME"));
				String tableName = rs.getString("TABLE_NAME");
				tablename.put(tableName, getAllColumns(tableName));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static List<String> getAllColumns(String tableName) throws SQLException, ClassNotFoundException {
		String sql = "select * from "+tableName;
		Connection conn;

		Statement stmt = null;
		Class.forName("org.postgresql.Driver");
		// //ViksitLogger.logMSG(this.getClass().getName(),"Driver Loaded.");
		String url = "jdbc:postgresql://elt.talentify.in:5432/talentify";

		conn = DriverManager.getConnection(url, "postgres", "4a626021-e55a");
		// //ViksitLogger.logMSG(this.getClass().getName(),"Got Connection.");

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i =0; i< rsmd.getColumnCount()-1; i++) {
			try {	
				String key = rsmd.getColumnName(i);
				String string ="CREATE INDEX ON "+tableName+" USING btree ("+key+");";	
				executesql(string);
			//ViksitLogger.logMSG(this.getClass().getName(),(string);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return null;
	}

	private static void executesql(String string) throws ClassNotFoundException, SQLException {
		Connection conn;

		Statement stmt = null;
		Class.forName("org.postgresql.Driver");
		// //ViksitLogger.logMSG(this.getClass().getName(),"Driver Loaded.");
		String url = "jdbc:postgresql://elt.talentify.in:5432/talentify";

		conn = DriverManager.getConnection(url, "postgres", "4a626021-e55a");		
		stmt = conn.createStatement();

		int rs = stmt.executeUpdate(string);
		//ViksitLogger.logMSG(this.getClass().getName(),("status ->"+ stmt.executeUpdate(string));
	}
}
