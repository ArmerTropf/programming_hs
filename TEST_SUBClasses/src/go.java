


public class go
{
	public static void main(String[]args)
	{
		BinTree<Integer,Integer> myTree = new BinTree<>();
		
		myTree.insert(5, 5);
		myTree.m_Root.
		
	}
}




class BinTree<K extends Comparable<K>,D> 
{
	public Node m_Root = null;
	
	class Node 
	{
		public Node(K key,D data) 
		{
			m_Key = key;
			m_Data = data;
		}
		
		K m_Key;
		D m_Data;
		Node m_Left = null;
		Node m_Right = null;
	}

	public void insert(K key,D data) 
	{
		Node tmp = m_Root;
		Node father = null;
		while (tmp != null) 
		{
			father = tmp;
			tmp = (key.compareTo(tmp.m_Key) < 0) ? tmp.m_Left : tmp.m_Right;
		}
		tmp = new Node(key,data);
		if (father == null)
			m_Root = tmp;
		else if (key.compareTo(father.m_Key) < 0)
			father.m_Left = tmp;
		else
			father.m_Right = tmp;
	}
	
}
