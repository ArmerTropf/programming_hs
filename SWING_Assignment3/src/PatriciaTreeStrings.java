public class PatriciaTreeStrings 
{
		private Node m_Root;
		
		public Node getM_Root() 
		{
			return m_Root;
		}
		
		class Node 
		{
			public String m_Key;
			public int m_BitPos;
			public Node m_Left;
			public Node m_Right;
						
			public Node(String key,int bitPos,Node successor) 
			{
				m_Key = key;
				m_BitPos = bitPos;
				
				boolean bIsLeft = left(key,bitPos);
				
				m_Left = bIsLeft ? this : successor;
				m_Right = bIsLeft ? successor : this;
			}
			
			public Node(String key,int bitPos) 
			{
				this(key,bitPos,null);
			}
			
		}
		
		static boolean left(String key,int bitPos) 
		{
	        int charPos = (int) Math.floor(bitPos / 8);
	        int bit = bitPos - charPos * 8;
	        char singleChar;
	
	        if(charPos < key.length())
	            singleChar = key.charAt(charPos);
	        else
	            singleChar = 0;
	
	        return (singleChar & (1 << bit)) == 0;
	    }
		
		
		public boolean search(String s) 
		{
			NodeHandler h = new NodeHandler(m_Root);
			h.search(s);
			return !h.isNull() && h.node(h.NODE).m_Key == s;
		}
		
		public boolean insert(String s) 
		{
			NodeHandler h = new NodeHandler(m_Root);
			h.search(s);
			int index = 0;
			
			if (h.isNull()) 
			{
				if (h.node(h.DAD) != null) 
				{
					index = h.node(h.DAD).m_BitPos + 1;
				}
			} 
			else if (h.node(h.NODE).m_Key != s) 
			{
				while (left(s,index) == left(h.node(h.NODE).m_Key,index))
					++index;
			} 
			else 
			{
				// already inserted
				return false;
			}
			
			h = new NodeHandler(m_Root);
			h.search(s,index);
			h.set(new Node(s, index, h.node(h.NODE)), h.NODE);
			
			return true;
		}
		boolean remove(String s) 
		{
			NodeHandler h = new NodeHandler(m_Root);
			h.search(s);
			
			if (h.isNull() || h.node(h.NODE).m_Key != s) 
			{
				return false;
			} 
			else 
			{
				NodeHandler h2 = new NodeHandler(h.node(h.DAD));
				h2.search(h.node(h.DAD).m_Key);
				h.node(h.NODE).m_Key = h.node(h.DAD).m_Key;
				h2.set(h.node(h.NODE),h2.NODE);
				h.set(h.brother(h.NODE),h.DAD);
			}
			
			return true;
		}
	
	    public Node getRoot() {
	        return m_Root;
	    }
		
		class NodeHandler {
			public final int NODE = 0;
			public final int DAD = 1;
			private Object[] m_Nodes = new Object[3];
			NodeHandler(Node n) {
				m_Nodes[NODE] = n;
			}
			
			Node brother(int kind) {
				Node dad = node(kind+1);
				Node node = node(kind);
				return dad.m_Left == node ? dad.m_Right : dad.m_Left;
			}
			
			void down(boolean left) {
				for(int i = m_Nodes.length-1;i >0;--i)
					m_Nodes[i] = m_Nodes[i-1];
				m_Nodes[NODE] = left ? node(DAD).m_Left : node(DAD).m_Right;
			}
			
			boolean isNull() {
				return m_Nodes[NODE] == null;
			}
			
			Node node(int kind) {
				return (Node)m_Nodes[kind];
			}
			
			void set(Node n,int kind) {
				if (node(kind+1) == null)
					m_Root = n;
				else if ( node(kind) != null ? node(kind+1).m_Left == node(kind) : left(n.m_Key,node(kind+1).m_BitPos)) node(kind+1).m_Left = n;
				else
					node(kind+1).m_Right = n;
				m_Nodes[kind] = n;
			}
			
			void search(String s,int maxPos) {
				int lastBitPos = -1;
				while ( !isNull() && lastBitPos < node(NODE).m_BitPos && maxPos > node(NODE).m_BitPos) {
					lastBitPos = node(NODE).m_BitPos;
					down(left(s,lastBitPos));
				}
			}
			void search(String s) {
				search(s,Integer.MAX_VALUE);
			}
		
		}
	}
