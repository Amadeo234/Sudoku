package Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayDeque;
import java.util.Deque;

public class VetoChecker implements VetoableChangeListener
{
    private Sudoku sudo;
    public VetoChecker(Sudoku sudo)
        {this.sudo=sudo;}
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException
    {
        String title=evt.getPropertyName();
        int row = title.charAt(0) - 48;
        int col = title.charAt(2) - 48;
        int nVar=(Integer)evt.getNewValue();
        if(nVar==-1)
            return;
        for(int i=0;i<sudo.getSize();++i)//check cross
        {
            if(i!= col)
                if(sudo.isTileDisable(row,i) && sudo.getTile(row,i)==nVar)
                    throw new PropertyVetoException("Builtin Fail",evt);
            if(i!= row)
                if(sudo.isTileDisable(i, col) && sudo.getTile(i, col)==nVar)
                    throw new PropertyVetoException("Builtin Fail",evt);
        }
        Deque<Pair> dq=new ArrayDeque<>();
        dq.push(new Pair(row, col));
        sudo.getTileNode(row, col).addNeighbors(dq);
        dq.pollLast();
        Pair t;
        while(!dq.isEmpty())
        {
            t=dq.poll();
            if(sudo.isTileDisable(t.getX(),t.getY()) && sudo.getTile(t.getX(),t.getY())==nVar)
                throw new PropertyVetoException("Builtin Fail",evt);
        }
    }
}
