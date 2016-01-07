public class BTree<K extends Comparable<K>, V> implements Tree<K, V> {

    private BNode root = null;

    @Override
    public boolean insert(K key, V value) {
        if(root != null) {
            BNode tempNode = root;

            while(tempNode != null) {
                final int comparisonResult = tempNode.getKey().compareTo(key);
                if(comparisonResult == 0)
                    return false;
                if(comparisonResult > 0) {
                    if(tempNode.left == null) {
                        tempNode.left = new BNode(key, value);
                    }
                    tempNode = tempNode.left;
                } else {
                    if(tempNode.right == null) {
                        tempNode.right = new BNode(key, value);
                    }
                    tempNode = tempNode.right;
                }
            }
        } else {
            root = new BNode(key, value);
        }
        return true;
    }

    @Override
    public BNode search(K key) {
        BNode tempNode = root;
        while(tempNode != null) {
            final int comparisonResult = tempNode.getKey().compareTo(key);
            if(comparisonResult == 0)
                return tempNode;
            tempNode = (comparisonResult > 0) ? tempNode.left : tempNode.right;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        //!!!buggy!!!
        NodeHandler nH = new NodeHandler(root);
        while(!nH.isNull()) {
            final int comparisonResult = nH.node.getKey().compareTo(key);
            if(comparisonResult == 0) {
                if(nH.node.right == null) {
                    nH.set(nH.node.left);
                } else {
                    NodeHandler nH2 = new NodeHandler(nH.node);
                    nH2.down(false);
                    while(!nH2.isNull()) {
                        nH2.down(true);
                    }
                    nH2.set(nH.node.left);
                    nH.set(nH.node.right);
                }
                return true;
            }
            nH.down(comparisonResult > 0);
        }
        return false;
    }

    public BNode getRoot() {
        return root;
    }

    class NodeHandler {
        BNode parent = null, node = null;

        private NodeHandler(BNode node) {
            this.node = node;
        }

        void down(boolean left) {
            parent = node;
            node = left ? node.left : node.right;
        }

        boolean isNull() {
            return node == null;
        }

        void set(BNode n) {
            assert n != null || node != null;
            if(parent == null)
                root = n;
            else if(node != null ? node == parent.left : n.getKey().compareTo(parent.getKey()) > 0)
                parent.left = n;
            else
                parent.right = n;
            node = n;
        }

    }

    public class BNode implements Node<K, V> {

        private K key;
        private V value;

        protected BNode left = null, right = null;

        public BNode(K key, V value) {
            this.key = key;
            this.value = value;

        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public BNode getLeft() {
            return left;
        }

        public BNode getRight() {
            return right;
        }
    }

}