import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Landline extends OldLandline {
    public enum MSG_STATUS {READ, UNREAD}
    private ArrayList<Message> msgList;

    /**Nested class Message within class Landline.
     * Created in order to use as Object parameter for ArrayList*/
    class Message {
        private Enum status;
        private String msg;

        //Message constructor has type Enum and String parameters
        public Message(Enum status, String msg) {
            this.msg = msg;
            this.status = status;
        }

        //setters and getters
        public Enum getStatus() {
            return status;
        }

        public void setStatus(Enum status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        //toString() for Message class
        @Override
        public String toString() {
            return "[" + status + " message: " + msg + "]";
        }
    }

    /**Landline class.
     * Landline constructor takes in String and long parameters to create Landline object*/
    public Landline(String owner, long number) {
        super(owner, number);
        msgList = new ArrayList<>();
    }

    /**leaveMsg method takes in parameter of type Phone.
     * Informs caller that recipient is busy and asks if they want to leave a msg.
     * If caller says yes, take message and return String msg (for later use).
     * If no, don't take message
     * If anything else is selected, say that is not a valid selection*/
    public String leaveMsg(Phone phone) {
        Scanner input = new Scanner(System.in);
        System.out.println(phone.getOwner() + " is unable to call " + getOwner() + ". Line is currently busy."
                + "\nDoes " + phone.getOwner() + " want to leave a message? [y/n]");
        String answer = input.nextLine();
        if(answer.toLowerCase().equals("y")) {
            String msg = input.nextLine();
            msg = msg.trim();
            if(!msg.isEmpty()) {
                System.out.println(phone.getOwner() + " left a message for " + getOwner());
            }
            return msg;
        } else if(answer.toLowerCase().equals("n")) {
            System.out.println("No message left.");
        } else {
            System.out.println("That is not a valid selection.");
        }
        return "";
    }

    /**storeMessage method takes message left from caller to a busy line and adds message to
     * an ArrayList of Message (nested class), which has enum type and String
     * All messages are initially enum UNREAD when stored (i.e., they're marked UNREAD when added to list)*/
    public void storeMessage(Phone myPhone) {
        String message = leaveMsg(myPhone);
        if (!(message == null))
            if (!message.isEmpty() || !message.trim().equals(""))
                msgList.add(new Message(MSG_STATUS.UNREAD, message));
    }

    /** NOTE: if a person is in a call, they cannot check messages. So the method checks to see
     * if person is in a call first. If yes, silently ignore. If no, read messages.
     * readMessages method (without parameter) goes through ALL the messages and prints them.
     * readMessages method (with parameter) takes in MSG_STATUS as parameter
     * If the MSG_STATUS is UNREAD the method will give as output all unread msgs only
     * If the MSG_STATUS is READ the method will give as output all read msgs only
     * NOTE: after going through an UNREAD msg the status will change to READ,
     * if its already READ no change will be applied*/
    public void readMessages() {
        if(!isBusy()) {
            System.out.println("\n" + getOwner() + "'s messages list:");
            for(int i = 0; i < msgList.size(); i++) {
                System.out.println(msgList.get(i));
                msgList.get(i).setStatus(MSG_STATUS.READ);
            }
        }
    }

    public void readMessages(MSG_STATUS status) {
        if(!isBusy()) {
            if (status.equals(MSG_STATUS.UNREAD)) {
                System.out.println("\n" + getOwner() + "'s unread messages list:");
                for (int i = 0; i < msgList.size(); i++) {
                    if(msgList.get(i).getStatus().equals(MSG_STATUS.UNREAD)) {
                        System.out.println(msgList.get(i));
                        msgList.get(i).setStatus(MSG_STATUS.READ);
                    }
                }
            } else if (status.equals(MSG_STATUS.READ)) {
                System.out.println("\n" + getOwner() + "'s read messages list:");
                for (int i = 0; i < msgList.size(); i++) {
                    if(msgList.get(i).getStatus().equals(MSG_STATUS.READ)) {
                        System.out.println(msgList.get(i));
                    }
                }
            }
        }
    }

    //toString() for Landline class
    @Override
    public String toString() {
        return "Landline [Owner: " + this.getOwner()
                + " Number:" + this.number().getPhoneNumber() + ']';
    }

    //equals() and hashCode() for Landline class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Landline landline = (Landline) o;
        return Objects.equals(msgList, landline.msgList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), msgList);
    }
}

