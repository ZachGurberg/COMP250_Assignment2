package assignment2;

public class ActionQueue extends MyQueue<Direction>{

    public ActionQueue(){
        super();
    }
    public void clear(){
        super.clear(); //clears dll from super (MyQueue)
    }

    public void loadFromEncodedString(String input){

        //primitive check for syntax
        int openCount=0;
        int closeCount=0;
        int intCount = 0;

        for (int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if (isDirection(c))
                continue;
            else if (isInt(c)) {
                for (int j = i+1; j < input.length(); j++) {
                    if (!isInt(input.charAt(j)))
                        break;
                    else
                        i = j;
                }
                intCount++;
            }

            else if (c == '[') {
                openCount++;
                if (i+1<input.length()){
                    if (!isInt(input.charAt(i+1))&&!isDirection(input.charAt(i+1)))
                        throw new IllegalArgumentException();
                }
                else throw new IllegalArgumentException();
            }
            else if (c == ']')
                closeCount++;
            else
                throw new IllegalArgumentException();
        }
        if (openCount!=closeCount || openCount!=intCount)
            throw new IllegalArgumentException();

        String conversion = tmp(input);
        addToStack(conversion);
    }

    private String tmp(String input) {

        String builder = "";

        for (int i =0; i<input.length(); i++){
            char c = input.charAt(i);

            if (isDirection(c)){
                builder+=c;
            }

            else if (isInt(c)){
                String numBuilder = "";
                numBuilder+=c;

                int multiplier=-1;
                String subString = "";
                int p = i+1;
                while (p<input.length()){
                    if (isInt(input.charAt(p))){
                        numBuilder+=input.charAt(p);
                        p++;
                    }
                    else if (input.charAt(p)=='['){
                        multiplier = Integer.parseInt(numBuilder);
                        break;}
                    else
                        throw new IllegalArgumentException();

                }
                if (multiplier==-1) //Missing opening parenthesis
                    throw new IllegalArgumentException();
                if (p+1>=input.length())
                    throw new IllegalArgumentException(); //Missing argument after opening parenthesis

                int counter = 0;
                for (int k = p+1; k<input.length(); k++){//Starting just after the '['
                    char d = input.charAt(k);
                    if (d == '[') {
                        counter++;
                    }
                    if (d == ']'){
                        if (counter == 0){
                            String toMultiply = tmp(subString);
                            for (int j=0; j<multiplier;j++){
                                builder+=toMultiply;
                            }
                            break;
                        } else{
                            counter --;
                        }
                    }
                    subString+=d;
                }
                if (counter!=0)
                    throw new IllegalArgumentException();
                i+=(subString.length()+2);
            }
        }
        return builder;
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
