
import java.util.ArrayList;
//
//Name        : IteratorPattern.java
//
//Iterator pattern:
//
//Provide a way to access the elements of an aggregate object
//sequentially without exposing its underlying representation.
//
//The classes and/or objects participating in this pattern are:

//1. Iterator  (AbstractIterator)
//		defines an interface for accessing and traversing elements.
//2. ConcreteIterator  (Iterator)
//		implements the Iterator interface.
//		keeps track of the current position in the traversal of the aggregate.
//3. Aggregate  (AbstractCollection)
//		defines an interface for creating an Iterator object
//4. ConcreteAggregate  (Collection)
//		implements the Iterator creation interface to return an instance of the proper ConcreteIterator
//

 class IteratorPattern
{
	static void printAggregate(AbstractIterator i)
	{
		System.out.println("Iterating over collection:");
		for (i.First(); !i.IsDone(); i.Next())
		{
			System.out.println(i.CurrentItem().getName());
		}
		System.out.println();
	}

	/*public static void main(String[] args)
	{
		// Create Aggregate.
		AbstractCollection aggregate = new WorkOrderCollection();
		aggregate.add(new Item("Item 0"));
		aggregate.add(new Item("Item 1"));
		aggregate.add(new Item("Item 2"));
		aggregate.add(new Item("Item 3"));
		aggregate.add(new Item("Item 4"));
		aggregate.add(new Item("Item 5"));
		aggregate.add(new Item("Item 6"));
		aggregate.add(new Item("Item 7"));
		aggregate.add(new Item("Item 8"));

		// Create Iterator
		AbstractIterator iterator = aggregate.CreateIterator();
		// Traverse the Aggregate.
		printAggregate(iterator);
	}*/
}


//
//This is the abstract "Iterator".
//		AbstractIterator
//

interface AbstractIterator
{
	void First();

	void Next();

	Boolean IsDone();

	WorkOrder CurrentItem();
}

//
//This is the "concrete" Iterator for collection.
//		CollectionIterator
//

class WorkOrderCollectionIterator extends Observer implements AbstractIterator
{
	public void First()
	{
		_current = 0;
	}

	public void Next()
	{
		_current++;
	}

	public WorkOrder CurrentItem()
	{
		return (IsDone() ? null : _collection.get(_current));
	}

	public Boolean IsDone()
	{
		return _current >= _collection.getCount();
	}

	public WorkOrderCollectionIterator(WorkOrderCollection collection)
	{
		_collection = collection;
		_current = 0;
	}




	@Override
	public void update(Subject subject) {
		_subject = subject;
		_subject_name = subject.getName();
		_subject_status = subject.getState();
		if(_subject_status == 1){
			System.out.println("Subject:"+subject.getName()+"\n"+"State: Complete");
		}else{
			System.out.println("Subject:"+subject.getName()+"\n"+"State: Not Complete");
		}

	}

	private String clientName;
	private Subject _subject;
	private  String _subject_name;
	private int _subject_status;

	public WorkOrderCollectionIterator(String name){
		clientName = name;
	}
	public Subject getSubject(){return _subject;}
	public void setSubject(Subject subject){_subject = subject;}
	public String getClientName(){return clientName;}

	private WorkOrderCollection _collection;
	private int _current;


};

//
//This is the abstract "Aggregate".
//			AbstractAggregate
//

interface AbstractCollection
{
	public AbstractIterator CreateIterator();

	public void add(WorkOrder wo); // Not needed for iteration.

	public int getCount(); // Needed for iteration.

	public WorkOrder get(int idx); // Needed for iteration.
};

//
//This is the concrete Aggregate.
//			Collection
//

class WorkOrderCollection implements AbstractCollection
{
	private ArrayList<WorkOrder> workorders = new ArrayList<WorkOrder>();

	public WorkOrderCollectionIterator CreateIterator()
	{
		return new WorkOrderCollectionIterator(this);
	}

	public int getCount()
	{
		return workorders.size();
	}

	public void add(WorkOrder wo)
	{
		workorders.add(wo);
	};

	public WorkOrder get(int index)
	{
		return workorders.get(index);
	};
};
