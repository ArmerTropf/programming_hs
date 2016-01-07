import java.awt.*;
import java.util.ArrayList;

/**
 * Adapter for connecting binary tree implementation to TermBuilder
 */

public class BTreeTermBuilderAdapter implements TermBuilderAdapter<BTree.BNode> {
    @Override
    public ArrayList<BTree.BNode> getChildren(BTree.BNode node) {
        ArrayList<BTree.BNode> list = new ArrayList<>(2);
        if(node.getLeft() != null)
            list.add(node.getLeft());
        if(node.getRight() != null)
            list.add(node.getRight());

        return list;
    }

    @Override
    public String getLabel(BTree.BNode node) {
        return node.getKey().toString();
    }

    @Override
    public String getEdgeLabel(BTree.BNode node) {
        return "";
    }

    @Override
    public Shape getShape(BTree.BNode node) {
        return Shape.CIRCLE;
    }

    @Override
    public Color getEdgeColor(BTree.BNode node) {
        return Color.black;
    }
}