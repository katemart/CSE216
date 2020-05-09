import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberFieldTest {
    private NumberField numberField1;
    private NumberField numberField2;
    private NumberField numberField3;
    private NumberField numberField4;
    private NumberField numberField5;
    private NumberField numberField6;

    @Before
    public void setUp() throws Exception {
        JFXPanel panel = new JFXPanel();
        numberField1 = new NumberField();
        numberField2 = new NumberField();
        numberField3 = new NumberField();
        numberField4 = new NumberField();
        numberField5 = new NumberField();
        numberField6 = new NumberField();

        numberField1.setText("3233458970");         //invalid, no separation w/ parentheses/space/dash
        numberField2.setText("972-642-2343");       //invalid, only separated by dashes
        numberField3.setText("453-2430581");        //invalid, no separation w/ parentheses/space
        numberField4.setText("(512)342-0512");      //invalid, no space after ")"
        numberField5.setText("(631) 784-2341");     //valid, format is (###) ###-####
        numberField6.setText("(984) 215-6983");     //valid, format is (###) ###-####
    }

    @Test
    public void numberValidation() {
        assertFalse(NumberField.numberValidation(numberField1.getText()));
        assertFalse(NumberField.numberValidation(numberField2.getText()));
        assertFalse(NumberField.numberValidation(numberField3.getText()));
        assertFalse(NumberField.numberValidation(numberField4.getText()));
        assertTrue(NumberField.numberValidation(numberField5.getText()));
        assertTrue(NumberField.numberValidation(numberField6.getText()));
    }

    @Test
    public void getPromptText() {
        assertEquals("Prompt text should be \"(###) ###-####\"",
                "(###) ###-####", numberField1.getPromptText());
        assertEquals("Prompt text should be \"(###) ###-####\"",
                "(###) ###-####", numberField2.getPromptText());
        assertEquals("Prompt text should be \"(###) ###-####\"",
                "(###) ###-####", numberField3.getPromptText());
        assertEquals("Prompt text should be \"(###) ###-####\"",
                "(###) ###-####", numberField4.getPromptText());
        assertEquals("Prompt text should be \"(###) ###-####\"",
                "(###) ###-####", numberField5.getPromptText());
        assertEquals("Prompt text should be \"(###) ###-####\"",
                "(###) ###-####", numberField6.getPromptText());
    }

    @Test
    public void getNumberColor() {
        numberField1.colorValidation();
        assertEquals("Color should be \"red\"", "red", numberField1.getNumberColor());

        numberField2.colorValidation();
        assertEquals("Color should be \"red\"", "red", numberField2.getNumberColor());

        numberField3.colorValidation();
        assertEquals("Color should be \"red\"", "red", numberField3.getNumberColor());

        numberField4.colorValidation();
        assertEquals("Color should be \"red\"", "red", numberField4.getNumberColor());

        numberField5.colorValidation();
        assertEquals("Color should be \"red\"", "black", numberField5.getNumberColor());

        numberField6.colorValidation();
        assertEquals("Color should be \"black\"", "black", numberField6.getNumberColor());
    }
}