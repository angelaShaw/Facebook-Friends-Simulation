package friends;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args){

        try {
            Scanner sc = new Scanner(new File("/Users/angelashaw/Documents/Workspace/JavaProject/friends/T"));
            Graph graph = new Graph(sc);
            System.out.println("--------------------------------------------------------------");
//                F2.shortestChain(graph,"nick","samir");
//            ArrayList<String> x = Friends.shortestChain(graph,"sam","aparna");
//                for(int i = 0; i < x.size(); i++){
//                    System.out.print(x.get(i) + "-----");
//                }

            ArrayList<ArrayList<String>> y = Friends.cliques(graph,"neptune");
                for(int i = 0; i < y.size(); i++){
                    System.out.println("C" + (i+1));
                    for (int j = 0; j < y.get(i).size(); j++)
                        System.out.println(y.get(i).get(j));
                }
//                System.out.println("--------------------------------------------------------------");
//                F2.connectors(graph );
            ArrayList<String> z = Friends.connectors(graph);
                    for(int i = 0; i < z.size(); i++){
                   System.out.print(z.get(i) + "            ");
               }
            System.out.println("--------------------------------------------------------------");


        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
