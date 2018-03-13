package com.knockout.bartender.resources;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;



/**
 */
/**
 * @author Bueka
 *
 */
public class Config {
    private static final String CONFIGURATION_FILE = "classes/META-INF/resources/WEB-INF/config.properties";
    private static Logger log = Logger.getLogger(Config.class); 
    private static Properties properties = null;

  static {
        try {

        init();

        } catch (Exception ex) {

            System.err.println("Config:It is not able to open file properties: "+CONFIGURATION_FILE+": "+ex);
            properties=null;
        }

  }



    public Config()
    {

    }

    /**
     * Read the file and start the class
     */
    public static void init(){
       try{
       	if (properties==null) {
	       		 File file = new File(CONFIGURATION_FILE);
	       		 if(file!=null){
	       			log.debug("Config:Loading File properties:"+file.getAbsolutePath());
	       			InputStream inputStream = new FileInputStream(file);
	       			properties = new Properties();
		         	// InputStream inputStream = file.toURL().openStream();
		             properties.load(inputStream);

		             inputStream.close();
	       		 }
        
	         }else
	         	System.err.println("Config:It is not able to find File properties: ["+CONFIGURATION_FILE+"]");
      }
      catch(Exception e){
        System.out.println("Config: It is not able to open File propertiess: "+e.getMessage());
        e.printStackTrace();
        properties = null;
      }

    }

 
    /**
    *  Get the value of a property
    *  @param group Group Properties name
    *  @param propertie Name property 
    *  @return String property value
    */
    public static String value(String grupo,String propiedad){
        if(properties == null)
            init();
        String valor=properties.getProperty(grupo+"."+propiedad, "");

        return valor;
    }
    
}