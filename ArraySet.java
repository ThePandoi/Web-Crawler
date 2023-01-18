import java.util.*;
import java.io.*;
/**
 * class that provides the skeleton of the ArraySet by utilizing an ArrayList.
 * @author Andy Pan
 */
public class ArraySet<T extends Comparable<T>> implements Iterable<T>{
	/**
	 * private data member list of ArrayList.
	 */
	private ArrayList<T> list;
	/**
	 * private inner class for the Iterator of the set.
	 */
	private class SetIterator implements Iterator<T> {
		/**
		 * private inner data member index.
		 */
		private int index;
		/**
		 * constructor for SetIterator.
		 */
		public SetIterator() {
			index = 0;
		}
		/**
		 * method that checks if the list has another value.
		 * @return boolean that confirms if the list has a next value
		 */
		public boolean hasNext() {
			return (index < list.size());
		}
		/**
		 * method that returns the next value in the list.
		 * @return next value in list
		 */
		public T next() {
			index++;
			return list.get(index-1);
		}
	}
	/**
	 * constructor for ArraySet that initializes the private data member.
	 */
	public ArraySet() {
		list = new ArrayList<T>();
	}
	/**
	 * method that returns the size of the list.
	 * @return size of list
	 */
	public int size() {
		return list.size();
	}
	/**
	 * method that returns list as an ArrayList for possible utilization in other methods.
	 * @return ArrayList
	 */
	public List<T> asList() {
		return list;
	}
	/**
	 * method that returns a boolean value if query is in the list.
	 * @return boolean value if the query is in the list
	 */
	public boolean contains(T query) {
		if (Collections.binarySearch(list, query) < 0) {
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * method that adds an item in the list in the correct order.
	 * @param item to be added
	 * @return boolean value confirming if the add was successful or not
	 */
	public boolean add(T item) throws RuntimeException{
		if (item == null) {
			throw new RuntimeException("ArraySet does not support null items");
		}
		if (contains(item)) {
			return false;
		}
		else {
			int index = (Collections.binarySearch(list, item) + 1) * -1;
			list.add(index, item);
			return true;
		}
	}
	/**
	 * method that returns query if it is in the list.
	 * @return query if it is in the list or null if it is not
	 */
	public T get(T query) {
		if (contains(query) == true) {
			return query;
		}
		else {
			return null;
		}
	}
	/**
	 * method that returns list as a String.
	 * @return list as a String
	 */
	public String toString() {
		return ""+list;
	}
	/**
	 * method that returns an iterator of the set.
	 * @return iterator of the set
	 */
	public Iterator<T> iterator() {
		Iterator<T> iterator = new SetIterator();
		return iterator;
	}
}