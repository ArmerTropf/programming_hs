public interface Tree<K extends Comparable<K>, V> {

    /**
     * inserts a node with the given key and value
     * @param key key of node to be inserted into the tree
     * @param value value to be stored inside the node
     */
    boolean insert(K key, V value);

    /**
     * searches for mode with given key. returns node or null if key does not exists
     * @param key key of node to be searched for
     * @return returns found node or null if key does not exists
     */
    Node search(K key);

    /**
     * removes node with given key from tree
     * @param key key of node to be removed
     */
    boolean remove(K key);


}