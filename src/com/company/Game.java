package com.company;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Game {
   static int X=1,Y=6;
  static Position P0=new Position(4,1);
  static Position D0=new Position(1,1);
  static Position D1=new Position(4,6);
  static Position P1=new Position(2,4);

    List<State> list = new ArrayList<>();
    void startGame(){
    State s=new State(X,Y);
        s.initial_state();

  //  s.translate();//.peek().translate();//.peek().translate().peek().print();
        System.out.println("\nhuerstic1.1  : "+s.distanc_h1());
    s.print();

    System.out.println("\n");

    System.out.println("\n");
    State q ;

/*    q=s.translate().peek();
    q.print();
        System.out.println("huerstic1.2  : "+q.distanc_h1());
        q=q.translate().peek().translate().peek().translate().peek();
        q.print();
        System.out.println("huerstic1.3  : "+q.distanc_h1());

   /* System.out.println("\n");
    System.out.println("\n");
    State q1 ;
    q1=s.translate().peek().translate().peek();
    q1.print();
    q1.path();
/*    System.out.println("\n");
   if(s.equals(q))
    System.out.println("s equal q1 : ");

    //q.print();

   //list.add(s);
  //  System.out.println("QQQQQQ  : "+list.contains(q));

*/    //System.out.println("\n");
 q=ASTAR(s);
  // q=UNIFORM(s);
  q.path();


}
    State UNIFORM(State node){
        long startTime =System.nanoTime();
        long endTime;
        int count=0;
        LinkedList<State> visited=new LinkedList<>();
        PriorityQueue<State> p=new PriorityQueue<>(new CoropreterState());
        p.add(node);

        while (!p.isEmpty()){
            State a=p.peek();
            p.poll();
            if(!visited.contains(a))
            visited.add(a);
           // a.print();
            System.out.println("\n");
         //   System.out.println("node.cost ___ : "+node.cost+  "____ a.cost : "+a.cost );
            if(a.finish()&&a.finished())
            {
                System.out.println("gooooooooooooooooool : "+count);
                endTime=System.nanoTime();
                System.out.println("Time is : "+(endTime-startTime));
                a.print();
                return a;

            }
            else{
                PriorityQueue<State> children =a.translate();

                for (State q:children) {
                    if(!visited.contains(q))
                        p.add(q);
                    else{

                        for (State z:visited) {
                            count++;
                            if (z.cost>q.cost){
                                visited.remove(z);
                                visited.add(q);
                                p.add(q);
                            }
                        }

                    }


                }
            }
            // System.out.println("sssizeee : "+visited.size());
        }

        return null;
    }
    State ASTAR(State node) {
        long startTime =System.nanoTime();
        long endTime;
        int count=0;
        LinkedList<State> visited=new LinkedList<>();
        PriorityQueue<State> p=new PriorityQueue<>(new CoropreterState());
        p.add(node);

        while (!p.isEmpty()){
            State a=p.peek();
            p.poll();
            if(!visited.contains(a))
                visited.add(a);
            // a.print();
            System.out.println("\n");
            //   System.out.println("node.cost ___ : "+node.cost+  "____ a.cost : "+a.cost );
            if(a.finish()&&a.finished())
            {
                System.out.println("gooooooooooooooooool : "+count);
                endTime=System.nanoTime();
                System.out.println("Time is : "+(endTime-startTime));
                a.print();
                return a;

            }
            else{
                PriorityQueue<State> children =a.translate();

                for (State q:children) {
                    count++;
                    if(!visited.contains(q))
                        p.add(q);
                  /*  else{

                        for (State z:visited) {
                            count++;
                            if (z.heurisitc1>q.heurisitc1){
                                visited.remove(z);
                                visited.add(q);
                                p.add(q);
                            }
                        }

                    }*/


                }
            }
            // System.out.println("sssizeee : "+visited.size());
        }

        return null;
        }


}
