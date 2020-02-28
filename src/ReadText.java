import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.text.*;


public class ReadText 
{ 

//  public static String readFile(String fileName)throws Exception
//  {
//    //    String data = "";
//    //    data = new String(Files.readAllBytes(Paths.get(fileName)));
//    //    return data;
//  }

  public static boolean test(int[] a) {
    boolean ret=true;
    // Put all the values into a HashSet
    HashSet<Integer> values = new HashSet();
    for (int i : a) {
      values.add(i);
    }
    // 1) check consecutive number and see if the length < 3
    // 2) check if all even or all odd
    // 3) check if at least 4/5 of the numbers have the same single digit
    // 4) check if at least 4/5 of the numbers come from the same ten range
    // 5) check if at least 4/5 of the numbers are consecutive multiples of integer a
    // 6) check if at least 4/5 of the numbers have the same difference
    // (e.g. 3 5 7 9 11 with the same difference of 2) (case 6 includes case 5)
    int maxLength = 0;
    int odd=0;
    int[] remainder = {0,0,0,0,0,0,0,0,0,0};
    int[] tenRange = {0,0,0,0,0,0,0,0};
    int dif = Integer.MAX_VALUE;
    int prev = -1; // previous value
    int temp;
    int countdif = 0;

    for (int i : values) {
      odd=i%2==0?odd:odd+1;
      remainder[i%10]++;
      tenRange[i/10]++;
      if (prev==-1) prev = i;
      else{
        temp = i-prev;
        if (dif==temp) countdif+=1;
        else countdif = 0;
        dif = temp;
      }

      if (values.contains(i - 1)) continue;
      int length = 0;
      while (values.contains(i++)) length++;
      maxLength = Math.max(maxLength, length);
    }
    ret = ret && maxLength<3;
    ret = ret && !(odd==5 || odd==0);
    for (int j: remainder){if (j>=4) ret=false;}
    for (int k: tenRange){if (k>=4) ret=false;}
    ret = ret && countdif<4;
    return ret;
  }

  public static void main(String[] args) throws Exception 
  {
//      File file1 = new File("src/mega.txt");
    File file1 = new File("powerball.csv");
    File file2 = new File("src/Output.txt");
    BufferedReader br = new BufferedReader(new FileReader(file1));
    BufferedWriter output = new BufferedWriter(new FileWriter(file2));
    String[] query;
    int[] num = new int[5];
    boolean ret;
    int count=0;
    for (int k = 0; k < 1051; k++) {
//    for (int k = 0; k < 12103013; k++) {
      String opString="";
      query = br.readLine().split(",");
        for (int i=4;i<9;i++)
//      for (int i=0;i<5;i++)
          num[i-4] = Integer.parseInt(query[i]);
      ret = test(num);
      if (ret){
        count++;
        for (int j=0;j<5;j++) opString+=query[j]+" ";
        output.write(opString);
        output.newLine();
      }
    }
    System.out.println("here's the result: "+ count);
    output.close();
  }

}

