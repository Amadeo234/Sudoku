package Model;

import Windows.Drawer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Deque;

public class Node extends Button {
    private int up,down,left,right;
    private Paint nativeColor;
    private final int row,column;//used in connection to NumberPick
    private static Node[][] board;//used for placing component in deque
    private int var;
    public Node(int up, int right, int down, int left,int row,int column,Node[][] board,Paint nativeColor)
    {
        this.nativeColor=nativeColor;
        Drawer.Colorize(nativeColor,this);
        this.up=up;this.down=down;this.left=left;this.right=right;
        this.row=row;this.column=column;
        Node.board=board;
        var=-1;
        this.setPrefWidth(25);
    }
    public void restoreColor()
    {
        Drawer.Colorize(nativeColor,this);
    }
    public void mark()
    {
        Drawer.Colorize(Color.RED,this);
    }
    public int getRow()
        {return row;}
    public int getColumn()
        {return column;}
    public int getVar()
        {return var;}
    public void setVar(int x)
    {
        var=x;
        if(var==-1)
            setText("");
        else
            setText(((Integer)x).toString());
    }
    public void addNeighbors(Deque<Pair> dq)
    {
        Pair x=new Pair(row,column);
        if(up==1)
        {
            x.decX();
            if(!dq.contains(x))
            {
                dq.push(new Pair(x.getX(),x.getY()));
                board[x.getX()][x.getY()].addNeighbors(dq);
            }
            x.incX();
        }
        if(down==1)
        {
            x.incX();
            if(!dq.contains(x))
            {
                dq.push(new Pair(x.getX(),x.getY()));
                board[x.getX()][x.getY()].addNeighbors(dq);
            }
            x.decX();
        }
        if(left==1)
        {
            x.decY();
            if(!dq.contains(x))
            {
                dq.push(new Pair(x.getX(),x.getY()));
                board[x.getX()][x.getY()].addNeighbors(dq);
            }
            x.incY();
        }
        if(right==1)
        {
            x.incY();
            if(!dq.contains(x))
            {
                dq.push(new Pair(x.getX(),x.getY()));
                board[x.getX()][x.getY()].addNeighbors(dq);
            }
            x.decY();
        }
    }
}
