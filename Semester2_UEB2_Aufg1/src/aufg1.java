
public class aufg1
{
	public static void main(String[]args)
	{
		
		SingleList <Integer> bums = new SingleList<Integer>();
		
		bums.add(new Integer(2));
		bums.add(new Integer(55));
		bums.add(new Integer(66));
		bums.push_front(new Integer(77));
		bums.print();	
		System.out.println();

//		bums.delete(66);
//		bums.delete(2);
//		bums.delete(55);
//		bums.print();
		
		
	}
}

class SingleList <T> implements SimpleCollection<T>
{


	private ListElem m_Head,m_Tail;

	
	
	SingleList()
	{
		m_Head = null;
		m_Tail = null;
	}
	
	
	public void resize()
	{
		
	}
	
	public int size()
	{
		int mySizeCounter = 0;
		for(ListElem elem = m_Head; elem != null;elem = elem.getNext())
		{
			++mySizeCounter;		
		}
		return mySizeCounter;
	}
	
	public void push_back(T arg)
	{
		
	}
	
	public void push_front(T arg)
	{
		add(arg);
	}
	
//	T get (int i);
//	{
//		return 
//	}
	
	public void set (int i, T arg)
	{
		
	}

	public void add(T obj) 
	{
		m_Head = new ListElem(obj, m_Head, m_Tail);
	}
	
	public void print()
	{
		ListElem myElem = m_Head;
				
		if (myElem != null )
		{
			for(ListElem elem = m_Head; elem != null;elem = elem.getNext())
			{
				System.out.println("ELEMENT "+ elem);
				System.out.println("Vorgänger "+ elem.m_Prev);
				System.out.println(elem.getElement() + "\t");
				System.out.println("Nachfolger "+ elem.m_Next);
				System.out.println();
			}
		}
		
	}
	
	public void delete(int myInt)
	{
		for (ListElem myElement = m_Head; myElement != null ;myElement = myElement.getNext())
		{
			System.out.println(myElement.getElement() + "   myInt " + myInt);
			if ( myElement.getNext().equals(myInt))
			{
//				System.out.println("Aktuelle "+myElement.getElement());
//				System.out.println("Nächste "+myElement.getNext().getNext().getElement());
				myElement.m_Next = myElement.getNext().m_Next;
				return;
			}
//			else
//				System.out.println("Element nicht gefunden");

		}	
	}
	
	class ListElem
	{
		private ListElem m_Next;
		private ListElem m_Prev;
		private T m_Elem;
		
		
		public ListElem(T obj, ListElem next, ListElem prev)
		{
			m_Next = next;
			m_Elem = obj;
			
			if (m_Next != null)
				m_Next.setPrev(this);
			if (m_Prev != null)
				m_Prev.setNext(this);
		}
		
		public T getElement()
		{
			return (T)m_Elem;
		}
		
		public ListElem getNext()
		{
			return m_Next;
		}
		
		public ListElem getPrev() 
		{
			return m_Prev;
		}
		
		public void setNext(ListElem g)
		{
			m_Next = g;
		}
		
		public void setPrev(ListElem g)
		{
			m_Prev = g;
		}
		
		
	}

}

interface SimpleCollection <T>
{
	void add(T arg);
	void resize();
	int size();
	void push_back(T arg);
	void push_front(T arg);
//	T get (int i);
	void set (int i, T arg);
	void delete (int i);
}

