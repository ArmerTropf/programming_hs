import java.util.ArrayList;

public class TermBuilder<N> {

    private ArrayList<N> nodes; //TODO: implement references
    private TermBuilderAdapter<N> adapter;
    private String term = "";

    /**
     * Builds a uDraw term representation of a given tree
     * @param adapter passes a TermBuilderAdapter
     * @param root the root of the tree
     */
    public TermBuilder(TermBuilderAdapter<N> adapter, N root) {
        this.adapter = adapter;

        term = "[";
        if(root != null)
            term+= convertTree(root);
        term+= "]";
    }

    /**
     * get string of term
     * @return string of term
     */
    public String getTerm() {
        return term;
    }

    /**
     * converts a node into uDraw term representation and calls convertChild for each child-node
     * @param node root of (sub)tree
     * @return string of term
     */
    private String convertTree(N node) {
        String term;

        // node
        term = "l(\""+node.hashCode()+"\", n(\"node\", ";
        // node-attributes
        term+= "[ a(\"OBJECT\", \"" + adapter.getLabel(node) + "\")," +
                " a(\"_GO\", \"" + adapter.getShape(node) + "\") ],";
        // begin list of edges/children
        term+= "[";

        // children
        for (N child : adapter.getChildren(node)) {
            term+= convertChild(node, child);
            term+= ",";
        }
        // strip last comma
        if(!adapter.getChildren(node).isEmpty())
            term = term.substring(0, term.length()-1);

        // close all the braces
        term+= "]))";
        return term;
    }

    /**
     * converts a node into uDraw term representation of its edge to its parent and calls
     * indirect recursive convertTree to create its node and child-nodes
     * @param parent parent-node of given node
     * @param node node
     * @return string of term
     */
    private String convertChild(N parent, N node) {
        String term;

        // edge to parent
        term = "l(\"" + parent.hashCode() + "->" + node.hashCode() + "\", e(\"edge\", ";
        // edge-attributes
        String color = Integer.toHexString(adapter.getEdgeColor(node).getRGB()).substring(2);
        term+= "[ a(\"OBJECT\", \"" + adapter.getEdgeLabel(node) + "\")," +
                " a(\"EDGECOLOR\", \"#" + color + "\") ],";

        // convert subtree
        term+= convertTree(node);

        // close all the braces
        term+= "))";

        return term;
    }
}