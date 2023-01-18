import java.io.File;
import java.net.URI;
/**
 * class that contains a method that removes extraneous symbols from filenames.
 */
public class Util{
    /**
     * static method that removes any extraneous ../ or ./ refs in filenames.
     * @param pageFileName name of the page
     * @param linkName name of the link
     */
    public static String relativeFileName(String pageFileName, String linkName){
        String canonFilename = null;
        try {
            File pageFile =  new File(pageFileName);
            File linkFile  = new File(pageFile.getParent(),linkName);
            String linkFileString = linkFile.getPath().replaceAll("\\\\","/");
            canonFilename = (new URI(linkFileString)).normalize().getPath();
            return canonFilename;
        }
        catch (Exception e) {
        String msg = "";
        msg += String.format("Could not resolve relative path\n");
        msg += String.format("page file     : %s\n",pageFileName);
        msg += String.format("link name     : %s\n",linkName);
        msg += String.format("canonical file: %s\n",canonFilename);
        msg += String.format("work dir      : %s\n",System.getProperty("user.dir"));
        throw new RuntimeException(msg);
        }
    }
}