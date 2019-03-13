package Model;

public class Pair {
    private int x,y;
    Pair(int x,int y)
    {
        this.x=x;this.y=y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void incX()
    {
        ++x;
    }
    public void incY()
    {
        ++y;
    }
    public void decX()
    {
        --x;
    }
    public void decY()
    {
        --y;
    }
    public boolean equals(Object o)
    {
        if(o==null)
            return false;
        if(((Object)this).getClass()!=o.getClass())
            return false;
        if(this.x==((Pair)o).x && this.y==((Pair)o).y)
            return true;
        return false;
    }
    public String toString()
    {
        return x+" "+y;
    }
}
