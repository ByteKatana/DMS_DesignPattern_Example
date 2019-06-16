interface Workflow{
    public void fillDoc();
    public void checkAllFields();
    public void Sign();
    public void isSigned();

    //Template Method
    public void executeWorkflow();

}

class PaymentWorkflow extends Subject implements Workflow{

    PaymentWorkflow(int state, String name){
        super(state, name);
    }

    @Override
    public int getState(){return _state;}

    @Override
    public void setState(int value){
        _state = value;
        notifyObservers();
    }

    @Override
    public void fillDoc() {
        System.out.println("Payment Document has been filled");
    }

    @Override
    public void checkAllFields() {
        System.out.println("All fields in the payments document has been checked");
    }

    @Override
    public void Sign() {
        System.out.println("Payment Document has been signed by manager");
    }

    @Override
    public void isSigned() {
        System.out.println("Verified that the payment document is signed by the manager");
    }

    public void executeWorkflow(){

        //Filling the document
        fillDoc();

        //Checking all fields in the document
        checkAllFields();

        //Signing the document
        Sign();

        //Checking if document is signed or not
        isSigned();

        this.setState(1);
    }

}

class ShipmentWorkflow extends Subject implements Workflow{

    ShipmentWorkflow(int state, String name){
        super(state, name);
    }

    @Override
    public int getState(){return _state;}

    @Override
    public void setState(int value){
        _state = value;
        notifyObservers();
    }

    @Override
    public void fillDoc() {
        System.out.println("Shipment Document has been filled");
    }

    @Override
    public void checkAllFields() {
        System.out.println("All fields in the shipment document has been checked");
    }

    @Override
    public void Sign() {
        System.out.println("Shipment Document has been signed by manager");
    }

    @Override
    public void isSigned() {
        System.out.println("Verified that the shipment document is signed by the manager");
    }

    public void executeWorkflow(){

        //Filling the document
        fillDoc();

        //Checking all fields in the document
        checkAllFields();

        //Signing the document
        Sign();

        //Checking if document is signed or not
        isSigned();

        this.setState(1);
    }
}

