import java.util.ArrayList;
import java.util.Collections;

public class Test {

    public static void main(String[] args) {
        //creation of OldLandline and apparent type objects
        OldLandline john = new OldLandline("John", 3232345678L);
        OldLandline mary = new OldLandline("mary", 2078459203L);
        OldLandline allie = new Landline("Allie", 5054569087L);
        OldLandline stefan = new SmartPhone("stefan", 5164739067L, 6, 2, 2,
                "on");
        //creation of Landline and apparent type objects
        Landline valerie = new Landline("valerie", 6469345012L);
        Landline rose = new Landline("Rose", 8002343821L);
        Landline carter = new SmartPhone("carter", 4045123867L, 8, 7, 6,
                "on");
        //creation of actual SmartPhone objects
        SmartPhone jimmy = new SmartPhone("Jimmy", 6314284853L, 5, 5, 3,
                "on");
        SmartPhone peter = new SmartPhone("Peter", 6316470918L, 4, 3, 7,
                "on");
        //creation of actual Laptop objects
        Laptop lily = new Laptop("lily", "Dell", 12, 10, 4, "off");
        Laptop summer = new Laptop("Summer", "HP", 15, 9, 8, "on");
        //creation of Phone apparent type objects
        Phone zack = new OldLandline("Zack", 2205634001L);
        Phone reid = new Landline("reid", 7879083217L);
        Phone belle = new SmartPhone("belle", 9073768490L, 10, 6, 9, "on");
        //creation of Computer apparent type objects
        Computer tom = new SmartPhone("Tom", 5164218970L, 7, 4, 5, "off");
        Computer kate = new Laptop("Kate", "Apple", 13, 8, 4, "on");

        //marker for testing readability purposes only
        System.out.println("\n------------------------------TESTING EXCEPTIONS---------------------------------------");
        //creation of objects with wrong parameters - for purposes of showing exceptions
        //NOTE: this should throw exception bc number > 10 digits
        OldLandline matt = new OldLandline("Matt", 374659238740L);
        //NOTE: this should throw exception bc number starts with zero
        Landline gia = new Landline("Gia", 0217654321L);
        //NOTE: these should throw exception bc state != on/ON or off/OFF
        Laptop aj = new Laptop("AJ", "Lenovo", 18, 9, 8, "whatever");
        SmartPhone will = new SmartPhone("will", 7452390854L, 8, 7, 5,
                "state");

        //marker for testing readability purposes only
        System.out.println("\n---------------------------TESTING OLDLANDLINES----------------------------------------");
        //testing OldLandlines
        /*NOTE: when leaving a message if a landline is busy, blank messages can't be left
        (i.e., they're not counted when printing out a list of messages) For example, if you do blank spaces
        or the enter key for a message then it won't say that a message has been left.*/
        john.end();             //gets silently ignored bc can't end call that has not been made
        john.call(mary);        //oldlandline calls oldlandline, says john is in call w/ mary
        mary.call(allie);       //gets silently ignored bc can't make simultaneous calls
        allie.call(john);       //says john is busy but doesn't ask if allie wants to leave msg bc john is oldlandline
        mary.end();             //says mary ends call w/ john
        john.call(john);        //gets silently ignored bc can't call self
        john.call(valerie);     //oldlandline calls landline, says john is in call w/ valerie
        mary.call(valerie);     //says valerie is busy, asks if message wants to be left (bc valerie is a landline)
        john.end();             //says john ends call w/ valerie
        mary.call(stefan);      //oldlandline calls smartphone, says mary is in call w/ stefan
        valerie.call(stefan);   //says stefan is busy, asks if message wants to be left
        john.call(stefan);      //says line is busy, asks for message - writing to test order of msgs when left
        stefan.end();           //says stefan ends call w/ mary
        /* if messages were left for valerie and stefan in above cases,
         * then these will show using readMessages(MSG_STATUS) and readMessages()
         * below in the order in which they were left: */
        //will only show unread messages for valerie (if any):
        valerie.readMessages(Landline.MSG_STATUS.UNREAD);
        //will show all of valerie's messages (if any), all unread messages are now changed to read:
        valerie.readMessages();
        //will only show unread messages for stefan (if any). NOTE: Typecasting is needed bc apparent type:
        ((SmartPhone) stefan).readMessages(Landline.MSG_STATUS.UNREAD);
        //will show all of stefan's read messages (if any)
        ((SmartPhone) stefan).readMessages(Landline.MSG_STATUS.READ);

        //marker for testing readability purposes only
        System.out.println("\n-----------------------------TESTING LANDLINES-----------------------------------------");
        //testing Landlines
        valerie.call(john);     //landline calls oldlandline, says valerie is in call w/ john
        rose.call(valerie);     //says valerie is busy, asks if message wants to be left
        john.end();             //says john ends call w/ valerie
        rose.call(valerie);     //landline calls landline, says rose is in call w/ valerie
        carter.call(valerie);   //says valerie is busy, asks if message wants to be left
        rose.end();             //says rose ends call w/ valerie
        valerie.call(carter);   //landline calls smartphone, says valerie is in call w/ carter
        rose.call(carter);      //says carter is busy, asks if message wants to be left
        /* if messages were left for valerie and carter in above cases,
         * then these will show using readMessages() below in the order in which they were left:
         * NOTE: call between rose and carter has not ended at this point,
         * to show that phones can't check messages while they're in a call*/
        valerie.readMessages();                             //gets silently ignored bc valerie still in call
        carter.readMessages(Landline.MSG_STATUS.UNREAD);    //gets silently ignored bc carter still in call
        valerie.end();                                      //says valerie ends call w/ carter
        //should show valerie's previous read messages (from above lines) if any were left:
        valerie.readMessages(Landline.MSG_STATUS.READ);
        valerie.readMessages(Landline.MSG_STATUS.UNREAD);   //shows valerie's unread read msgs if any were left
        carter.readMessages(Landline.MSG_STATUS.UNREAD);    //shows carter's unread msgs (if any were left)

        //marker for testing readability purposes only
        System.out.println("\n-----------------------------TESTING SMARTPHONES---------------------------------------");
        //testing SmartPhones
        jimmy.call(peter);      //smartphone calls smartphone, says jimmy is in call w/ peter
        /*change's peter's phone state to "off" - to test what happens when you turn off a phone w/o ending call first
        (it should end call): */
        peter.setState("off");  //says peter ends call w/ jimmy
        peter.call(jimmy);      //gets silently ignored bc phone is off
        valerie.call(peter);    //says valerie can't call peter, asks if she wants to leave a msg
        jimmy.call(valerie);    //says jimmy is in call w/ valerie
        jimmy.call(rose);       //gets silently ignored bc can't make simultaneous calls
        jimmy.end();            //says jimmy ends call w/ valerie
        jimmy.call(john);       //smartphone calls oldlandline, says jimmy is in call w/ john
        rose.call(jimmy);       //says jimmy is busy, asks if message wants to be left
        john.end();             //says john ends call w/ jimmy
        jimmy.call(valerie);    //smartphone calls landline, says jimmy is in call w/ valerie
        /* NOTE: testing smartphone additional features install/play game,
         * call between jimmy and valerie has not ended, therefore smartphones can install/play games while in call*/
        jimmy.installGame("brawl stars");       //says installing game
        jimmy.installGame("hearthstone");       //says installing game
        jimmy.playGame("BRAWL STARS");          //says jimmy plays game, NOTE: case-insensitive
        jimmy.playGame("arena of valor");       //says game can't be played, must install first
        jimmy.end();                                       //says jimmy ends call w/ valerie
        jimmy.installGame("Arena of Valor");    //says installing game
        jimmy.installGame("   ");               //says must specify game name to install
        jimmy.installGame("Identity V");        //says installing game
        jimmy.installGame("arena of valor");    //gets silently ignored bc game is already installed
        jimmy.playGame("");                     //says must specify game name to play
        //gets silently ignored bc smartphone is off and can't install on device that is off:
        peter.installGame("candy crush");
        //gets silently ignored bc smartphone is off and can't play on device that is off:
        peter.playGame("fun run 3");
        jimmy.installGame("fallout shelter");   //says installing game
        jimmy.installGame("game of dice");      //gets silently ignored bc smartphone can't have > 5 games
        jimmy.readMessages();                              //shows all of jimmy's msgs (if any)
        peter.readMessages();                              //gets silently ignored bc smartphone is off
        //says peter's processor speed is 4:
        System.out.println(peter.getOwner() + "'s processor speed is " + peter.getProcessorSpeeed());
        peter.setState("offf");                            //will throw exception bc state must be on/ON, off/OFF

        //marker for testing readability purposes only
        System.out.println("\n-----------------------------TESTING LAPTOPS-------------------------------------------");
        //testing Laptops
        summer.installGame("The SIMS");             //says installing game on summer's hp laptop (hostname)
        summer.playGame("fortnite");                //says game can't be played, must install first
        //gets silently ignored bc laptop is off and can't install on device that is off:
        lily.installGame("words with friends");
        //says lily's dell laptop's (hostname's) processor speed is 4:
        System.out.println(lily.getHostname() + "'s processor speed is " + lily.getProcessorSpeeed());
        lily.setState("ONNN'");                            //will throw exception bc state must be on/ON, off/OFF

        //marker for testing readability purposes only
        System.out.println("\n----------------------TESTING APPARENT/ACTUAL TYPES------------------------------------");
        //testing apparent types
        //NOTE: No typecasting is needed in these cases and functionality is the same as above:
        zack.call(reid);                        //says zack is in call w/ reid
        //says zack is busy but doesn't ask if belle wants to leave a msg (bc zack is an oldlandline):
        belle.call(zack);
        belle.call(reid);                       //says reid is busy and asks if msg wants to be left
        reid.end();                             //says reid ends call w/ zack
        kate.installGame("sudoku");  //says installing game on kate's apple laptop (hostname)
        tom.setState("on");                     //change state of tom's phone to "on" (for use in cases below)
        //NOTE: Typecasting is needed for cases like these but functionality is the same:
        //will show all of reid's unread messages (if any left in the part above):
        ((Landline) reid).readMessages(Landline.MSG_STATUS.UNREAD);
        ((SmartPhone) belle).installGame("solitaire");      //says installing game
        ((SmartPhone) belle).playGame("8 ball pool");       //says game can't be played, must install first
        ((SmartPhone) tom).call(belle);                                //says tom is in call w/ belle
        ((SmartPhone) tom).end();                                      //says tom ends call w/ belle

        //marker for testing readability purposes only
        System.out.println("\n----------------------------TESTING ORDERINGS------------------------------------------");
        //testing Orderings
        //phone ordering testing
        ArrayList<Phone> phones = new ArrayList<Phone>();           //create ArrayList of Phone objects
        //NOTE: Typecasting is needed to add tom to the list (bc he is a declared Computer w/ actual type Smartphone)
        Collections.addAll(phones, john, mary, allie, stefan, valerie, rose, carter, jimmy, peter, zack,
                reid, belle, (SmartPhone)tom);
        //sort and display by phone number
        System.out.println("Phones Ordered by their Numbers (increasing):");
        Collections.sort(phones, new Orderings.PhoneByNumber());
        for(int i = 0; i < phones.size(); i++) {
            System.out.println(phones.get(i).getOwner() + ", " + phones.get(i).number().getPhoneNumber());
        }
        //sort and display by owner name
        System.out.println("\nPhones Ordered by their Owner Names (alphabetically, case-insensitive):");
        Collections.sort(phones, new Orderings.PhoneByOwner());
        for(int i = 0; i < phones.size(); i++) {
            System.out.println(phones.get(i).getOwner());
        }
        //computer ordering testing
        ArrayList<Computer> computers = new ArrayList<>();          //create ArrayList of Computer objects
        Collections.addAll(computers, tom, kate, lily, summer, jimmy, peter);
        //sort and display by screen size
        System.out.println("\nComputers Ordered by Screen Size (increasing):");
        Collections.sort(computers, new Orderings.ComputerByScreenSize());
        for(int i = 0; i < computers.size(); i++) {
            System.out.println(computers.get(i));
        }
        //sort and display by brand name
        /* NOTE: SmartPhones will display first in the list (as they have no brand)
         * and they will be sorted by RAM amount against each other */
        System.out.println("\nComputers Ordered by Brand (alphabetically, case-sensitive):");
        Collections.sort(computers, new Orderings.ComputerByBrand());
        for(int i = 0; i < computers.size(); i++) {
            System.out.println(computers.get(i));
        }
        //sort and display by amount of RAM
        System.out.println("\nComputers Ordered by amount of RAM (increasing):");
        Collections.sort(computers, new Orderings.ComputerByRAM());
        for(int i = 0; i < computers.size(); i++) {
            System.out.println(computers.get(i));
        }
    }
}