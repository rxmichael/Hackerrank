import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AngryChildren {

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int nopackets = sc.nextInt();
        if(nopackets > Math.pow(10,5))
            throw new Exception();
        int size = sc.nextInt();
        int[] candies = new int[nopackets];
        for(int i=0; i<nopackets; i++)
        {
            candies[i] = sc.nextInt();
        }
        Arrays.sort(candies);
        int[] fairness = new int[size];
        int min = candies[candies.length-1] - candies[0];
        for(int j=0; j<candies.length - size; j++)
        {
            if((candies[j+size-1] - candies[j]) < min)
                min = candies[j+size-1] - candies[j];
        }
        System.out.println(min);
    }
}