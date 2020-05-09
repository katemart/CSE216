import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NameFieldTest {
    private NameField nameField1;
    private NameField nameField2;
    private NameField nameField3;
    private NameField nameField4;
    private NameField nameField5;
    private NameField nameField6;
    private NameField nameField7;

    @Before
    public void setUp() throws Exception {
        JFXPanel panel = new JFXPanel();
        nameField1 = new NameField();
        nameField2 = new NameField();
        nameField3 = new NameField();
        nameField4 = new NameField();
        nameField5 = new NameField();
        nameField6 = new NameField();
        nameField7 = new NameField();

        nameField1.setText("John Bradley West");        //invalid, 3 names
        nameField2.setText("Christopher Hemsworth");    //invalid, > 20 chars
        nameField3.setText("peter dinklage");           //invalid, no uppercase for both
        nameField4.setText("Lena headey");              //invalid, no uppercase for one
        nameField5.setText("Nikolaj");                  //invalid, one name only
        nameField6.setText("Maisie Williams");          //valid, 2 words < 20 chars both uppercase
        nameField7.setText("S T");                      //valid, 2 words < 20 chars both uppercase
    }

    @Test
    public void nameValidation() {
        assertFalse(NameField.nameValidation(nameField1.getText()));
        assertFalse(NameField.nameValidation(nameField2.getText()));
        assertFalse(NameField.nameValidation(nameField3.getText()));
        assertFalse(NameField.nameValidation(nameField4.getText()));
        assertFalse(NameField.nameValidation(nameField5.getText()));
        assertTrue(NameField.nameValidation(nameField6.getText()));
        assertTrue(NameField.nameValidation(nameField7.getText()));
    }

    @Test
    public void getPromptText() {
        assertEquals("Prompt text should be \"Name\"", "Name", nameField1.getPromptText());
        assertEquals("Prompt text should be \"Name\"", "Name", nameField2.getPromptText());
        assertEquals("Prompt text should be \"Name\"", "Name", nameField3.getPromptText());
        assertEquals("Prompt text should be \"Name\"", "Name", nameField4.getPromptText());
        assertEquals("Prompt text should be \"Name\"", "Name", nameField5.getPromptText());
        assertEquals("Prompt text should be \"Name\"", "Name", nameField6.getPromptText());
        assertEquals("Prompt text should be \"Name\"", "Name", nameField7.getPromptText());
    }

    @Test
    public void getNameColor() {
        nameField1.colorValidation();
        assertEquals("Color should be \"red\"", "red", nameField1.getNameColor());

        nameField2.colorValidation();
        assertEquals("Color should be \"red\"", "red", nameField2.getNameColor());

        nameField3.colorValidation();
        assertEquals("Color should be \"red\"", "red", nameField3.getNameColor());

        nameField4.colorValidation();
        assertEquals("Color should be \"red\"", "red", nameField4.getNameColor());

        nameField5.colorValidation();
        assertEquals("Color should be \"red\"", "red", nameField5.getNameColor());

        nameField6.colorValidation();
        assertEquals("Color should be \"black\"", "black", nameField6.getNameColor());

        nameField7.colorValidation();
        assertEquals("Color should be \"black\"", "black", nameField7.getNameColor());
    }
}