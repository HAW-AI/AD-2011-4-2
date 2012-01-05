package redBlackTree.interfaces;

public interface Node<K extends Comparable<? super K>, V> {

	public abstract Node<K, V> grandparent();

	public abstract Node<K, V> sibling();

	public abstract Node<K, V> uncle();

}