package jdbcproj.resources;

/**
 * This class contain constants which need for registering JDBC driver and create connection for database.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-03
 * */
public abstract class Resources {
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/jdbc_proj?user=root&password=653223";
}
