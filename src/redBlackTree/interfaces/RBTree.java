package redBlackTree.interfaces;

public interface RBTree<K extends Comparable<? super K>, V> {

	public static final boolean VERIFY_RBTREE = true;

	/**
	 * checks all five properties to verify the validity of the tree
	 * expensive function -> only executed if VERIFY_RBTREE is true
	 */
	public abstract void verifyProperties();

	/**
	 * searches for a given key and returns its value
	 */
	public abstract V lookup(K key);

	/**
	 * first step of insertion:
	 * insert the key as it would be inserted into an ordinary binary search tree
	 * if the key already exists, the value is replaced
	 * else a new red node is created at the insert place
	 */
	public abstract void insert(K key, V value);

	/**
	 * deletes a key-value pair
	 */
	public abstract void delete(K key);

	/**
	 * prints the tree to standard output
	 * prints the right subtree before the left subtree
	 * 	  -> the tree is displayed sideways
	 * only the keys are displayed
	 */
	public abstract void print();

}