import assignment2.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        class Temp{
            int value;
            public Temp(int x){
                value = x;
            }
        }
        Temp obj1 = new Temp(1);
        Temp obj2 = new Temp(2);
        Temp obj3 = new Temp(3);
        Temp obj4 = new Temp(4);


        MyDoublyLinkedList<Temp> dll = new MyDoublyLinkedList();
        dll.add(obj1);
        dll.add(obj2);
        dll.add(obj3);
        dll.add(obj4);

        MyDoublyLinkedList<Temp> dll2 = new MyDoublyLinkedList();
        dll2.add(obj1);
        dll2.add(obj2);
        dll2.add(obj3);
        dll2.add(obj4);

        MyStack<Temp> stack1 = new MyStack();
        stack1.push(obj1);

        MyStack<Temp> stack2 = new MyStack();
        stack2.push(obj1);

        MyQueue<Temp> queue1 = new MyQueue<>();
        queue1.enqueue(obj1);
        queue1.enqueue(obj2);

        MyQueue<Temp> queue2 = new MyQueue<>();
        queue2.enqueue((obj1));
        queue2.enqueue(obj2);


        String tmp = "(24,12).(12,23).";
        TargetQueue test = new TargetQueue();
        test.addTargets(tmp);

//        String actions = "2[N]3[W]";
//        String actions = "3[NE]";
        String actions = "3[2[N]2[E]]";
//        String actions = "2[3[2[N]3SE]1[E]]";
//        String actions = "2[3[2N]]";
//        String actions = "2[3[2[SW]2[NE]]]";
//        String actions = "12[N]";
//        String actions = "12[2[NE]10[2[W]3[SW]]]"; //TODO Multiplier of nested sequence of two parts where the second part is also a nested sequence. Will require moving finalBuilder to a stack
//        String actions = "2[N]2[1[N]2[S]]";
//        String actions = "2[1[N]2[2[N]2[S]]]";
        ActionQueue action = new ActionQueue();
        action.loadFromEncodedString(actions);

    }
}