package com.company;

import java.util.Comparator;

public class CoropreterState implements Comparator<State> {
   /* @Override// for UCS
    public int compare(State o1, State o2) {
        if (o1.cost>o2.cost)
            return 1;
        if (o1.cost<o2.cost)
            return -1;
        return 0;*/
  /*@Override//for Heuristic1
    public int compare(State o1, State o2) {
        if (o1.heurisitc1>o2.heurisitc1)
            return 1;
        if (o1.heurisitc1<o2.heurisitc1)
            return -1;
        return 0;
    /*@Override//for Heuristic2
        public int compare(State o1, State o2) {
        //System.out.println("o1.h : "+o1.heurisitc2);
       // System.out.println("o2.h : "+o2.heurisitc2);
            if (o1.heurisitc2>o2.heurisitc2)
                return 1;
            if (o1.heurisitc2<o2.heurisitc2)
                return -1;
            return 0;*/
      @Override//for Heuristic1
      public int compare(State o1, State o2) {
          if (o1.heurisitc3>o2.heurisitc3)
              return 1;
          if (o1.heurisitc3<o2.heurisitc3)
              return -1;
          return 0;



    }
}
