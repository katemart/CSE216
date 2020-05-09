import javafx.scene.control.TextField;

public class NumberField extends TextField {
    private String numberColor = "red";

    public NumberField() {
        setPromptText("(###) ###-####");
        colorFocused();
    }

    //called in colorValidation() to test validity of input
    public static boolean numberValidation(String number) {
        return number.trim().matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$");
    }

    //called in colorValidation() and colorFocused() to set color
    private void setNumberColor(String color) {
        this.setStyle(String.format("-fx-text-inner-color: %s;", color));
        this.numberColor = color;
    }

    //changes text color depending on its validity, called in colorFocused()
    public void colorValidation() {
        if(numberValidation(this.getText())) {
            setNumberColor("black");
        }
        else setNumberColor("red");
    }

    private void colorFocused() {               //called in constructor
        this.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) colorValidation();    //when focus is lost
            else setNumberColor("black");       //when still has focus
        });
    }

    public String getNumberColor() {
        return numberColor;
    }
}
