
import java.util.ArrayList;
//
//Name        : CompositePattern.java
//
// The classes and/or objects participating in this pattern are:
// 1. Component   (WorkOrder)
//		Declares the interface for objects in the composition. Implements
//      default behavior for the interface common to all classes, as
//      appropriate. declares an interface for accessing and managing its
//		child components.
// 2. Leaf   (WorkOrderDoc)
//		represents leaf objects in the composition. A leaf has no children.
//	    Defines behavior for primitive objects in the composition.
// 3. Composite   (Purchase)
//		defines behavior for components having children. Stores child
//		components. Implements child-related operations in the Component interface.
// 4. Client  (CompositeApp)
//		Manipulates objects in the composition through the Component interface.

// This is the "Component". (i.e tree node.)
interface WorkOrder
{
	void Add(WorkOrder d);

	void Remove(WorkOrder d);

	void Operation(Client observer);

	public String getName();

}

//This is the "Leaf".
class WorkOrderDoc implements WorkOrder
{
	private String name;

	public String getName()
	{
		return name;
	}

	public WorkOrderDoc(String name)
	{
		this.name = name;
	}

	public void Add(WorkOrder c)
	{
		System.out.println("Cannot add to a PrimitiveElement.");
	}

	public void Remove(WorkOrder c)
	{
		System.out.println("Cannot remove from a PrimitiveElement.");
	}

	public void Operation(Client observer)
	{
			PaymentWorkflow paymentWorkflow = new PaymentWorkflow(0,"Payment Workflow");
			observer.setSubject(paymentWorkflow);
			paymentWorkflow.Attach(observer);
			paymentWorkflow.executeWorkflow();

			ShipmentWorkflow shipmentWorkflow = new ShipmentWorkflow(0, "Shipment Workflow");
			observer.setSubject(shipmentWorkflow);
			shipmentWorkflow.Attach(observer);
			shipmentWorkflow.executeWorkflow();

	}
}

// This is the "Composite"
class WorkOrderComposite implements WorkOrder
{
	private ArrayList<WorkOrder> workOrders = new ArrayList<WorkOrder>();

	private String name;

	public String getName()
	{
		return name;
	}

	public WorkOrderComposite(String name)
	{
		this.name = name;
	}

	public void Add(WorkOrder d)
	{
		workOrders.add(d);
	};

	public void Remove(WorkOrder d)
	{
		for (int i = 0; i < workOrders.size(); i++)
		{
			if (workOrders.get(i).getName() == d.getName())
			{
				workOrders.remove(i);
				return;
			}
		}
	}

	public void Operation(Client observer)
	{
		for(WorkOrder wo: workOrders){
			wo.Operation(observer);
		}
	}
}


//This is the "client"
class CompositePattern
{
	/*public static void main(String[] args)
	{

		// Create a tree structure
		WorkOrder purchaseNewEquipmants = new Purchase("Buying a new iPhoneX...");
		purchaseNewEquipmants.Add(new WorkOrderDoc("Check if the website has enough stocks."));
		purchaseNewEquipmants.Add(new WorkOrderDoc("Check if the payment is successful."));
		purchaseNewEquipmants.Add(new WorkOrderDoc("Check if the shipment is done."));

		WorkOrder purchaseSurfacePro = new Purchase("Buying a new Windows PC...");
		purchaseSurfacePro.Add(new WorkOrderDoc("Buy a Surface Pro 6."));
		purchaseSurfacePro.Add(new WorkOrderDoc("Buy a Surface Pro Flip Cover."));
		purchaseNewEquipmants.Add(purchaseSurfacePro);

		// Add and remove a PrimitiveElement
		WorkOrder provingDeletion = new WorkOrderDoc("This document will be deleted soon.");
		provingDeletion.Add(new WorkOrderDoc("This document just added and will be removed very soon."));
		purchaseNewEquipmants.Add(provingDeletion);
		purchaseNewEquipmants.Remove(provingDeletion);

		// Recursively display nodes
		purchaseNewEquipmants.Operation();
	}*/
}
