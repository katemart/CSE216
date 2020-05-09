import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputPaneTest {
    private InputPane inputPane1;
    private InputPane inputPane2;
    private InputPane inputPane3;
    private InputPane inputPane4;

    @Before
    public void setUp() throws Exception {
        JFXPanel panel = new JFXPanel();
        //when all fields are empty button is disabled
        inputPane1 = new InputPane();
        inputPane1.getNameField1().setText("");
        inputPane1.getNameField2().setText("");
        inputPane1.getNameField3().setText("");
        inputPane1.getNumberField1().setText("");
        inputPane1.getNumberField2().setText("");
        inputPane1.getNumberField3().setText("");

        //when some fields are empty button is disabled
        inputPane2 = new InputPane();
        inputPane2.getNameField1().setText("");
        inputPane2.getNameField2().setText("Emilia Clarke");
        inputPane2.getNameField3().setText("");
        inputPane2.getNumberField1().setText("555-981-4453");
        inputPane2.getNumberField2().setText("(423) 546-2342");
        inputPane2.getNumberField3().setText("");

        //when all fields are not empty button is enabled (even if there is invalid info)
        inputPane3 = new InputPane();
        inputPane3.getNameField1().setText("Isaac Hempstead Wright");
        inputPane3.getNameField2().setText("jack gleeson");
        inputPane3.getNameField3().setText("Natalie Dormer");
        inputPane3.getNumberField1().setText("(555) 981-4453");
        inputPane3.getNumberField2().setText("(423) 546-2342");
        inputPane3.getNumberField3().setText("631-324-5639");

        //when all fields are not empty button is enabled (no wrong info)
        inputPane4 = new InputPane();
        inputPane4.getNameField1().setText("Jason Momoa");
        inputPane4.getNameField2().setText("Aidan Gillen");
        inputPane4.getNameField3().setText("Alfie Allen");
        inputPane4.getNumberField1().setText("(555) 765-4293");
        inputPane4.getNumberField2().setText("(423) 336-6721");
        inputPane4.getNumberField3().setText("(631) 324-5639");
    }

    @Test
    //underlying boolean to activate button
    public void allFilled() {
        assertFalse(inputPane1.allFilled());                //not all text boxes are filled
        assertTrue(inputPane1.getButton().isDisabled());    //button is disabled

        assertFalse(inputPane2.allFilled());                //not all text boxes are filled
        assertTrue(inputPane2.getButton().isDisabled());    //button is disabled

        assertTrue(inputPane3.allFilled());                 //all text boxes are filled
        assertFalse(inputPane3.getButton().isDisabled());   //button is enabled

        assertTrue(inputPane4.allFilled());                 //all text boxes are filled
        assertFalse(inputPane4.getButton().isDisabled());   //button is enabled
    }

    @Test
    //underlying method to check if data is valid and show pop up box accordingly
    public void isInputValid() {
        //there is invalid information, fields are still editable after closing pop up box
        assertFalse(inputPane3.isInputValid());

        //there is no invalid information, all the text boxes are not editable
        assertTrue(inputPane4.isInputValid());
        assertFalse(inputPane4.getNameField1().isEditable());   //field not editable
        assertFalse(inputPane4.getNameField2().isEditable());   //field not editable
        assertFalse(inputPane4.getNameField3().isEditable());   //field not editable
        assertFalse(inputPane4.getNumberField1().isEditable()); //field not editable
        assertFalse(inputPane4.getNumberField2().isEditable()); //field not editable
        assertFalse(inputPane4.getNumberField3().isEditable()); //field not editable
    }
}