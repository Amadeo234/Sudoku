package Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class PropChecker implements PropertyChangeListener
{
    private ArrayList<ArrayList<Integer>> graf;
    private Sudoku sudo;
    public PropChecker(Sudoku sudo)
    {
        this.sudo=sudo;
        this.graf=new ArrayList<>(sudo.getSize()*sudo.getSize());
        for(int i=sudo.getSize()*sudo.getSize();i>=0;--i)
            graf.add(new ArrayList<>(sudo.getSize()*sudo.getSize()));
    }

    private void removeCollision(int idx)
    {
        for(Integer i: graf.get(idx))
        {
            graf.get(i).remove((Integer)idx);
            if(graf.get(i).size()==0)
            {
                int col2=i%sudo.getSize();
                int row2=(i-col2)/sudo.getSize();
                sudo.getTileNode(row2,col2).restoreColor();
            }
        }
        graf.get(idx).clear();
        int col=idx%sudo.getSize();
        int row=(idx-col)/sudo.getSize();
        sudo.getTileNode(row,col).restoreColor();
    }
    private void addCollision(int row1,int col1,int row2,int col2)
    {
        int idx1=sudo.getSize()*row1+col1;
        int idx2=sudo.getSize()*row2+col2;
        graf.get(idx1).add(idx2);
        graf.get(idx2).add(idx1);
        sudo.getTileNode(row1,col1).mark();
        sudo.getTileNode(row2,col2).mark();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        String title=evt.getPropertyName();
        int row=title.charAt(0)-48;
        int col=title.charAt(2)-48;
        int idx=sudo.getSize()*row+col;
        int nVar=(Integer)evt.getNewValue();
        int oVar=(Integer)evt.getOldValue();
        if(nVar==oVar)
            return;
        removeCollision(idx);
        if(nVar==-1)
            return;
        boolean proper=true;
        for(int i=0;i<sudo.getSize();++i)//check cross
        {
            if(i!=col)
                if(sudo.getTile(row,i)==nVar)
                {
                    addCollision(row,i,row,col);
                    proper=false;
                }
            if(i!=row)
                if(sudo.getTile(i,col)==nVar)
                {
                    addCollision(i,col,row,col);
                    proper=false;
                }
        }
        Deque<Pair> dq=new ArrayDeque<>();
        dq.push(new Pair(row,col));
        sudo.getTileNode(row,col).addNeighbors(dq);
        dq.pollLast();
        Pair t;
        while(!dq.isEmpty())
        {
            t=dq.poll();
            if(sudo.getTile(t.getX(),t.getY())==nVar)
            {
                addCollision(t.getX(),t.getY(),row,col);
                proper=false;
            }
        }
        if(proper)
            removeCollision(idx);
    }
}
