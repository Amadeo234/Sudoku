package Windows;

import Model.Sudoku;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.beans.PropertyVetoException;

public class NumberPick extends Stage {
    private int row,col;
    private Sudoku sudo;
    private ErrorWindow vw;
    public NumberPick(int button_count)
    {
        GridPane root=new GridPane();
        vw=new ErrorWindow("Collision with built-in number!");
        root.setAlignment(Pos.CENTER);
        ++button_count;
        Button a=new Button();
        a.setPrefWidth(23);
        a.setOnAction(
            (event)->{
                try {
                    sudo.setTile(row,col,-1);
                } catch (PropertyVetoException e) {
                    vw.close();
                    vw.show();
                }
                this.close();
            }
        );
        root.add(a,0,0);
        int i=1;
        for(;i<button_count/2;++i)
        {
            Button b=new Button(Integer.toString(i));
            b.setOnAction(
                    (event)->{
                        try {
                            sudo.setTile(row,col,Integer.parseInt(b.getText()));
                        } catch (PropertyVetoException e) {
                            vw.close();
                            vw.show();
                        }
                        this.close();
                    }
            );
            root.add(b,i,0);
        }
        for(;i<button_count;++i)
        {
            Button b=new Button(Integer.toString(i));
            b.setOnAction(
                    (event)->{
                        try {
                            sudo.setTile(row,col,Integer.parseInt(b.getText()));
                        } catch (PropertyVetoException e) {
                            vw.close();
                            vw.show();
                        }
                        this.close();
                    }
            );
            root.add(b,i-(button_count/2),1);
        }
        a=new Button("Cancel");
        a.setOnAction((event)->this.close());
        root.add(a,0,2,button_count/2+1,1);
        Scene x=new Scene(root,150,150);
        this.setScene(x);
    }
    public void setSudoku(Sudoku sudo)
        {this.sudo=sudo;}
    public void show(int row, int col)
    {
        this.close();
        this.row=row;this.col=col;
        this.show();
    }
}
