package assignment2;

public class Caterpillar extends MyDoublyLinkedList<Position>{
    int size;
    Position headPosition;

    public Caterpillar(){
        size =1;
        headPosition = new Position(7,7);
    }
    public Position getHead(){
        return headPosition;
    }

    public void eat(Position food){
        if (isAdjacent(food)){
            super.addFirst(food);
            headPosition = new Position(food); //TODO should we check self collision?
            size++;
        } else throw new IllegalArgumentException();
    }

    public void move(Position p){
        if (isAdjacent(p)){
            super.addFirst(p);
            //TODO should we update the head position too? Should we check self collision?
            super.removeLast();
        } else throw new IllegalArgumentException();
    }

    public boolean selfCollision(Position p){
        for (Position pos : this){
            if (pos.equals(p)){
                return true;
            }
        }
        return false;
    }

    private boolean isAdjacent(Position p){
        if (Math.abs(p.getX() - headPosition.getX())>1)
            return false;
        else if (Math.abs(p.getY()- headPosition.getY())>1)
            return false;
        else return true;
    }
}
