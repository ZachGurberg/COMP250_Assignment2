package assignment2;

public class World {
    private Direction currentDirection;
    private Caterpillar caterpillar;
    private Position currentFood;
    private Region space;
    private ActionQueue actions;
    private TargetQueue targets;
    private GameState state;

    public World(TargetQueue targets, ActionQueue actions){
        this.targets = targets;
        this.actions = actions;
        space = new Region(0,0,15,15);
        caterpillar = new Caterpillar();
        currentFood = targets.dequeue();
        state = GameState.MOVE;
        this.currentDirection = Direction.NORTH; //Default
    }

    public void step(){
        if (actions.isEmpty()){
            state = GameState.NO_MORE_ACTION;
            currentDirection = null;}
        else {
            currentDirection = actions.dequeue();
        }

        if (state != GameState.MOVE && state!= GameState.EAT){
            return;
        }

        Position nextPosition = new Position(caterpillar.getHead().getX(), caterpillar.getHead().getY()); //TODO check if I can initialize this? maybe set to head position?
        if (currentDirection==Direction.EAST)
            nextPosition = new Position(caterpillar.getHead().getX()+1, caterpillar.getHead().getY());
        else if (currentDirection == Direction.WEST)
            nextPosition = new Position(caterpillar.getHead().getX()-1, caterpillar.getHead().getY());
        else if (currentDirection == Direction.SOUTH)
            nextPosition = new Position(caterpillar.getHead().getX(), caterpillar.getHead().getY()+1);
        else if(currentDirection == Direction.NORTH)
            nextPosition = new Position(caterpillar.getHead().getX(), caterpillar.getHead().getY()-1);

        if (!space.contains(nextPosition)) {
            state = GameState.WALL_COLLISION;
        }
        else if (caterpillar.selfCollision(nextPosition)) {
            state = GameState.SELF_COLLISION;
        }
        else if (currentFood.equals(nextPosition)) {
            caterpillar.eat(nextPosition);
            if (targets.isEmpty())
                state = GameState.DONE;
            else {
                currentFood = targets.dequeue();
                state = GameState.EAT;
            }
        }
        else{
            caterpillar.move(nextPosition);
            state = GameState.MOVE;
        }
    }

    public GameState getState(){
        return state;
    }
    public Caterpillar getCaterpillar(){
        return caterpillar;
    }

    public Position getFood(){
        return currentFood;
    }
    public boolean isRunning(){
        return(state == GameState.MOVE || state == GameState.EAT);
    }


}
