package Windows;

import Model.Node;
import Model.Sudoku;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Scanner;

public class Initializer {
    static private NumberPick np;
    static private Paint[] colorizer;
    static{
        colorizer=new Paint[9];
        colorizer[0]=Color.YELLOW;
        colorizer[1]=Color.GREENYELLOW;
        colorizer[2]=Color.AQUA;
        colorizer[3]=Color.WHITE;
        colorizer[4]=Color.MAGENTA;
        colorizer[5]=Color.LIGHTBLUE;
        colorizer[6]=Color.LIGHTCORAL;
        colorizer[7]=Color.LIGHTSEAGREEN;
        colorizer[8]=Color.ORANGE;
    }

    static public NumberPick getNp()
    {
        return np;
    }
    static public Sudoku sudokuIni(GridPane root, Button ret,Scanner sc) throws Exception
    {
        int n=sc.nextInt();
        Node[][] board=new Node[n][n];
        np=new NumberPick(n);
        int u,d,l,r,c;
        for(int row=0;row<n;++row)
        {
            for(int column=0;column<n;++column)
            {
                u=sc.nextInt();r=sc.nextInt();d=sc.nextInt();l=sc.nextInt();c=sc.nextInt();
                Node temp=new Node(u,r,d,l,row,column,board,colorizer[c]);
                temp.setOnAction(event->np.show(temp.getRow(),temp.getColumn()));
                temp.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                board[row][column]=temp;
            }
        }
        root.setAlignment(Pos.CENTER);
        for(int j=0;j<n;++j)
            for (int i = 0; i < n; ++i)
                root.add(board[j][i],i,j);

        ret.setPrefWidth(25*n);
        root.add(ret,0,n,n,1);
        n=sc.nextInt();
        int col,row,var;
        for(int i=0;i<n;++i)
        {
            row=sc.nextInt();col=sc.nextInt();var=sc.nextInt();
            board[row][col].setVar(var);
            board[row][col].setDisable(true);
        }
        Sudoku sudo=new Sudoku(board);
        np.setSudoku(sudo);
        return sudo;
    }
}
