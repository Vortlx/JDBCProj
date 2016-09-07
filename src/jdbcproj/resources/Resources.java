package jdbcproj.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class have access to property file with data for database. For example URL.
 * 
 * @author Lebedev Alexander
 * @since 2016-09-03
 * */
public abstract class Resources {
	
	static private File file;
	static private FileInputStream inProp;
	static private Properties prop;
	
	static{
		try{
			file = new File("./resources/JDBCInfo.properties");
			inProp = new FileInputStream(file);
			prop = new Properties();
			
			prop.load(inProp);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method return property from file.
	 * 
	 * @param key Key of value
	 * @return String value of property
	 * */
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
}
