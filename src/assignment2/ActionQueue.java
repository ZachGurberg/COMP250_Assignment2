package assignment2;

public class ActionQueue extends MyQueue<Direction>{

    private MyStack<Integer> multiplierStack;
    private MyStack<String> directionStack;
    public ActionQueue(){
        super();
        multiplierStack = new MyStack<Integer>();
        directionStack = new MyStack<String>();
    }
    public void clear(){
        super.clear(); //clears dll from super (MyQueue)
        multiplierStack.clear();
        directionStack.clear();
    }

    public void loadFromEncodedString(String input){
        String numBuilder = "";
        String directionBuilder = "";
        String finalBuilder = "";
        String current = "";
        boolean done;

        if(input.isEmpty())
            return;
        if (input==null)
            return;
        for (int i = 0; i<input.length(); i++){
            char c = input.charAt(i);

            if (multiplierStack.isEmpty() && !finalBuilder.isEmpty()) {
                addToStack(finalBuilder);
                finalBuilder = "";
            }

            if (isInt(c)) {
                if (i+1<input.length()) {                        //for making sure that what follows and int is either int or a '['
                    if (!isInt(input.charAt(i+1)) && input.charAt(i+1) != '[')
                        throw new IllegalArgumentException();
                }
                else throw new IllegalArgumentException(); // if the int is the last character throw
                //otherwise its valid
                numBuilder+=Character.toString(c);
            }
            else if (c == '[')
                if (numBuilder.isEmpty()) //should have a multiplier
                    throw new IllegalArgumentException();
                else{
                    multiplierStack.push(Integer.parseInt(numBuilder));
                    numBuilder = "";

                }
            else if (c==']') {
                if (!directionBuilder.isEmpty()) {
                    directionStack.push(directionBuilder);
                    directionBuilder = "";
                }
                if (multiplierStack.isEmpty())
                    throw new IllegalArgumentException();

                if (directionStack.isEmpty()){
                    current = finalBuilder;
                    int count = multiplierStack.pop();
                    for (int j = 0; j< count-1; j++)
                        finalBuilder += current;
                }
                else{
                    current = directionStack.pop();
                    int count = multiplierStack.pop();
                    for (int j =0; j< count; j++)
                        finalBuilder+=current;
                }
            }

            else if (isDirection(c)){
                if (multiplierStack.isEmpty())
                    throw new IllegalArgumentException();
                if (i+1<input.length()) {
                    if (!isDirection(input.charAt(i+1)) && input.charAt(i+1) != ']') //only element that follows should be a direction or ']'
                        throw new IllegalArgumentException();
                }
                else throw new IllegalArgumentException(); // if direction is last character throw
                directionBuilder+=Character.toString(c);
                }
            else throw new IllegalArgumentException();
        }
        if (!multiplierStack.isEmpty()) //missing closing parenthesis
            throw new IllegalArgumentException();

        addToStack(finalBuilder);
    }

    private void addToStack(String s){
        for (int i = 0; i<s.length(); i++){
            super.enqueue(toDirection(s.charAt(i)));
        }
    }
    private boolean isDirection(char c){
        if (c == 'N' || c =='W' || c == 'S' || c == 'E')
            return true;
        else return false;
    }

    private Direction toDirection(char c){
        if (c == 'N')
            return Direction.NORTH;
        else if (c=='S')
            return Direction.SOUTH;
        else if (c=='W')
            return Direction.WEST;
        else if (c == 'E')
            return Direction.EAST;
        else throw new RuntimeException("Unexpected character");
    }

    private boolean isInt(char c){
        try{
            Integer.parseInt(String.valueOf(c));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
