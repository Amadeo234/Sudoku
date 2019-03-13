package Windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ErrorWindow extends Stage {
    public ErrorWindow(String errorText)
    {
        GridPane p=new GridPane();
        Label t=new Label(errorText);
        Button b=new Button("OK!");
        b.setOnAction(e->this.close());
        p.setAlignment(Pos.CENTER);
        p.add(t,0,0);
        p.add(b,0,1);
        Scene x=new Scene(p,200,150);
        this.setScene(x);
    }
}
