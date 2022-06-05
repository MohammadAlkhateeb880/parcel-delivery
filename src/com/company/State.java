package com.company;

import java.util.*;

import static java.lang.Math.*;


public class State {
    int n=5;
    int m=7;
    Cell [][]map =new Cell[n][m];
    int Xtanker;
    int Ytanker;
    int cost;
    int heurisitc1;
    int heurisitc2;
    int heurisitc3;
    State parent;
    Set<String> parcelList =new HashSet<>();
    PriorityQueue<State> children=new PriorityQueue<>(new CoropreterState());

    State(){}
    State(int Xtanker,int Ytanker){
        this.Xtanker=Xtanker;
        this.Ytanker=Ytanker;
    }
    void copy(State another) {

        this.n = another.n;
        this.m = another.m;
        this.heurisitc1=another.heurisitc1;
        this.heurisitc2=another.heurisitc2;
        this.heurisitc3=another.heurisitc3;
        this.cost=another.cost;
        this.Xtanker=another.Xtanker;
        this.Ytanker=another.Ytanker;
        this.parcelList.addAll(another.parcelList);
        for ( int i=1;i<another.n-1;i++ )
            for (int j=1;j<another.m-1;j++) {
                this.map[i][j].s = another.map[i][j].s;
                this.map[i][j].num = another.map[i][j].num;
                this.map[i][j].Done=another.map[i][j].Done;
                this.map[i][j].check=another.map[i][j].check;
                this.map[i][j].parcel.typOfParcel=another.map[i][j].parcel.typOfParcel;
               // System.out.println(" Sizeeee : "+another.parcelList.size());

              // this.map[i][j].num.addAll(another.map[i][j].num);
            }

        this.parent=another.parent;

    }

        void initial_state(){
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
            {
                map[i][j]=new Cell();
                map[i][j].s="#";
                map[i][j].check=".";
                map[i][j].parcel=new Parcel();

                map[i][j].parcel.typOfParcel="";
               // map[i][j].num.add(0);
            }
        heurisitc1=0;
        heurisitc2=0;
        heurisitc3=0;
      map[1][1].s="D";
        map[1][1].check="Di";
        map[1][2].s=".";
        map[1][3].s=".";
        map[1][4].s=".";
        map[1][5].s=".";
        map[1][1].parcel.typOfParcel="0";
        map[2][5].s=".";
        map[2][6].s=".";
        map[2][1].s=".";
        map[3][1].s=".";
        map[3][2].s=".";
        map[3][5].s=".";
        map[3][6].s=".";
        map[4][2].s=".";
        map[4][3].s=".";
        map[4][4].s=".";
        map[4][5].s=".";

        map[2][4].s="P";
        map[2][4].check="Pi";
        map[2][4].parcel.typOfParcel="1";

        map[4][1].s="P";
        map[4][1].check="Pi";
        map[4][1].parcel.typOfParcel="0";
        map[4][6].s="D";
        map[4][6].check="Di";
        map[4][6].parcel.typOfParcel="1";
            map[Xtanker][Ytanker].s="T";
            map[Xtanker][Ytanker].check="T";
/*
            map[1][1].s=".";
            map[1][2].s=".";
            map[1][3].s=".";
            map[1][4].s=".";
            map[1][5].s=".";
            map[2][1].s=".";
            map[2][2].s="P";
            map[2][2].parcel.typOfParcel="0";
            map[2][2].check="Pi";
            map[2][5].s=".";
            map[2][7].s=".";
            map[3][1].s="D";
            map[3][1].parcel.typOfParcel="0";
            map[3][1].check="Di";
            map[3][2].s=".";
            map[3][7].s=".";
            map[4][7].s=".";
            map[3][6].s=".";
            map[4][6].s=".";
            map[3][3].s=".";
            map[3][5].s=".";
            map[3][6].s=".";
            map[3][7].s=".";
            map[4][1].s=".";
            map[4][2].s=".";
            map[4][3].s=".";
            map[4][5].s=".";
            map[4][6].s=".";
            map[4][7].s=".";
            map[Game.X][Game.Y].s="T";
            map[Game.X][Game.Y].check="T";

*/


    }
    void print() {

        for (int i = 1; i < n-1; i++) {
            for (int j = 1; j < m-1; j++) {


                        //for (int d:this.map[i][j].num)
                        //System.out.print(d+"-");
                System.out.print(this.map[i][j].check);
                System.out.print(map[i][j].parcel.typOfParcel);
                System.out.print("    ");
            }
            System.out.println();
        }

    }
    void path(){

        LinkedList<State> l=new LinkedList<>();
        State q=this;
        while (q!=null){
            l.addFirst(q);

            q=q.parent;

        }
        for (State a:l) {
            System.out.println("__________  Cost : "+a.cost);
            a.print();
        }

    }

