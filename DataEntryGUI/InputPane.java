import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.Arrays;
import java.util.List;

public class InputPane {
    private GridPane pane;
    private Button button;
    private PopUpWindow errorPopUpWindow;
    private PopUpWindow validPopUpWindow;
    private NameField nameField1, nameField2, nameField3;
    private NumberField numberField1, numberField2, numberField3;
    private List<NameField> nameFields;
    private List<NumberField> numberFields;

    public InputPane() {
        pane = new GridPane();

        nameField1 = new NameField();
        nameField2 = new NameField();
        nameField3 = new NameField();

        numberField1 = new NumberField();
        numberField2 = new NumberField();
        numberField3 = new NumberField();

        button = new Button("Create Profiles");

        nameFields = Arrays.asList(nameField1, nameField2, nameField3);
        numberFields = Arrays.asList(numberField1, numberField2, numberField3);

        setPaneLayout();
        buttonClicked();
        isButtonClickable();
    }

    private void setPaneLayout() {
        pane.add(nameField1, 1, 0);
        pane.add(numberField1, 2, 0);

        pane.add(nameField2, 1, 1);
        pane.add(numberField2, 2, 1);

        pane.add(nameField3, 1, 2);
        pane.add(numberField3, 2, 2);

        pane.add(button, 1, 3, 2, 1);
        GridPane.setHalignment(button, HPos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);
    }

    public boolean isInputValid() {             //called in buttonClicked()
        for(int i = 0; i < nameFields.size(); i++) {
            if(!NameField.nameValidation(nameFields.get(i).getText()) ||
                    !NumberField.numberValidation(numberFields.get(i).getText())) {
                return false;
            }
        }
        for(int i = 0; i < nameFields.size(); i++) {
            nameFields.get(i).setEditable(false);
            numberFields.get(i).setEditable(false);
        }
        return true;
    }

    //sets pop up window according to value from isInputValid(), called in constructor
    private void buttonClicked() {
        button.setOnAction(e -> {
            if(isInputValid()) {
                validPopUpWindow = new PopUpWindow("Data Saved",
                        "The profiles have been saved and added to the database.");
            } else {
                errorPopUpWindow = new PopUpWindow("Invalid input error",
                        "INVALID INPUT: you have attempted to provide one or more invalid input(s). " +
                                "Please correct the information displayed in red and retry.");
            }
        });
    }

    //called in clickable() to disable/enable button
    public boolean allFilled() {
        for(int i = 0; i < nameFields.size(); i++) {
            if(nameFields.get(i).getText().isEmpty()
                    || numberFields.get(i).getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    //called in isButtonClickable() to take into account ALL fields
    private void clickable(TextField field) {
        button.setDisable(true);
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            button.setDisable(!allFilled());
        });
    }

    private void isButtonClickable() {           //called in constructor
        for(int i = 0; i < nameFields.size(); i++) {
            clickable(nameFields.get(i));
            clickable(numberFields.get(i));
        }
    }

    public GridPane getPane() {
        return pane;
    }

    public Button getButton() {
        return button;
    }

    public NameField getNameField1() {
        return nameField1;
    }

    public NameField getNameField2() {
        return nameField2;
    }

    public NameField getNameField3() {
        return nameField3;
    }

    public NumberField getNumberField1() {
        return numberField1;
    }

    public NumberField getNumberField2() {
        return numberField2;
    }

    public NumberField getNumberField3() {
        return numberField3;
    }
}
