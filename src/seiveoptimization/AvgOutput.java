/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seiveoptimization;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author ram bablu
 */

class Obj{
    long val;
    int original_run = 0;
    int optimized_run = 0;
    
    double mean_original = 0;
    double mean_optimal = 0;
    
    public Obj(){}
    
    public void update( long val,int o_r, int op_r) {
        this.val = val;
        this.original_run += o_r;
        this.optimized_run += op_r;
        //System.out.println(val+"\t\t"+original_run+"\t"+optimized_run);
    }

    String findMean(int n) {
        mean_original = this.original_run / n;
        mean_optimal = this.optimized_run / n;
        return (val+","+mean_original+","+mean_optimal);
    }
}

public class AvgOutput {
    private static final int MAX = 20;
    public AvgOutput() throws FileNotFoundException, IOException {
        int T = MAX;
        Obj resultSet[] = new Obj[11];
        for( int i = 0; i < 11; i++) {
            resultSet[i] = new Obj();
        }
        while(T-- > 0) {
            
            try (Scanner in = new Scanner(new File("Results"+(T)+".csv"))) {
                
                in.useDelimiter(",");
                in.nextLine();

                int i = 0;
                while(in.hasNextLine()) {
                    resultSet[i].update(in.nextLong(), in.nextInt(), in.nextInt());
                    in.nextLine();
                    i++;
                }
                
            }
            new File("Results"+(T)+".csv").delete();            
        }
        
        PrintWriter p = new PrintWriter( new BufferedWriter( new FileWriter("Output.csv") ) );
        p.println("Input,Original Runtime,Optimized RunTime");
        for( int i = 0; i < 11; i++) {
            p.println(resultSet[i].findMean(MAX));
        }
        p.close();
    }
}
