
public class Hashing <D>
{
	private Node<D>[] m_Entries;
	private int m_iNrOfEntries = 0;
	
	public Hashing() 
	{
		m_Entries = new Node[1023];
		 m_iNrOfEntries = 0;
	}
	
	public D search(int key) 
	{
		int iIndex = key % m_Entries.length;
		for(int i = 0; m_Entries[iIndex] != null && i < m_Entries.length; ++i) 
		{
			if (m_Entries[iIndex].m_Key==key)
				return m_Entries[iIndex].m_Data;
			iIndex = (iIndex + 1) % m_Entries.length;
		}
		return null;
	}
	
	public void insert(int key, D data) 
	{
		int iIndex = key % m_Entries.length;
		for(int i = 0; i < m_Entries.length; ++i) 
		{
			if (m_Entries[iIndex] == null) 
			{
				m_Entries[iIndex] = new Node<D>(key,data);
				++m_iNrOfEntries;
				
				if (m_iNrOfEntries > m_Entries.length *8/10)
					resize();
				return;
			}
			iIndex = (iIndex + 1) % m_Entries.length;
		}
	}
	
	private void resize() 
	{
		final int OLDCAPACITY = m_Entries.length;
		Node<D>[] oldEntries = m_Entries;
		final int iNewCap = (m_Entries.length + 1) * 2 - 1;
		m_Entries = new Node[iNewCap];
		m_iNrOfEntries = 0;
		
		for(int i = 0;i < OLDCAPACITY;++i) 
		{
			if (oldEntries[i] != null) 
			{
				insert(oldEntries[i].m_Key, oldEntries[i].m_Data);
			}
		}
	}
	
	private int hashKey(Object key,int iLength) 
	{
		if (key instanceof Integer) 
		{
			Integer i = (Integer)key;
		
			if (i.intValue() < 0)
				return -i.intValue() % iLength;
			else
				return i.intValue() % iLength;
		} 
		else if (key instanceof Character) 
		{
			Character c = (Character)key;
			return c.charValue() % iLength;
		} 
		else
			return 0;
	}
	
	
	
	class Node<D> 
	{
		private int m_Key;
		private D m_Data;
		
		public Node(int key,D data) 
		{
			m_Key = key;
			m_Data = data;
		}
		
	}
		
	

}
