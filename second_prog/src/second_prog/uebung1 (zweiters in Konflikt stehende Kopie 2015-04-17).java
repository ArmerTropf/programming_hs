package second_prog;

public class uebung1 {

	public static void main(String[]args)
	{
		Vector <Integer> go = new Vector<Integer>(3);
//		go.push_back(1);
//		go.push_back(2);
//		go.push_back(3);
	
		System.out.println("Point: "+go.myPrivPoint);
		//go.set(0, 0);
		//go.set(1, 1);
		//go.set(2, 2);
		//go.set(2, 3);
		//go.push_front(44);
//		go.set(1, 6);
//		go.push_front(55);

		go.push_back(442);

		go.push_back(2);
		go.push_back(34);
		go.push_front(77);
		go.push_front(88);
		
		go.push_back(34);
		go.push_back(34);
		go.push_back(34);
		go.push_front(88);





//		go.set(2, 6);
//		go.delete(0);
//		go.delete(0);
//		go.delete(0);
//		go.push_front(55);
//		go.push_front(55);
		//go.delete(6);
		
		//go.set(2, 3);
		
		
		
		System.out.println(go.get(0));
		System.out.println(go.get(1));
		System.out.println(go.get(2));
		System.out.println(go.get(3));
		System.out.println(go.get(4));
		System.out.println(go.get(5));
		System.out.println(go.get(6));
		System.out.println(go.get(7));
		System.out.println(go.get(8));
		System.out.println(go.get(9));
		System.out.println(go.get(10));
		System.out.println(go.get(11));
		
		
		
		System.out.println("\nPoint: "+go.myPrivPoint);
		System.out.println(go.size());
	}
}

class Vector<T> implements SimpleCollection <T>
{
	private int myCap = 0;
	private int myInc = 0;
	private int myNextFree = 0;
	public int myPrivPoint = 0;
	private Object [] myObj;


	
	Vector(int iniCap, int iniInc)
	{  
		myCap = iniCap;
		myInc = iniInc;
		
		myObj =  new Object[iniCap];
		if (myInc == 0 )
			myInc = myCap;
	}
	Vector(int iniCap)
	{
		this(iniCap, 0);
	}

//SIZE
	public int size()
	{
		int myElements = 0;
		for (int t = 0; t != myObj.length ;++t)
		{
			if (myObj[t] != null)
			{
				++myElements;
			}
		}
		return myElements;
		
		//return myNextFree;	
	}

//BACK
	public void push_back(T arg)
	{
		
		if (myNextFree >= myObj.length)
		{
			resize();
		}
		
		myObj[myNextFree++] = arg;
		
	}

//FRONT
	public void push_front(T arg)
	{
		if (myPrivPoint < 0)
		{
			resize_front();
		}		
		
		myObj[myPrivPoint--] = arg;	
		 
		for (int g = myPrivPoint ; g >= 0 ; --g)
		{
			if ( myObj[g] == null && g >= 0 )
			{	
				myPrivPoint = g;
				break;
			}
		}
		
		
		
	}
	public T get(int i)
	{
	
		
		return (T)myObj[i];
		
	}
	
//	public T get2(int i)
//	{
//		//for (int k = 0 )	
//	}
	
//SET
	public void set(int i, T arg)
	{

		if (size() > i)
		{
			myObj[i] = arg;
			
			
			if (i+1 >= myNextFree && i+1 <= myObj.length-1 )
			{
				myNextFree = i+1;
			}
			
			//Priv Pointer setzen wegen dem Einfügen für PushFront
			for (int g = 0 ; g != myObj.length ; ++g)
			{
				if ( myObj[g] != null && myPrivPoint > 0)
				{	
					myPrivPoint = g-1;
					break;
				}
				else
					myPrivPoint = myNextFree;
			}
		}
		
		
		
		
		
		
	
	}

//DELETE
	public void delete(int i)
	{
	
		for (int g = i ; g < myObj.length-1 ; ++g)
		{
			myObj[g] = myObj[g+1]; 
		}
		
		//myobj[i] = 
	}
	
//RES_FRONT
	public void resize_front()
	{
		
		Object [] myNewArrayObj = new Object[myObj.length+myInc];
		
		for (int i = 0 ; i + myInc != myNewArrayObj.length ; ++i)
		{
			if (myObj[i]  != null)
			{	
				myNewArrayObj[i + myInc] = myObj[i];
				myNextFree = (i + myInc) + 1;
			}
			
		}
		myPrivPoint = myInc-1;
		myObj = (T[])myNewArrayObj;
		myInc = myObj.length;
		
	}
	
	public void resize()
	{
		
		Object [] myNewArrayObj = new Object[myObj.length+myInc];
		for (int i = 0 ; i != myObj.length ; ++i)
		{
			myNewArrayObj[i] = myObj[i];
			
		}
		myObj = (T[])myNewArrayObj;
		myInc = myObj.length;
		
	}
	
	
}

interface SimpleCollection<T>
{
	int size();
	void push_back(T arg);
	void push_front(T arg);
	T get(int i);
//	int get(int i);
	void set(int i, T arg);
	void delete(int i);
	
	
	
}
