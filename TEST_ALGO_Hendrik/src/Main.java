import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        new MainView();

        /**
         * TEST CODE
         */
        RedBlackTree<Integer, Integer> rbtree = new RedBlackTree<>();
        BTree<Integer, Integer> btree = new BTree<>();

       
        rbtree.insert(20, 20);
        rbtree.insert(25, 25);
        rbtree.insert(5, 5);
        rbtree.insert(3, 3);
		rbtree.insert(29, 29);
		rbtree.insert(7, 7);
		rbtree.insert(30, 30);
       
		
//        for (int i = 0; i < 100; i++) {
//            final int random = (int) (Math.random() * 100);
//            rbtree.insert(random, random);
//            btree.insert(random, random);
//        }

//        TermBuilder<BTree.BNode> BtermBuilder = new TermBuilder<>(new BTreeTermBuilderAdapter(), btree.getRoot());
//
//        try {
//            UDGFileWriter fW = new UDGFileWriter("C:\\Users\\Hendrik\\Documents", "testBin.udg");
//            fW.writeTerm(BtermBuilder);
//            fW.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        TermBuilder<RedBlackTree.RBNode> RBtermBuilder = new TermBuilder<>(new RBTreeTermBuilderAdapter(), rbtree.getRoot());

        try {
            UDGFileWriter fW = new UDGFileWriter("D:\\eigene Dateien\\udraw", "testRB.udg");
            fW.writeTerm(RBtermBuilder);
            fW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        TermBuilder<RedBlackTree.RBNode> TD234Builder = new TermBuilder<>(new TD234TermBuilderAdapter(), rbtree.getRoot());
//
//        try {
//            UDGFileWriter fW = new UDGFileWriter("C:\\Users\\Hendrik\\Documents", "test234.udg");
//            fW.writeTerm(TD234Builder);
//            fW.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
    }

}