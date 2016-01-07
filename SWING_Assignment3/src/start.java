public class start 
{
	public static void main(String[]args)
	{
		
		RedBlackTree<Integer, Integer> RB = new RedBlackTree<>();
		
		RB.insert(20, 20);
		RB.insert(25, 25);
		RB.insert(5, 5);
		RB.insert(24, 24);
		RB.insert(29, 29);
		RB.insert(7, 7);
		RB.insert(3, 3);
		RB.insert(30, 30);
//		
		String udrawText = "[";
		if(RB.m_Root != null)
		udrawText += "l(\"" + RB.m_Root.m_Key+ "\", n(\"node\", [ a(\"OBJECT\", \""+ RB.m_Root.m_Data + "\")], [";
//		
		if(RB.m_Root.m_Left != null)
			udrawText += goDownTree(RB.m_Root.m_Left, RB.m_Root);
		
		if(RB.m_Root.m_Right != null) {
			if(RB.m_Root.m_Left != null)
				udrawText += ",";
			udrawText += goDownTree(RB.m_Root.m_Right, RB.m_Root);
		}
		udrawText += "]))]";
//		
		System.out.println(udrawText);
//		
//		
		
//		PatriciaTree PT = new PatriciaTree();
//		
//		PT.insert('T');
//		PT.insert('E');
//		PT.insert('S');
//		PT.insert('T');
//		

		
	}
	
	
	private static String goDownTree(RedBlackTree.Node go, RedBlackTree.Node parent)
	{
		String udrawText;
		udrawText = "l(\"" + parent.m_Key + "->" + go.m_Key + "\", e(\"edge\", [a(\"OBJECT\", \"\"), a(\"EDGECOLOR\", \""+(go.m_bIsRed ? "#FF0000" : "#000000" )+"\")],";
		
		udrawText += "l(\"" + go.m_Key+ "\", n(\"node\", [ a(\"OBJECT\", \""+ go.m_Data + "\")], [";

		if(go.m_Left != null)
			udrawText += goDownTree(go.m_Left, go);
		
		if(go.m_Right != null) {
			if(go.m_Left != null)
				udrawText += ",";
			udrawText += goDownTree(go.m_Right, go);
		}
		udrawText += "]))))";
		
		return udrawText;
	}

}
