import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.*;
import java.io.*;


public class ESort
{
 static int v = 107374182; 
 static int b = 1000000; 

 public static void mergeSort(String[] x) 
    {
        String[] l = new String[];
	String[] r = new String[];
        int center;
 
        if (x.length >= 2) {    
            center = x.length / 2;
            // copy the left half of x into the l.
            for (int i=0; i<center; i++) {
                    l[i] = x[i];
            }
 
            //copy the right half of x into the r.
            for (int i=center; i< x.length; i++) {
                    r[i] = x[i];
            }
 
            // Sort the left and right halves of the arraylist.
            mergeSort(l);
            mergeSort(r);
 
            // Merge the results back together.
            merge(l, r, x);
        }
   }

  public static void merge(String[] l, String[] r, String[] result) 
    {
        int j1 = 0;
        int j2 = 0;
	while(j1 < l.length && j2 < r.length)
	{
            if ((l[j1].substring(0,10).compareTo(r[j2].substring(0,10)))<0)  {
                      result[i] = l[j1];
                      j1++;
              } 
	      else 
	      {
                      result[i] = r[j2];
                      j2++;
               }
		i++;
         }
    }



 
 public static void esort(File file)
 {
  String t = "t-file-";
  String buffer[] =  new String[b < v ? b : v];
  
  try
  {
   FileReader fr = new FileReader(file);
   BufferedReader br = new BufferedReader(fr);
   int s = (int) Math.ceil((double) v/b);
   int m=0, k=0;
   for (m = 0; m < s; m++)
   {
    for (k = 0; k < (b < v ? b : v); k++)
    {
     String t1 = br.readLine();
     if (t1 != null)
     {
      buffer[k] = t1;
     }
     else
     {
      break;
     }
    }
      mergeSort(buffer);
      FileWriter fw = new FileWriter(t + Integer.toString(i) + ".txt");
      PrintWriter pw = new PrintWriter(fw);
      for (int l = 0; l < k; l++)
      pw.println(buffer[l]);
      pw.close();
      fw.close();
    }   
   br.close();
   fr.close();
  }   

  catch (IOException e)
  {
   e.printStackTrace();
  }

}
   
  

        
 public static void main(String[] args)
 {
  long start = System.currentTimeMillis(); 
 File file = new File("dataset10gb.txt");
  esort(file);
 long end = System.currentTimeMillis();
 System.out.println("Time taken: " + (end - start));
 }
}