    boolean finished(){
        return (Game.X==Xtanker)&&(Game.Y==Ytanker);
    }
    boolean finish(){
        for(int i=1;i<n-1;i++)
            for(int j=1;j<m-1;j++)
                if(map[i][j].s.equals("P")||map[i][j].s.equals("D"))
                    if(!map[i][j].Done)
                        //if((Game.X!=Xtanker)&&(Game.Y!=Ytanker))
                        return false;
                    return true;

    }

    int distanc_h0(){
        if(!this.map[Game.P1.x][Game.P1.y].Done){
            //calculate distance from current state to position receive to position Delivery
            return  (abs(this.Xtanker-Game.P1.x)+abs(this.Ytanker-Game.P1.y))
                    +(abs(Game.P1.x-Game.D1.x)+abs(Game.P1.y-Game.D1.y));


        }
        else if(!this.map[Game.D1.x][Game.D1.y].Done){
            return  abs(this.Xtanker-Game.D1.x)+abs(this.Ytanker-Game.D1.y);
        }
        else
            return 0;

    }
    int distanc_h3(){
        return distanc_h2()+distanc_h0();
    }
   int distanc_h2(){
        if(!this.map[Game.P0.x][Game.P0.y].Done){
                    //calculate distance from current state to position receive to position Delivery
            return  (abs(this.Xtanker-Game.P0.x)+abs(this.Ytanker-Game.P0.y))
                    +(abs(Game.P0.x-Game.D0.x)+abs(Game.P0.y-Game.D0.y));


        }
        else if(!this.map[Game.D0.x][Game.D0.y].Done){
            return  abs(this.Xtanker-Game.D0.x)+abs(this.Ytanker-Game.D0.y);
        }
        else
            return 0;


   }
    int distanc_h1(){
       /*   Random r=new Random();
        return r.nextInt(5);*/
        return (abs(this.Xtanker-Game.X)+abs(this.Ytanker-Game.Y));
    }


    List<String> next_state(){
        List<String> p=new ArrayList<>();
        int x1=Xtanker;
        int y1=Ytanker;


        if(!map[x1][y1+1].s.equals("#"))
        {
            p.add("Right");

        }
        if(!map[x1][y1-1].s.equals("#")){
            p.add("Left");

        }
        if(!map[x1+1][y1].s.equals("#")){

            p.add("Down");

        }
        if(!map[x1-1][y1].s.equals("#")){
            p.add("Up");


        }
        return p;
    }

