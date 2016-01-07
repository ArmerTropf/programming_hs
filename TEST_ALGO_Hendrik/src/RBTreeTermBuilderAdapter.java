import java.awt.*;
import java.util.ArrayList;

public class RBTreeTermBuilderAdapter implements TermBuilderAdapter<RedBlackTree.RBNode> {
    @Override
    public ArrayList<RedBlackTree.RBNode> getChildren(RedBlackTree.RBNode node) {
        ArrayList<RedBlackTree.RBNode> list = new ArrayList<>(2);
        if(node.getLeft() != null)
            list.add(node.getLeft());
        if(node.getRight() != null)
            list.add(node.getRight());

        return list;
    }

    @Override
    public String getLabel(RedBlackTree.RBNode node) {
        return node.key.toString();
    }

    @Override
    public String getEdgeLabel(RedBlackTree.RBNode node) {
        return "";
    }

    @Override
    public Shape getShape(RedBlackTree.RBNode node) {
        return Shape.CIRCLE;
    }

    @Override
    public Color getEdgeColor(RedBlackTree.RBNode node) {
        return node.red ? Color.red : Color.black;
    }
}