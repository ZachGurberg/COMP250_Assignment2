package assignment2;

public class Region {
    private int xMax;
    private int yMax;
    private int xMin;
    private int yMin;
    public Region(int xMin, int yMin, int xMax, int yMax){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public boolean contains(Position p){
        if (p == null)
            return false;
        else
            return (p.getX()<=xMax && p.getX()>=xMin && p.getY()<=yMax && p.getY()>=yMin);
    }
}
