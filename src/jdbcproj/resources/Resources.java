package jdbcproj.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class have access to property file with data for database. For example URL.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
public abstract class Resources {
	
	static private File file;
	static private FileInputStream inProp;
	static private Properties prop;
	
	static{
		try{
			file = new File("D:\\apache-tomcat-9.0.0.M10\\webapps\\JDBCProj\\WEB-INF\\resources\\JDBCInfo.properties");
			inProp = new FileInputStream(file);
			prop = new Properties();
			
			prop.load(inProp);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method return property from file. Else return null
	 * 
	 * @param key Key of value
	 * @return String value of property
	 * */
	public static String getProperty(String key){
		return prop.getProperty(key);
	}

	/**
	 * Method return property from file. Else return def
	 *
	 * @param key Key of value
	 * @param def Default value
	 * @return String value of property
	 * */
	public static String getProperty(String key, String def){
		return prop.getProperty(key, def);
	}
}