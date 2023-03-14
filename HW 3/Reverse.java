import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

// Changed Reverse

public class Reverse {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        try {
            String[] list1 = new String[1];
            int i = 0;
            while(scanner1.hasNextLine()) {
                if (i >= list1.length) {
                    list1 = Arrays.copyOf(list1, list1.length*2);
                }
                list1[i] = scanner1.nextLine();
                i++;
            }
            for (int t = i-1; t >= 0; t--) {
                Scanner scanner2 = new Scanner(list1[t]);
                int[] list2 = new int[1];
                int k = 0;
                while (scanner2.hasNext()) {
                    if (k >= list2.length)
                    {
                        list2 = Arrays.copyOf(list2, list2.length * 2);
                    }
                    list2[k] = scanner2.nextInt();
                    k++;
                }
                for (int j = k-1; j >= 0; j--) {
                    System.out.print(list2[j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Reader error: " + e.getMessage());
        }
    }
}
    