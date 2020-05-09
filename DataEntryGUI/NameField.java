import javafx.scene.control.TextField;

public class NameField extends TextField {
    private String nameColor = "red";

    public NameField() {
        setPromptText("Name");
        colorFocused();
    }

    //called in colorValidation() to test validity of input
    public static boolean nameValidation(String name) {
        return (name.trim().matches("^([A-Z][a-z]*) ([A-Z][a-z]*)$")
                && name.length() <= 20);
    }

    //called in colorValidation() and colorFocused() to set color
    private void setNameColor(String color) {
        this.setStyle(String.format("-fx-text-inner-color: %s;", color));
        this.nameColor = color;
    }

    //changes text color depending on its validity, called in colorFocused()
    public void colorValidation() {
        if(nameValidation(this.getText())) {
            setNameColor("black");
        } else setNameColor("red");
    }


    private void colorFocused() {               //called in constructor
        this.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) colorValidation();    //when focus is lost
            else setNameColor("black");         //when still has focus
        });
    }

    public String getNameColor() {
        return nameColor;
    }
}
