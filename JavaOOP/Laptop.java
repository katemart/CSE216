import java.util.HashSet;
import java.util.Objects;

public class Laptop implements Computer {
    private String owner;
    private String brand;
    private String hostname;
    private int screenSize;
    private int ram;
    private int pSpeed;
    private State state;
    private HashSet<String> laptopGames;

    //Laptop constructor takes in String, String, int, int, int, String parameters to create Laptop object
    public Laptop(String owner, String brand, int screensSize, int ram, int pSpeed, String state) {
        this.owner = owner;
        this.brand = brand;
        this.screenSize = screensSize;
        this.ram = ram;
        this.pSpeed = pSpeed;
        setState(state); //state is initially set to whatever the user indicates when object is first created
        setHostname(owner, brand); //hostname is set with owner and brand of Laptop when object is first created
        laptopGames = new HashSet<>(4); /**laptopGames HashSet size is set to 4 to indicate that Laptops
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

    //setState method in this class has same functionality as setState method in SmartPhone class
    @Override
    public void setState(String to) {
        try {
            if (to.equals("on") || to.equals("ON")) {
                state = State.ON;
            } else if (to.equals("off") || to.equals("OFF")) {
                state = State.OFF;
            } else throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid state. Laptop must be on/ON or off/OFF.");
        }
    }

    //installGame method in this class has same functionality as installGame method in SmartPhone class
    @Override
    public void installGame(String gameName) {
        gameName = gameName.trim();
        if(state.equals(State.ON)) {
            if (laptopGames.size() <= 4) {
                if(gameName!= null) {
                    if(!gameName.isEmpty()) {
                        if(!hasGame(gameName.toLowerCase())) {
                            laptopGames.add(gameName.toLowerCase());
                            System.out.println("Installing " + gameName + " on " + hostname);
                        }
                    } else System.out.println("Must specify game to install");
                }
            }
        }
    }

    //hasGame method in this class has same functionality as hasGame method in SmartPhone class
    @Override
    public boolean hasGame(String gameName) {
        if(laptopGames.contains(gameName.toLowerCase())) {
            return true;
        } else return false;
    }

    //playGame method in this class has same functionality as playGame method in SmartPhone class
    @Override
    public void playGame(String gameName) {
        gameName = gameName.trim();
        if(gameName != null && state.equals(State.ON)) {
            if(!gameName.isEmpty()) {
                if(laptopGames.contains(gameName.toLowerCase())) {
                    System.out.println(getOwner() + " is playing " + gameName);
                } else {
                    System.out.println("Cannot play " + gameName + " on " + hostname + ". Install it first.");
                }
            } else System.out.println("Must specify game name to play.");
        }
    }

    //additional setters and getters
    public String getOwner() {
        return owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String owner, String brand) {
        hostname = owner + "'s " + brand + " Laptop";
    }

    //toString() for Laptop class
    @Override
    public String toString() {
        return "Laptop [Owner: " + owner
                + ", Brand: " + brand
                + ", Screen size: " + screenSize
                + ", RAM: " + ram + ']';
    }

    //equals() and hashCode() for Laptop class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return screenSize == laptop.screenSize &&
                ram == laptop.ram &&
                pSpeed == laptop.pSpeed &&
                owner.equals(laptop.owner) &&
                brand.equals(laptop.brand) &&
                hostname.equals(laptop.hostname) &&
                state == laptop.state &&
                Objects.equals(laptopGames, laptop.laptopGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, brand, hostname, screenSize, ram, pSpeed, state, laptopGames);
    }
}
