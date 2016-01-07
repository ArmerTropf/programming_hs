public interface TNode<K extends Comparable<K>, V> {
    K getKey();
    V getValue();
}