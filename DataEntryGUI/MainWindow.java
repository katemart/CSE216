import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {
    private BorderPane root;
    private InputPane pane;

    public MainWindow(Stage stage) {
        root = new BorderPane();
        pane = new InputPane();
        root.setPadding(new Insets(30, 15, 15, 15));

        root.setCenter(pane.getPane());

        stage.setTitle("Data Entry GUI");
        stage.setScene(new Scene(root, 400, 200));
        stage.show();
    }
}
