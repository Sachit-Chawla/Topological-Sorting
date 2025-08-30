/**
 * Author@ Sachit Singh Chawla
 * B00865842
 * This is the topological algorithm,
 * it sorts all the graph with the highest priority by using arraylists and gives the output of the sorted list
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class topologicalSorting {
    public static void main ( String[] args ) throws FileNotFoundException {
        Scanner in = new Scanner ( System.in );

        //Input
        System.out.print ( "Enter the filename to read from: " );
        String filename = in.nextLine ( );
        File file = new File ( filename );
        Scanner inputFile = new Scanner ( file );

        //Number of vertices
        int n = Integer.parseInt ( inputFile.next ( ) );
        //create a 2d array of nxn for adjacent matrix
        int[][] adj = new int[n][n];

        // Taking inputs for vertices and saving them in an adjacent matrix and converting them into integers
        while (inputFile.hasNext ( )) {
            int v0 = inputFile.next ( ).charAt ( 0 ) - 65;
            int v1 = inputFile.next ( ).charAt ( 0 ) - 65;
            adj[v0][v1] = 1;
        }
        inputFile.close ();

        //Prints the 2d adjacent matrix
        for (int i = 0; i < n; i++) {
            System.out.print("\t"+(char)(i+65));
        }
        System.out.println ( );
        for (int i = 0; i < adj.length; i++) {
            System.out.print((char)(i+65));
            for (int j = 0; j < adj[0].length; j++) {
                System.out.print ("\t"+adj[i][j] );
            }
            System.out.println ( );
        }
        System.out.println ( );
        //Three queues for queue: checks the neighbour vertex
        //                 pred:  keeps the count of all the vertices having arrows coming
        //                 topNUm: keeps the vertices with first priority
        ArrayList<Integer> queue = new ArrayList<> ( );
        ArrayList<Integer> pred = new ArrayList<> ( );
        ArrayList<Integer> topNum = new ArrayList<> ( );
        //Helper array to see if the element was already added to queue or not and it should be added just once
        // , sets false when not added and true if added
        boolean[] flagged = new boolean[n];
        for (int i = 0; i < flagged.length; i++) {
            flagged[i] = false;
        }
        //Counts all the directed arrows coming inside the alphabet by going column vise and adding them and then saves it into
        //the predecessor count arraylist
        for (int i = 0; i < adj.length; i++) {
            int vertexCount = 0;
            for (int j = 0; j < adj[0].length; j++) {
                if ( adj[j][i] == 1 ) {
                    vertexCount++;
                }
            }
            //This puts the vertex count as A-->B-->C-->D-->E
            pred.add ( vertexCount );
        }
        // adds all the vertices to the queue which have the highest priority
        for (int i = 0; i < adj.length; i++) {
            if ( pred.get ( i ) == 0 && flagged[i] == false ) {
                queue.add ( i );
                //Flags to identify it to be already added to the queue once
                flagged[i] = true;
            }
        }

        /* This while loop is all of the Topological sorting algorithm
        it adds to the addNum who are on index 0 = w and then adds neighbours of w to the queue
        then sees if the elements contained in the queue have vertex count of 0 and then adds them to
        addNum, repeats till the queue is empty
         */
        int count = 0;
        while (! queue.isEmpty ( )) {
            int w = queue.remove ( 0 );
            topNum.add ( count, w );
            count++;
            for (int i = 0; i < adj.length; i++) {
                if ( adj[w][i] == 1 ) {
                    pred.set( i,pred.get ( i )-1 );
                    if ( pred.get ( i ) == 0 && !(flagged[i] ) ) {
                        queue.add ( i );
                        //Flags to identify it to be already added to the queue once
                        flagged[i] = true;
                    }
                }
            }
        }

        //output
        System.out.print("topnum:      ");
        for (int i = 1; i <= topNum.size (); i++) {
            System.out.print(i + "  ");
        }
        System.out.println ( );
        System.out.print("             ");
        for (int i = 0; i < topNum.size ( ); i++) {
            //converts the integers into alphabets
            System.out.print((char)(topNum.get ( i )+65) + "  ");
        }
    }
}
