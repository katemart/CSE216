import java.util.HashSet;
import java.util.Objects;

public class SmartPhone extends Landline implements Computer {
    private int screenSize;
    private int ram;
    private int pSpeed;
    private State state;
    private HashSet<String> phoneGames;

    //SmartPhone constructor takes in String, long, int, int, int, String parameters to create SmartPhone object
    public SmartPhone (String owner, long number, int screenSize, int ram, int pSpeed, String state) {
        super(owner, number);
        this.screenSize = screenSize;
        this.ram = ram;
        this.pSpeed = pSpeed;
        setState(state); //state is initially set to whatever the user indicates when object is first created
        phoneGames = new HashSet<>(4); /**phoneGames HashSet size is set to 4 to indicate that SmartPhones
         cannot store more than 5 games (for later use when installing games)*/
    }

    //Computer interface methods
    @Override
    public int getScreenSize() {
        return screenSize;
    }

    @Override
    public int getRAM() {
        return ram;
    }

    @Override
    public int getProcessorSpeeed() {
        return pSpeed;
    }

    @Override
    public State getState() {
        return state;
    }

    /**setState is used when setState() is called in constructor to set the SmartPhone's state
     * setState method takes in parameter of type String then checks if parameter equals on/on, off/OFF, or neither
     * If parameter given equals on/ON then the SmartPhone's State is set to ON (from enum State in Comp. interface)
     * If parameter given equals off/OFF then the SmartPhone's State is set to OFF (from enum State in Comp. interface)
     * If parameter given doesn't equal on/ON or off/OFF then an IllegalArgumentException is thrown*/
    @Override
    public void setState(String to) {
        try {
            if (to.equals("ON") || to.equals("on")) {
                state = State.ON;
            } else if (to.equals("OFF") || to.equals("off")) {
                end();
                state = State.OFF;
            } else throw new IllegalArgumentException("Invalid state. Smart phone must be on/ON or off/OFF.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid state. Smart phone must be on/ON or off/OFF.");
        }

    }

    /**installGame method takes in a String parameter then checks to see if SmartPhone is ON and there are no more
     * than 4 games already installed in the SmartPhone.
     * If these conditions are met then it goes on to check that the game name (string parameter given)
     * is not null, if so then it installs the game on SmartPhone (i.e., adds it to the HashSet of games)
     * If the conditions aren't met (i.e., SmartPhone is OFF and/or has more than 4 games already installed,
     * then the game is not installed.
     * If game name (string parameter given) is empty then it tells the user a name must be given
     * NOTE: String parameter is turned into lowercase to avoid issues/excessive number of cases*/
    @Override
    public void installGame(String gameName) {
        gameName = gameName.trim();
        if (state.equals(State.ON)) {
            if (phoneGames.size() <= 4) {
                if(gameName != null) {
                    if(!gameName.isEmpty()) {
                        if(!hasGame(gameName.toLowerCase())) {
                            phoneGames.add(gameName.toLowerCase());
                            System.out.println("Installing " + gameName + " on " + getOwner() + "'s smart phone.");
                        } else {
                            return;
                        }
                    } else System.out.println("Must specify game name to install.");
                }
            }
        }
    }

    /**hasGame method takes in String parameter and is called when checking if the game is already installed in device
     * It checks the string (and turns it to lowercase to avoid issues) to see if it is already included in the HashSet
     * and if so it returns true, otherwise it returns false*/
    @Override
    public boolean hasGame(String gameName) {
        if(phoneGames.contains(gameName.toLowerCase())) {
            return true;
        } else return false;
    }

    /**playGame method takes in String parameter and checks to see that the device is ON
     * If so, then it checks that the String given is not null. If both of these conditions are met, then it checks
     * the HashSet to see if it has the game in it, if so the user can play it. Otherwise they must install it first
     * If the string is empty the user must specify name*/
    @Override
    public void playGame(String gameName) {
        gameName = gameName.trim();
        if(state.equals(State.ON)) {
            if(gameName != null) {
                if(!gameName.isEmpty()) {
                    if(phoneGames.contains(gameName.toLowerCase())) {
                        System.out.println(getOwner() + " is playing " + gameName);
                    } else {
                        System.out.println("Cannot play " + gameName + " on " + getOwner() +"'s smart phone. " +
                                "Install it first.");
                    }
                } else System.out.println("Must specify game name to play.");
            }
        }
    }

    @Override
    public boolean isBusy() {
        return State.OFF.equals(state) || super.isBusy();
    }

    /** override readMessages() from Landline to check if phone is on/off.
     * If off, silently ignore
     * If on, read messages*/
    @Override
    public void readMessages() {
        if(state.equals(State.ON)) {
            super.readMessages();
        }
    }

    /** override readMessages(MSG_STATUS) from Landline to check if phone is on/off.
     * If off, silently ignore
     * If on, read messages*/
    @Override
    public void readMessages(MSG_STATUS status) {
        if(state.equals(State.ON)) {
            super.readMessages(status);
        }
    }

    //toString() for SmartPhone class
    @Override
    public String toString() {
        return "SmartPhone [Owner: " + getOwner()
                + ", Number: " + number().getPhoneNumber()
                + ", Screen size: " + screenSize
                + ", RAM: " + ram + ']';
    }

    //equals() and hashCode() for SmartPhone class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartPhone that = (SmartPhone) o;
        return screenSize == that.screenSize &&
                ram == that.ram &&
                pSpeed == that.pSpeed &&
                state == that.state &&
                Objects.equals(phoneGames, that.phoneGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), screenSize, ram, pSpeed, state, phoneGames);
    }
}
