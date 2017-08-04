/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seiveoptimization;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ram bablu
 */
public class SieveOptimization {
    static int MAX = 1001;
    
    /**
     * Constructor
     * @throws IOException 
     */
    public SieveOptimization() throws IOException{
    
        int T = 20;
        while(T-- > 0){
            MAX = 1001;
            PrintWriter p = new PrintWriter( new BufferedWriter( new FileWriter("Results"+T+".csv") ) );
            try{
                p.println("Input,Original Seive,Optimized Seive,Clashes");
                while(MAX < 900000010 ) {
                    boolean seive[] = new boolean[MAX];
                    long t = System.currentTimeMillis();
                    original_seive(seive);
                    long t1 = System.currentTimeMillis()-t;
                    seive = null;
                    seive = new boolean[MAX];
                    long t2 = System.currentTimeMillis();
                    optimized_seive(seive);
                    long t3 = System.currentTimeMillis()-t2;
                    String clashes = "No Clashes";
                    p.println(MAX+","+(t1)+","+(t3)+","+clashes);
                    //System.out.println(MAX+"\t\t"+(t1)+"\t"+(t3)+"\t"+clashes);
                    if( MAX < 100000000 ) {
                        MAX--;
                        MAX *= 10;
                        MAX++;
                    } else {
                        MAX +=100000000;
                    }
                }
                System.out.println("Task Completed : "+T);
                p.close();
            } catch(OutOfMemoryError e) {
                System.out.println("Task Interrupted :"+T);
                p.close();
            }
        }
    
    }
    
    /**
     * This Method takes a boolean array and a prime number index i
     * It sets all the factors of i to non primes starting from i*i.
     * 
     * @param seive
     * @param i 
     */
    private static void make_fake_primes(boolean[] seive, int i) {
        for( int j = i*i; j < MAX; j+=i) 
            seive[j] = true;
    }
    
    /**
     * Original sieve method.
     * 
     * @param sieve 
     */
    private static void original_seive( boolean[] sieve ) { 
        sieve[0] = true;
        sieve[1] = true;
	int sqrt = (int) Math.sqrt(MAX);
        for( int i = 2; i < sqrt; i++ ) {
            if( sieve[i] == false ) {
                make_fake_primes(sieve, i);
            }
        }
    }

    private static void optimized_seive( boolean[] sieve ) { 
        sieve[0] = true;
        sieve[1] = true;
    
        make_fake_primes(sieve, 2);
        make_fake_primes(sieve, 3);
        int sqrt = (int) Math.sqrt(MAX);
        for( int i = 6; i < sqrt; i+=6 ) {
            if( !sieve[i-1] ) {
                make_fake_primes(sieve, i-1);
            } 
            if( !sieve[i+1] ) {
                make_fake_primes(sieve, i+1);
            }
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        SieveOptimization seiveOptimization = new SieveOptimization();
        System.out.println("Execution Finished");
        AvgOutput avgoutput = new AvgOutput();
            
    }
    
}
