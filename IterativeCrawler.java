import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.*;
import java.util.*;
/**
 * class that visits all the linked pages with iteration instead of using recursion.
 * @author Andy Pan
 */
public class IterativeCrawler extends Crawler {
	/**
	 * data member representing the number of pages that are pending to be searched. 
	 */
	protected ArraySet<String> pendingPages;
	/**
	 * constructor for iterative crawler that calls parent constructor and initializes the protected data member.
	 */
	public IterativeCrawler() {
		super();
		pendingPages = new ArraySet<String>();
	}
	/**
	 * method that adds the page to pendingpages and calls crawlRemaining().
	 * @param pageFileName
	 */
	public void crawl(String pageFileName) {
		addPendingPage(pageFileName);
		crawlRemaining();
	}
	/**
	 * method that keeps iterating through linked pages as long as pendingPages has an item.
	 */
	public void crawlRemaining() {
		while (pendingPagesSize() != 0) {
			crawlNextPage();
		}
	}
	/**
	 * method that adds filename to pendingPages.
	 * @param pageFileName
	 */
	public void addPendingPage(String pageFileName) {
		pendingPages.add(pageFileName);
	}
	/**
	 * method that returns size of pendingpages.
	 * @return pendingpage size
	 */
	public int pendingPagesSize() {
		return pendingPages.size();
	}
	/**
	 * method that prints each item of pendingpages on a different line.
	 * @return pending which is the string containing the items
	 */
	public String pendingPagesString() {
		String pending = "";
		// uses iterator to iterate through the foundPages and prints a newline after each printed item
		Iterator<String> iterator = pendingPages.iterator();
		while (iterator.hasNext()) {
			pending = pending+iterator.next()+"\n";
		}
		return pending;
	}
	/**
	 * method that crawls a single page that will be retrieved and removed from pendingpages
	 */
	public void crawlNextPage() {
		try {
			// creates a filename from the last item in the list and creates a document	 
			String pageFileName = pendingPages.asList().remove(pendingPagesSize()-1);
			File input = new File(pageFileName);
 			foundPages.add(pageFileName);
 			Document doc = Jsoup.parse(input, "UTF-8");
 			// creates an arraylist of links
 			ArrayList<Element> links = doc.select("a[href]");
 			for (Element element : links) {
 				// creates a link reference
 				String linkedPage = element.attr("href"); 
 				if (validPageLink(linkedPage) == false) {
 					skippedPages.add(linkedPage);
 					continue;
 				}
 				// creates a linked file
 				String linkedFile = Util.relativeFileName(pageFileName, linkedPage); 
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
 					// otherwise, if it is valid, linkedFile will be added on to pendingPages
 					addPendingPage(linkedFile);
 				}
 			}
 		}
 		catch (Exception e) {
 		}
	}
}