    PriorityQueue<State>  translate(){
        List<String> l=next_state();

        for(String h : l)
        {
            switch (h)
            {
                case "Up":
                {

                    State w= new State();
                    w.initial_state();
                    w.copy(this);
                    w.parent=this;
                    //w.map[w.Xtanker][w.Ytanker].num.add(w.cost+1);
                    if(!(w.map[w.Xtanker][w.Ytanker].s.equals("D")||w.map[w.Xtanker][w.Ytanker].s.equals("P"))){
                        w.map[w.Xtanker][w.Ytanker].check=".";
                    }
                    w.Xtanker--;
                    if(!(w.map[w.Xtanker][w.Ytanker].s.equals("D")||w.map[w.Xtanker][w.Ytanker].s.equals("P"))){
                        w.map[w.Xtanker][w.Ytanker].check="T";
                    }
                    w.cost++;

                    if(w.map[w.Xtanker][w.Ytanker].s.equals("P")){//Receiving site Parcel
                        if(!w.map[w.Xtanker][w.Ytanker].Done)
                        {
                            w.map[w.Xtanker][w.Ytanker].Done=true;
                            w.parcelList.add(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel);
                            w.map[w.Xtanker][w.Ytanker].check="Pj";
                            w.cost++;
                            //w.heurisitc2++;
                        }
                    }
                    else   if(w.map[w.Xtanker][w.Ytanker].s.equals("D"))//Delivery site Parcel
                            if(!w.map[w.Xtanker][w.Ytanker].Done)
                            {
                                if(w.parcelList.contains(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel))
                                {
                                    w.parcelList.remove(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel);
                                    w.map[w.Xtanker][w.Ytanker].Done=true;
                                    w.map[w.Xtanker][w.Ytanker].check="Dj";
                                    w.cost--;
                                }
                            }
                    w.heurisitc2=w.distanc_h2();
                    w.heurisitc1=w.distanc_h1();
                    w.heurisitc3=w.distanc_h3();
                    this.children.add(w);
                    break;
                }
                case "Down":
                {
                    State w= new State();
                    w.initial_state();
                    w.copy(this);
                    w.parent=this;
                    w.map[w.Xtanker][w.Ytanker].num.add(w.cost+1);
                    if(!(w.map[w.Xtanker][w.Ytanker].s.equals("D")||w.map[w.Xtanker][w.Ytanker].s.equals("P"))){
                        w.map[w.Xtanker][w.Ytanker].check=".";
                    }
                    w.Xtanker++;
                    if(!(w.map[w.Xtanker][w.Ytanker].s.equals("D")||w.map[w.Xtanker][w.Ytanker].s.equals("P"))){
                        w.map[w.Xtanker][w.Ytanker].check="T";
                    }
                    w.cost++;

                    if(w.map[w.Xtanker][w.Ytanker].s.equals("P")){//Receiving site Parcel
                        if(!w.map[w.Xtanker][w.Ytanker].Done)
                        {
                            w.map[w.Xtanker][w.Ytanker].Done=true;
                            w.parcelList.add(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel);
                            w.map[w.Xtanker][w.Ytanker].check="Pj";
                            w.cost++;
                           // w.heurisitc2++;
                        }
                    }
                    else   if(w.map[w.Xtanker][w.Ytanker].s.equals("D"))//Delivery site Parcel
                        if(!w.map[w.Xtanker][w.Ytanker].Done)
                        {
                            if(w.parcelList.contains(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel))
                            {
                                w.parcelList.remove(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel);
                                w.map[w.Xtanker][w.Ytanker].Done=true;
                                w.map[w.Xtanker][w.Ytanker].check="Dj";
                                w.cost--;
                            }
                        }
                    w.heurisitc2=w.distanc_h2();
                    w.heurisitc3=w.distanc_h3();
                        w.heurisitc1=w.distanc_h1();
                        this.children.add(w);
                    break;
                }
                case "Left":
                {
                    State w= new State();
                    w.initial_state();
                    w.copy(this);
                    w.parent=this;
                    w.map[w.Xtanker][w.Ytanker].num.add(w.cost+1);
                    if(!(w.map[w.Xtanker][w.Ytanker].s.equals("D")||w.map[w.Xtanker][w.Ytanker].s.equals("P"))){
                        w.map[w.Xtanker][w.Ytanker].check=".";
                    }
                    w.Ytanker--;
                    if(!(w.map[w.Xtanker][w.Ytanker].s.equals("D")||w.map[w.Xtanker][w.Ytanker].s.equals("P"))){
                        w.map[w.Xtanker][w.Ytanker].check="T";
                    }
                    w.cost++;

                    if(w.map[w.Xtanker][w.Ytanker].s.equals("P")){//Receiving site Parcel
                        if(!w.map[w.Xtanker][w.Ytanker].Done)
                        {
                            w.map[w.Xtanker][w.Ytanker].Done=true;
                            w.parcelList.add(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel);
                            w.map[w.Xtanker][w.Ytanker].check="Pj";
                            w.cost++;
                           // w.heurisitc2++;
                        }
                    }
                    else   if(w.map[w.Xtanker][w.Ytanker].s.equals("D"))//Delivery site Parcel
                        if(!w.map[w.Xtanker][w.Ytanker].Done)
                        {
                            if(w.parcelList.contains(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel))
                            {
                                w.parcelList.remove(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel);
                                w.map[w.Xtanker][w.Ytanker].Done=true;
                                w.map[w.Xtanker][w.Ytanker].check="Dj";
                                w.cost--;
                            }
                        }
                    w.heurisitc2=w.distanc_h2();
                    w.heurisitc1=w.distanc_h1();
                    w.heurisitc3=w.distanc_h3();
                        this.children.add(w);
                    break;
                }
                case "Right":
                {
                    State w= new State();
                    w.initial_state();
                    w.copy(this);
                    w.map[w.Xtanker][w.Ytanker].num.add(w.cost+1);
                    w.parent=this;
                    if(!(w.map[w.Xtanker][w.Ytanker].s.equals("D")||w.map[w.Xtanker][w.Ytanker].s.equals("P"))){
                        w.map[w.Xtanker][w.Ytanker].check=".";
                    }
                    w.Ytanker++;
                    if(!(w.map[w.Xtanker][w.Ytanker].s.equals("D")||w.map[w.Xtanker][w.Ytanker].s.equals("P"))){
                        w.map[w.Xtanker][w.Ytanker].check="T";
                    }
                    w.cost++;

                    if(w.map[w.Xtanker][w.Ytanker].s.equals("P")){//Receiving site Parcel
                        if(!w.map[w.Xtanker][w.Ytanker].Done)
                        {
                            w.map[w.Xtanker][w.Ytanker].Done=true;
                            w.parcelList.add(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel);
                            w.map[w.Xtanker][w.Ytanker].check="Pj";
                            w.cost++;
                           // w.heurisitc2++;
                        }
                    }
                    else   if(w.map[w.Xtanker][w.Ytanker].s.equals("D"))//Delivery site Parcel
                        if(!w.map[w.Xtanker][w.Ytanker].Done)
                        {
                            if(w.parcelList.contains(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel))
                            {
                                w.parcelList.remove(w.map[w.Xtanker][w.Ytanker].parcel.typOfParcel);
                                w.map[w.Xtanker][w.Ytanker].Done=true;
                                w.map[w.Xtanker][w.Ytanker].check="Dj";
                                w.cost--;
                            }
                        }
                    w.heurisitc2=w.distanc_h2();
                    w.heurisitc1=w.distanc_h1();
                    w.heurisitc3=w.distanc_h3();
                        this.children.add(w);
                    break;
                }

            }

        }
        return this.children;
    }
    @Override
    public   boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj== null || this.getClass() != obj.getClass())
            return  false;
        State q= (State)obj;
        for (int i=1;i<n-1;i++)
            for(int j=1;j<m-1;j++)
                    if (!this.map[i][j].check.equals(q.map[i][j].check))
                        return false;
        //return this.heurisitc2==((State) obj).heurisitc2;
       // return this.heurisitc1==((State) obj).heurisitc1;
        return this.heurisitc3==((State) obj).heurisitc3;
       // return true;
    }



}
