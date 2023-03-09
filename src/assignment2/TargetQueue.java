package assignment2;

public class TargetQueue extends MyQueue<Position>{
    private MyStack<String> stack;
    public TargetQueue(){
        super();
        stack = new MyStack<String>();
    }
    public void clear(){
        super.clear();
        this.stack.clear();
    }
    public void addTargets(String input){

        if (input == null)
            return;
        if (input.isEmpty())
            return;

        String num = "";
        int i =0;
        while (i<input.length()){
            char c = input.charAt(i);
            switch(c){
                case '(':
                    if (stack.isEmpty() && num.isEmpty())
                        stack.push(Character.toString(c));
                    else
                        throw new IllegalArgumentException();
                    break;
                case ',':
                    if (!num.isEmpty() && isInt(num)){
                        stack.push(num);
                        stack.push(Character.toString(c));
                        num = "";
                    } else
                        throw new IllegalArgumentException();
                    break;
                case ')':
                    int x;
                    Position pos;
                    if (stack.getSize() ==3){
                        boolean condition = true;
                        if (!stack.pop().equals(","))
                            condition = false;
                        String tmp = stack.pop();
                        if (!isInt(tmp))
                            condition = false;
                        if (!stack.pop().equals("("))
                            condition = false;
                        if(num.isEmpty() || !(isInt(num)))
                            condition = false;
                        if(condition) {
                            pos = new Position(Integer.parseInt(tmp), Integer.parseInt(num));
                            super.enqueue(pos);
                        }
                        else throw new IllegalArgumentException();
                    }
                    else
                        throw new IllegalArgumentException();
                    break;
                case '.':
                    if((num.length()!=0 && stack.getSize()!=0)||i==0)
                        throw new IllegalArgumentException();
                    else if (i<input.length()-1){
                        if (input.charAt(i+1) == c)
                            throw new IllegalArgumentException();
                    }
                    stack.clear();
                    num="";
                    break;
                default:
                    if(!Character.isDigit(c))
                        throw new IllegalArgumentException();
                    else{
                        num+=c;
                    }
                    break;
            }
            i++;
        }
        if (input.charAt(i-1)!='.')
            throw new IllegalArgumentException();
        return;
    }

    private boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
