public class RedBlackTree<K extends Comparable<K>, V> implements Tree<K, V> {

    protected RBNode root = null;

    @Override
    public boolean insert(K key, V value) {
        NodeHandler nH = new NodeHandler(root);
        while(!nH.isNull()) {
            // split all 4-nodes on the way down
            if(nH.node().is4Node()) {
                nH.node().convert4Node();
                nH.split();
            }

            final int comparisonResult = key.compareTo(nH.node(nH.NODE).key);
            if(comparisonResult == 0)
                return false;

            // go down
            nH.down(comparisonResult < 0);

        }

        nH.set(new RBNode(key, value), nH.NODE, false);
        nH.split();
        root.red = false;
        return true;
    }

    @Override
    public RBNode search(K key) {
        RBNode tempNode = root;
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
        NodeHandler h = new NodeHandler(root);
        while (!h.isNull()) {
            h.join();
            final int RES = key.compareTo(h.node(h.NODE).key);
            if (RES == 0) {
                if (h.node(h.NODE).right == null) {
                    h.set(h.node(h.NODE).left,h.NODE,true);
                } else {
                    NodeHandler h2 = new NodeHandler(h);
                    h2.down(false); // go right
                    h2.join();
                    while (h2.node(h2.NODE).left != null) {
                        h2.down(true);
                        h2.join();
                    }
                    h.node(h.NODE).key = h2.node(h2.NODE).key;
                    h.node(h.NODE).value = h2.node(h2.NODE).value;
                    h2.set(h2.node(h2.NODE).right,h2.NODE,true);
                }
                if (root != null)
                    root.red = false;
                return true;
            }
            h.down(RES < 0);
        }
        return false;
    }
    public RBNode getRoot() {
        return root;
    }

    class NodeHandler {

        public final int NODE = 0, DAD = 1, GDAD = 2, GGDAD = 3;

        Object[] nodes = new Object[4];

        private NodeHandler(RBNode node) {
            nodes[NODE] = node;
        }

        void down(boolean left) {
            for (int i = nodes.length-1; i > 0; --i)
                nodes[i] = nodes[i-1];
            nodes[NODE] = left ? node(DAD).left : node(DAD).right;
        }

        RBNode node(int relation) {
            return (RBNode) nodes[relation];
        }

        RBNode node() {
            return (RBNode) nodes[NODE];
        }

        boolean isNull() {
            return nodes[NODE] == null;
        }

        public void set(RBNode node, int relation, boolean keepColor) {
            if(node(relation+1) == null)
                root = node;
            else if(node(relation) != null ?
                    node(relation+1).left == node(relation) :
                    node.key.compareTo(node(relation + 1).key) < 0) {
                node(relation+1).left = node;
            } else {
                node(relation+1).right = node;
            }
            if(keepColor && node(relation) != null && node != null)
                node.red = node(relation).red;
            nodes[relation] = node;
        }

        public void rotate(int relation) {
            RBNode dad = node(relation);
            RBNode son = node(relation-1);
            boolean sonColor = son.red;
            if(!sonColor) {
                if (son.left != null)
                    son.left.red = false;
                if (son.right != null)
                    son.right.red = false;
                dad.red = false;
                dad.left.red = true;
                dad.right.red = true;
            } else {
                son.red = dad.red;
                dad.red = sonColor;
            }
            // rotate
            if (dad.left == son) {
                // clockwise rotation
                dad.left = son.right;
                son.right = dad;
            } else {
                // counter-clockwise rotation
                dad.right = son.left;
                son.left = dad;
            }
            set(son,relation, false);
        }

        public void split() {
            RBNode dad = node(DAD);
            if (dad != null && dad.red) {
                if ( node(GDAD).key.compareTo(dad.key) < 0 !=
                        dad.key.compareTo(node(NODE).key) < 0)
                    rotate(DAD);
                rotate(GDAD);
            }
        }

        public void join() {
            if (node(NODE).is2Node()) {
                if (node(DAD) == null &&
                        node(NODE).left != null &&
                        node(NODE).left.is2Node() &&
                        node(NODE).right != null &&
                        node(NODE).right.is2Node()) {
                    node(NODE).left.red = true;
                    node(NODE).right.red = true;
                } else if (node(DAD) != null) {
                    NodeHandler nephew = getNephew();
                    if (nephew.node(DAD).red) {
                        nephew.rotate(GDAD);
                        nodes[GGDAD] = nodes[GDAD];
                        nodes[GDAD] = nephew.nodes[GDAD];
                        nephew = getNephew();
                    }
                    if (nephew.node(DAD).is2Node()) {
                        node(NODE).red = true;
                        nephew.node(DAD).red = true;
                        node(DAD).red = false;
                    } else {
                        if (!nephew.isNull() && nephew.node(NODE).red)
                            nephew.rotate(DAD);
                        nephew.rotate(GDAD);
                    }
                }
            }
        }

        NodeHandler getNephew() {
            RBNode node = node(NODE);
            RBNode dad = node(DAD);
            RBNode gDad = node(GDAD);
            RBNode brother = node == dad.left ? dad.right: dad.left;
            RBNode nephew = node == dad.left ? brother.left : brother.right;
            NodeHandler res = new NodeHandler(nephew);
            res.nodes[DAD] = brother;
            res.nodes[GDAD] = dad;
            res.nodes[GGDAD] = gDad;
            return res;
        }

        NodeHandler(NodeHandler h) {
            nodes[NODE] = h.nodes[NODE];
            nodes[DAD] = h.nodes[DAD];
            nodes[GDAD] = h.nodes[GDAD];
            nodes[GGDAD] = h.nodes[GGDAD];
        }
    }

    public class RBNode implements TNode<K, V> {

        K key;
        V value;

        boolean red = true;

        RBNode left = null, right = null;

        public RBNode(K key, V value) {
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

        public RBNode getLeft() {
            return left;
        }

        public RBNode getRight() {
            return right;
        }

        public boolean is2Node() {
            return !red && (left == null || !left.red) && (right == null || !right.red);
        }

        public boolean is4Node() {
            return (left != null && left.red) && (right != null && right.red);
        }

        protected void convert4Node() {
            left.red = false;
            right.red = false;
            red = true;
        }
    }
}