public class Hole {
    private int pos1;
    private int pos2;

    public Hole(int pos1, int pos2){
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public int getPos1()
    {
        return this.pos1;
    }

    public int getPos2()
    {
        return this.pos2;
    }

    public void setPos1(int newPos1)
    {
        this.pos1 = newPos1;
    }

    public void setPos2(int newPos2)
    {
        this.pos2 = newPos2;
    }

    public void incPos1()
    {
        this.pos1++;
    }

    public void incPos2()
    {
        this.pos2++;
    }

    public void decPos1()
    {
        this.pos1--;
    }

    public void decPos2()
    {
        this.pos2--;
    }

    @Override
    public String toString()
    {
        return ("(" + this.pos1 + ", " + this.pos2 + ")");
    }
}
