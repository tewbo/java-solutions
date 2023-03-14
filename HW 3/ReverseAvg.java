import java.util.Scanner;
import java.util.Arrays;

public class ReverseAvg {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        int i = 0;
        int j = 0;
        long[] columnSum = new long[1];
        long[] rowSum = new long[1];
        int[] rowCnt = new int[1];
        int[] columnCnt = new int[1];
        int[] list = new int[1];
        while(sc1.hasNextLine()) {
            if (i >= rowSum.length) {
                rowSum = Arrays.copyOf(rowSum, rowSum.length*2);
                rowCnt = Arrays.copyOf(rowCnt, rowCnt.length*2);               
            }
            Scanner sc2 = new Scanner(sc1.nextLine());
            int k = 0;
            while (sc2.hasNextInt()) {
                int x = sc2.nextInt();
                if (j >= list.length) {
                    list = Arrays.copyOf(list, list.length*2);
                }
                if (k >= columnSum.length) {
                    columnSum = Arrays.copyOf(columnSum, columnSum.length*2);
                    columnCnt = Arrays.copyOf(columnCnt, columnCnt.length*2);
                }
                list[j] = x;
                rowSum[i] += x;
                rowCnt[i]++;
                columnSum[k] += x;
                columnCnt[k]++;
                k++;
                j++;
            }
            
            i++;
        }
        j = 0;
        for (int t = 0; t < i; t++) {
            for (int k = 0; k < rowCnt[t]; k++) {
                long val = (rowSum[t] + columnSum[k] - (long)list[j]) / (rowCnt[t] + columnCnt[k] - 1);
                j++;
                System.out.print(val);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
    