package cn.applesay.tag.ext.pingying;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rsong on 12/29/17.
 */
public class AlphabetPronouce {

    private static final Logger LOGGER = Logger.getLogger(AlphabetPronouce.class.getName() );

    private static Properties props = new Properties();

    private static final AlphabetPronouce config = new AlphabetPronouce();


    private  AlphabetPronouce(){
        loadResource();
    }

    private static void loadResource(){
        ClassLoader classloader = AlphabetPronouce.class.getClassLoader();
        //InputStream is = classloader.getResourceAsStream("alphabetPronunce.properties");
        //LOGGER.log(Level.ALL,classloader.getParent().getSystemResource("/").toString());
        //System.out.println("Path is:" +classloader.getParent().getSystemResource("/").toString());

        try{
                InputStream resourceStream = classloader.getResourceAsStream("pinying/alphabetPronunce.properties");
                props.load(resourceStream);
        }catch (IOException ioe)
        {
            LOGGER.log(Level.SEVERE,"Loading Alphabet Pronunce Error! {0}", ioe.getStackTrace());
            ioe.printStackTrace(System.out);
        }
    }

    public static String getCharPronunce(char character)
    {
        character = Character.toLowerCase(character);
        return props.getProperty(String.valueOf(character));
    }
}
