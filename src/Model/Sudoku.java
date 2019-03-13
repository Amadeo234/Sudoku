package Model;

import java.beans.*;
import java.io.Serializable;

public class Sudoku implements Serializable {
    private Node[][] board;
    private PropertyChangeListener pcl;
    private VetoableChangeListener vcl;

    public Sudoku(Node[][] board)
        {this.board = board;}
    public void setPropertyChangeListener(PropertyChangeListener pcl)
        {this.pcl =pcl;}
    public void setVetoableChangeListener(VetoableChangeListener vcl)
        {this.vcl=vcl;}
    public int getTile(int row, int column)
        {return board[row][column].getVar();}
    public int getSize()
        {return board.length;}
    public boolean isTileDisable(int row,int column)
        {return board[row][column].isDisable();}
    public Node getTileNode(int row,int column)
        {return board[row][column];}

    public void setTile(int row, int column, int var) throws PropertyVetoException
    {
        int old=board[row][column].getVar();
        String title=row+" "+column;
        vcl.vetoableChange(new PropertyChangeEvent(this,title,old,var));
        board[row][column].setVar(var);
        pcl.propertyChange(new PropertyChangeEvent(this,title,old,var));
    }
}
