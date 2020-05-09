import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PopUpWindow {
    private String title;
    private String msg;
    private Alert msgBox;

    public PopUpWindow(String title, String msg) {
        this.title = title;
        this.msg = msg;
        createAlert(title, msg);
    }

    public void createAlert(String title, String msg) {
        msgBox = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.CLOSE);
        msgBox.setTitle(title);
        msgBox.setHeaderText(null);
        msgBox.setGraphic(null);
        msgBox.showAndWait();
    }

    public String getTitle() {
        return title;
    }

    public String getMsg() {
        return msg;
    }

    public Alert getMsgBox() {
        return msgBox;
    }

    public void setMsgBox(Alert msgBox) {
        this.msgBox = msgBox;
    }
}
