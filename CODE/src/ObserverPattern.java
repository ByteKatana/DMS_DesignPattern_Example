import java.util.ArrayList;
import java.util.List;

abstract class Subject{
    private List<Observer> observers = new ArrayList<Observer>();
    protected int _state;
    protected String _name;

    Subject(int state, String name){
        _name = name;
        _state = state;
    }

    public void Attach(Observer observer){
        observers.add(observer);
    }


    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public int getState(){return _state;}
    public void setState(int value){
        _state = value;
        notifyObservers();
    }

    public String getName(){return _name;}
    public void setName(String value){_name = value;}
}

abstract class Observer{
    public abstract void update(Subject subject);
}

class Client extends Observer{
    private String clientName;
    private Subject _subject;
    private  String _subject_name;
    private int _subject_status;

    public Client(String name){
        clientName = name;
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



    public static void main(String[] args){

        //Payment Document. i.e: invoice, receipt etc.
        WorkOrder paymentDoc = new WorkOrderDoc("Payment Document");

        //Shipment Document. i.e: cargo receipt etc.
        WorkOrder shipmentDoc = new WorkOrderDoc("Shipment Document");

        //Main Work Order
        WorkOrder purchase = new WorkOrderComposite("Purchase Order");

        //Sub-workorder Of Main Work Order
        WorkOrder payment = new WorkOrderComposite("Payment Workorder");
        payment.Add(paymentDoc);

        //Sub-workorder Of Main Work Order
        WorkOrder shipment = new WorkOrderComposite("Shipment Workorder");
        shipment.Add(shipmentDoc);

        purchase.Add(payment);
        purchase.Add(shipment);

        WorkOrderCollection collection = new WorkOrderCollection();
        collection.add(purchase);

        AbstractIterator workOrderIterator = collection.CreateIterator();
        Client clientObserver = new Client("Work Order Owner");
        printAggregate(workOrderIterator,clientObserver);


    }

    static void printAggregate(AbstractIterator i, Client observer)
    {
        System.out.println("Iterating over Work Order Collection:");
        for (i.First(); !i.IsDone(); i.Next())
        {
            i.CurrentItem().Operation(observer);

        }

    }


    public Subject getSubject(){return _subject;}
    public void setSubject(Subject subject){_subject = subject;}
    public String getClientName(){return clientName;}
}