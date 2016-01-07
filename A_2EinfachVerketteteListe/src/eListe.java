
public class eListe
{
	public static void main(String[]args)
	{
		
		SingleList <Integer> bums = new SingleList<Integer>();
		
		bums.add(new Integer(2));
		bums.add(new Integer(55));
		bums.add(new Integer(66));
		bums.print();	
		System.out.println();

		bums.delete(66);
//		bums.delete(2);
//		bums.delete(55);
		bums.print();
		
	}
}

class SingleList <T>
{


	private ListElem m_Head;

	
	
	SingleList()
	{
		m_Head = null;
	}
	
	public void add(T obj) 
	{
		m_Head = new ListElem(obj, m_Head);
	}
	
	public void print()
	{
		ListElem myElem = m_Head;
				
		if (myElem != null )
		{
			for(ListElem elem = m_Head; elem != null;elem = elem.getNext())
			{
					System.out.println(elem.getElement() + "\t");
			}
		}
		
//		
//		ListElem test = m_Head;
//		System.out.println(test.getElement());
//		System.out.println(test + " NEXT: " + test.getNext());
//		
//		test = test.getNext();
//		System.out.println(test.getElement());
//		System.out.println(test + " NEXT: " + test.getNext());
//		
//		test = test.getNext();
//		System.out.println(test.getElement());
//		System.out.println(test + " NEXT: " + test.getNext());
		
	}
	
	public void delete(T myInt)
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
		private T m_Elem;
		
		
		public ListElem(T obj, ListElem next)
		{
			m_Next = next;
			m_Elem = obj;
			
		}
		
		public T getElement()
		{
			return (T)m_Elem;
		}
		
		public ListElem getNext()
		{
			return m_Next;
		}
		
		public void setNext(ListElem g)
		{
			m_Next = g;
		}
		
		
	}

}



