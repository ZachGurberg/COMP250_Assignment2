package assignment2;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Position(Position p){
        this.x = p.x;
        this.y=p.y;
    }

    public void reset(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void reset(Position p){
        this.x = p.x;
        this.y = p.y;
    }

    public static int getDistance(Position p1, Position p2){
        return (Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y));
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void moveWest(){
        this.x = this.x-1;
    }
    public void moveEast(){
        this.x=this.x+1;
    }
    public void moveNorth(){
        this.y = this.y-1;
    }
    public void moveSouth(){
        this.y = this.y+1;
    }
    public boolean equals(Object o){
        if (!(o instanceof Position))
            return false;
        else{
            Position comp = (Position)o;
            return (comp.x==this.x && comp.y==this.y);
        }
    }


}
