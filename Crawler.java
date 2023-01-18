import java.util.*;
import java.io.*;
/**
 * abstract class that provides the backbone of the crawler.
 * @author Andy Pan
 */
public abstract class Crawler {
	/**
	 * data member that represents an array of found pages.
	 */
	protected ArraySet<String> foundPages;
    /**
     * data member that represents an array of skipped pages. 
     */
	protected ArraySet<String> skippedPages;
	/**
	 * constructor for crawler that initializes the private data members.
	 */
	public Crawler() {
		foundPages = new ArraySet<String>();
		skippedPages = new ArraySet<String>();
	}
	/**
	 * abstract method that allows the crawler to crawl through the files.
	 * @param pageFileName
	 */
	public abstract void crawl(String pageFileName);
	/**
	 * returns foundPages as an ArrayList.
	 * @return foundPages as ArrayList
	 */
	public List<String> foundPagesList() {
		return foundPages.asList();
	}
	/**
	 * returns skippedPages as an ArrayList.
	 * @return skippedPages as ArrayList
	 */
	public List<String> skippedPagesList() {
		return skippedPages.asList();
	}
	/**
	 * returns foundPages as a String.
	 * @return foundPages as String
	 */
	public String foundPagesString() {
		String founded = "";
		Iterator<String> iterator = foundPages.iterator();
		while (iterator.hasNext()) {
			founded = founded+iterator.next()+"\n";
		}
		return founded;
	}
	/**
	 * returns skippedPages as a String.
	 * @return skippedPages as String
	 */
	public String skippedPagesString() {
		String skipped = "";
		Iterator<String> iterator = skippedPages.iterator();
		while (iterator.hasNext()) {
			skipped = skipped+iterator.next()+"\n";
		}
		return skipped;
	}
	/**
	 * method that checks if the pagelink names are valid.
	 * @param pageFileName name of the link
	 * @return boolean value confirming if the filename is valid or not
	 */
	public static boolean validPageLink(String pageFileName) {
		if (pageFileName.contains("http://") || pageFileName.contains("https://") || pageFileName.contains("file://") || pageFileName.contains("javascript:")) {
			return false;
		}
		else if (pageFileName.contains(".html") || pageFileName.contains(".HTML")) {
			return true;
		}
		else if (pageFileName.contains(".jpg") || pageFileName.contains(".png") || pageFileName.contains(".jpeg")) {
			return false;
		}
		else {
			return false;
		}
	}
}