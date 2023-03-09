package assignment2;

public class World {
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
        this.targets.dequeue(); //TODO should we add this to the actions queue? Type difference between direction and action
        state = GameState.MOVE;
    }

    public void step(){

    }


}
