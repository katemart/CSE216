import java.util.Comparator;

public class Orderings {

    //class PhoneByNumber implements Comparator<Phone> to compare phones by their number
    public static class PhoneByNumber implements  Comparator<Phone>{
        @Override
        public int compare(Phone p1, Phone p2) {
            return Long.compare(p1.number().getPhoneNumber(), p2.number().getPhoneNumber());
        }
    }

    //class PhoneByOwner implements Comparator<Phone> to compare phones by their owner's name
    public static class PhoneByOwner implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getOwner().compareToIgnoreCase(p2.getOwner());
        }
    }

    //class ComputerByScreenSize implements Comparator<Computer> to compare computers by their screen size
    public static class ComputerByScreenSize implements Comparator<Computer> {
        @Override
        public int compare(Computer c1, Computer c2) {
            return c1.getScreenSize() - c2.getScreenSize();
        }
    }

    /**class ComputerByBrand implements Comparator<Computer> to compare computers by their brand name
     * Since not all computers have brands (e.g., smart phones) this method will check to see if the computers
     * to be compared are Laptops or not.
     * If both of them are Laptops they will be compared by their brand (case insensitive)
     * If one of them is a Laptop and the other isn't they will be compared and return -1 or 1 (depending on the case)
     * Lastly, if neither object is a computer they will just be compared by their amount of RAM
     * If neither of these cases happen, then something went wrong and method returns -69
     * (chosen randomly for debugging purposes)*/
    public static class ComputerByBrand implements Comparator<Computer> {
        @Override
        public int compare(Computer c1, Computer c2) {
            if(c1 instanceof Laptop && c2 instanceof Laptop)
                return ((Laptop) c1).getBrand().compareToIgnoreCase(((Laptop) c2).getBrand());
            else if (c1 instanceof Laptop && c2 instanceof SmartPhone)
                return 1;
            else if (c1 instanceof SmartPhone && c2 instanceof Laptop)
                return -1;
            else if (c1 instanceof SmartPhone && c2 instanceof SmartPhone)
                return Integer.compare(c1.getRAM(), c2.getRAM());
            return -69;
        }
    }

    //class ComputerByRAM implements Comparator<Computer> to compare computers by their amount of RAM
    public static class ComputerByRAM implements Comparator<Computer> {
        @Override
        public int compare(Computer c1, Computer c2) {
            return c1.getRAM() - c2.getRAM();
        }
    }
}
