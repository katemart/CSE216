import java.util.Objects;

public class PhoneNumber {
    private final long phoneNumber;

    //take in a parameter of type long
    public PhoneNumber(long phoneNumber) {
        /**convert long to string to check that 1st digit is between 1-9 and total digits equal 10.
         * If it is invalid, throw exception*/
        this.phoneNumber = phoneNumber;
        try {
            if(!String.valueOf(phoneNumber).matches("[1-9]\\d{9}"))
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid phone number. Must be 10 valid digits.");
        }
    }

    //getter
    public long getPhoneNumber() {
        return phoneNumber;
    }

    //toString() for PhoneNumber class
    @Override
    public String toString() {
        return "[Phone Number: " + phoneNumber + "]";
    }

    //equals() and hashCode() for PhoneNumber class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return phoneNumber == that.phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}