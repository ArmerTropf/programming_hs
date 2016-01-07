/*Aufgabe 1:
Implementieren Sie ein Interface SimpleCollection mit den Methoden:

int size() 						// liefert die Anzahl der Elemente zurück
void push_back(T arg) 			// fügt ein neues Element am Ende ein
void push_front (T arg) 		// fügt ein neues Element am Anfang ein
T get(int i) 					// liefert das i-te Element
void set(int i, T arg) 			// setzt das i-te Element auf arg
void delete(int i) 				// löscht das i-te Element

Implementieren Sie die Vektorklasse aus der Vorlesung mittels Generics, d.h.
der Typ für die Elemente wird durch ein Generic Parameter T spezifiziert.
*/

public class aufg1
{

	public static void main(String[]args)
	{
		myVector <Integer>test = new myVector<Integer>(1,0);
			
		test.add(1);
		test.add(2);
		test.push_front("TTTT");
		test.push_back(3);
//		test.delete(1);
//		test.push_front("GGGg");
//		test.push_back(88);
//		test.delete(2);
		
//		test.push_front("1");
//		test.push_front("2");
//		test.push_front("3");
//		test.push_front("4");
//		test.add(2);
		test.delete(1);
		
		
		for (int h = 0 ; h != test.size() ; ++h)
		{
			System.out.println(test.get(h));
			
		}	
				
//		System.out.println(test.get(0));
		
		System.out.println();
		System.out.println("Anzahl der Elemente: "+ test.size());
		System.out.println("Größe des Arrays: "+ test.mObjects.length);
		System.out.println("mNextFree: "+ test.mNextFree);
	}
}

class myVector <T> implements SimpleCollection<T>
{
	private int mIncWidth;
	public  int mNextFree = 0;
	public Object[] mObjects;
	
	
	myVector(int initialCapacity,int capacityIncrement)
	{
		mIncWidth = capacityIncrement;
		mNextFree = 0;
		mObjects = new Object[initialCapacity];
	}
	
	myVector(int initialCapacity)
	{
		this(initialCapacity,0);
	}
	
	myVector()
	{
		this(1,0);
	}
	
	
	public void add(T arg)
	{
		if (mNextFree >= mObjects.length) 
		{
			resize();
		}
			mObjects[mNextFree++] = arg;
	}
	
	public void resize()
	{
		final int newSize;
		
		if (mIncWidth==0)
		{
			newSize = mObjects.length * 2;
		}
		else
		{
			newSize = mObjects.length + mIncWidth;
		}
		
		Object[] newObjects = new Object[newSize];
		
		for(int i = 0;i < mObjects.length;++i) 
		{
				newObjects[i] = mObjects[i];
		}
				mObjects = newObjects;
	}
	
	public int size()
	{
		int myElements = 0;
		for (int t = 0; t != mObjects.length ;++t)
		{
			if (mObjects[t] != null)
			{
				++myElements;
			}
		}
		return myElements;
	}
	
	public void push_back(T arg)
	{
		this.add(arg);
	}
	
	public void push_front(T arg)
	{
		if (mNextFree >= mObjects.length) 
		{
//			System.out.println("Resize PUSHFRONT");
			resize();
		}
	
		Object myNewObjects[] = new Object[mObjects.length];
		
		//An Stelle 0 das neue Element einfügen
		myNewObjects[0] = arg;
		//Ab stelle 1 alle weiteren Elemente des alten Arrays einfügen
		for (int i = 1 ; i != this.size()+1 ; ++i)
		{
			myNewObjects[i] = mObjects[i-1];
		}
		mNextFree++;
		//neues Array auf altes Array kopieren
		mObjects = myNewObjects;
	}
	
	public T get(int i)
	{
		return (T)mObjects[i];
	}
	
	public void set(int i, T arg)
	{
		mObjects[i] = arg;
	}
	
	public void delete(int i)
	{
		int myElementCount = this.size()-1; 

		Object [] myNewObjects = new Object[mObjects.length];
		
		for (int g = 0 ; g != myElementCount ; ++g)
		{
			if (g >= i)
			{
				myNewObjects[g] = mObjects[g+1];
			}
			else
			{
				myNewObjects[g] = mObjects[g];
			}			
		}
		mObjects = myNewObjects;
		mNextFree--;
		
	}
}

interface SimpleCollection <T>
{
	void add(T arg);
	void resize();
	int size();
	void push_back(T arg);
	void push_front(T arg);
	T get (int i);
	void set (int i, T arg);
	void delete (int i);
}
