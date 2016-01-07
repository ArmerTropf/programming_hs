import java.awt.*;
import java.util.ArrayList;

public class TD234TermBuilderAdapter implements TermBuilderAdapter<RedBlackTree.RBNode> {
    @Override
    public ArrayList<RedBlackTree.RBNode> getChildren(RedBlackTree.RBNode node) {
        ArrayList<RedBlackTree.RBNode> list = new ArrayList<>(2);
        if(node.getLeft() != null) {
            if(node.getLeft().red) {
                if(node.getLeft().getLeft() != null)
                    list.add(node.getLeft().getLeft());
                if(node.getLeft().getRight() != null)
                    list.add(node.getLeft().getRight());
            } else {
                list.add(node.getLeft());
            }
        }
        if(node.getRight() != null) {
            if(node.getRight().red) {
                if(node.getRight().getLeft() != null)
                    list.add(node.getRight().getLeft());
                if(node.getRight().getRight() != null)
                    list.add(node.getRight().getRight());
            } else {
                list.add(node.getRight());
            }
        }
        return list;
    }

    @Override
    public String getLabel(RedBlackTree.RBNode node) {
        String label = "";
        if(node.getLeft() != null && node.getLeft().red) {
            label+= node.getLeft().getKey().toString() + " ";
        }
        label+= node.getKey().toString();
        if(node.getRight() != null && node.getRight().red) {
            label+= " " + node.getRight().getKey().toString();
        }
        return label;
    }

    @Override
    public String getEdgeLabel(RedBlackTree.RBNode node) {
        return "";
    }

    @Override
    public Shape getShape(RedBlackTree.RBNode node) {
        if((node.getLeft() != null && node.getLeft().red) || (node.getRight() != null && node.getRight().red))
            return Shape.ELLIPSE;
        else
            return Shape.CIRCLE;
    }

    @Override
    public Color getEdgeColor(RedBlackTree.RBNode node) {
        return Color.black;
    }
}