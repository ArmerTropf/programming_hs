package sem2_ueb1;



public class aufg2 
{

	public static void main(String[]args)
	{
	
	}
	
}

class DoubleList<T> implements SimpleCollection<T>
{
		class ListElem<T> 
		{
			public ListElem(T obj,ListElem next,ListElem prev) 
			{
				m_Next = next;
				m_Prev = prev;
				m_Elem = obj;
				if (m_Next != null)
					m_Next.setPrev(this);
				if (m_Prev != null)
					m_Prev.setNext(this);
			}
			
		}


		public int size()
		{
			return 0;
		}
		
		public void push_back(T arg)
		{
			
		}
		
		public void push_front(T arg)
		{
			
		}
		
//		 public T get(int i)
//		 {
//			 return <T>;
//		 }
		
		public void set(int i, T arg)
		{
			
		}
		
		public void delete(int i)
		{
			
		}




}

interface SimpleCollection<T>
{
	int size();
	void push_back(T arg);
	void push_front(T arg);
//	T get(int i);
	void set(int i, T arg);
	void delete(int i);
	
}



