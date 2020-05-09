import java.util.Objects;

public class OldLandline implements Phone {
    private String owner;
    private PhoneNumber number;
    private boolean isBusy;
    private Phone caller;

    //create OldLandline with person name and phone number
    public OldLandline(String owner, long number) {
        this.owner = owner;
        this.number = new PhoneNumber(number);
    }

    @Override
    public String getOwner() {
        return owner;
    }

    /**call method takes in parameter of type Phone and checks to see if line is Busy/Not Busy.
     * NOTE: method checks to see if either one and/or both person(s) are busy AND that the person is not calling self.
     * If busy, check if phone is a Landline and if so take message from caller.
     * If phone is not a Landline, no message can be left.
     * If phone is not busy, receive call*/
    @Override
    public void call(Phone phone) {
        if(isBusy())
            return;
        else if(phone.isBusy() && !(getOwner().equals(phone.getOwner()))) {
            if(phone instanceof Landline) {
                ((Landline)phone).storeMessage(this);
            } else {
                System.out.println(getOwner() + " is unable to call " + phone.getOwner() + ". " +
                        "Line is currently busy.");
            }
        } else if(((!isBusy() || !phone.isBusy()) || (!isBusy() && !phone.isBusy()))
                && !(getOwner().equals(phone.getOwner()))) {
            receive(phone);
        }
    }

    /**end method first checks to see that the person ending the call is IN a call
     * If person is in a call, method calls receiveEndSignal() and then "frees" up the call parties
     * (i.e., it sets the busy var to false for both callers)
     * NOTE: when busy var is true it means the party is in a call.
     * If person is not in a call, call can't be ended because you cannot end something that does not exist
     * so it'll just silently ignore that*/
    @Override
    public void end() {
      if(this.isBusy()) {
          receiveEndSignal(caller);
          setBusy(false);
          ((OldLandline)caller).setBusy(false);
      }
    }

    /**receive method takes in parameter of type Phone and sets caller and recipient to "busy"
     * This method is called within the call method above, as you cannot receive a call unless someone is calling you.
     * This method also assigns a value to var caller created at the beginning in order to use later in end method.*/
    @Override
    public void receive(Phone from) {
        setBusy(true);
        ((OldLandline)from).setBusy(true);
        System.out.println(getOwner() + " is on the phone with " + from.getOwner());
        setCaller(from);
        ((OldLandline)from).setCaller(this);
    }

    /**receiveEndSignal method informs the parties have "ended" the call.
     * This method is called within end method above*/
    @Override
    public void receiveEndSignal(Phone from) {
        System.out.println(getOwner() + " has ended the call to " + from.getOwner() + ".");
    }

    //setters and getters
    @Override
    public boolean isBusy() {
        return isBusy; //line is busy
    }

    @Override
    public PhoneNumber number() {
        return number;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Phone getCaller() {
        return caller;
    }

    public void setCaller(Phone caller) {
        this.caller = caller;
    }

    //toString() for OldLandline class
    @Override
    public String toString() {
        return "OldLandline [Owner: " + owner
                + ", Number: " + number + ']';
    }

    //equals() and hashCode() for OldLandline class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OldLandline that = (OldLandline) o;
        return isBusy == that.isBusy &&
                owner.equals(that.owner) &&
                number.equals(that.number) &&
                caller.equals(that.caller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, number, isBusy, caller);
    }
}
