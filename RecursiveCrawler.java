import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;
/**
 * class that recursively visits all pages linked in a collection
 * @author Andy Pan
 */
public class RecursiveCrawler extends Crawler {
	/**
	 * constructor that calls the parent constructor, which initializes the data members.
	 */
	public RecursiveCrawler() {
		super();
	}
	/**
	 * method that uses the pageFileName to traverse through the links.
	 * @param pageFileName
	 */
	public void crawl(String pageFileName) {
		try {
			File input = new File(pageFileName);
 			foundPages.add(pageFileName);
 			// create Jsoup document
 			Document doc = Jsoup.parse(input, "UTF-8");
 			ArrayList<Element> links = doc.select("a[href]");
 			for (Element element : links) {
 				// creates a link reference
 				String linkedPage = element.attr("href"); 
 				// checks if the link reference is invalid, if so it will be added to skippedPages
 				if (validPageLink(linkedPage) == false) {
 					skippedPages.add(linkedPage);
 					continue;
 				}
 				// creates a link file
 				String linkedFile = Util.relativeFileName(pageFileName, linkedPage); 
 				// checks if either foundPages or skippedPages already has it, if so, it moves on to the next item
 				if (foundPages.contains(linkedFile) || skippedPages.contains(linkedFile)) {
 					continue;
 				}
 				// if the linkedFile does not exist, it is skipped and the loop moves on to the next item in the list
 				File file = new File(linkedFile);
 				if (!(file.exists())) {
 					skippedPages.add(linkedFile);
 					continue;
 				}
 				else {
 					// otherwise, if it is valid, crawl() will be recursively called with linkedFile as parameter
 					crawl(linkedFile);
 				}
 			}
 		}
 		catch (Exception e) {
 		}
	}
}