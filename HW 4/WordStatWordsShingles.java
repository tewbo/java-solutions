import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
import java.nio.charset.StandardCharsets;
import java.lang.IndexOutOfBoundsException;

public class WordStatWordsShingles {
    public static final int BUFFER_SIZE = 512;
    public static final int SUBSTR_LEN = 3;
    public static void main(String args[]) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8.name()), BUFFER_SIZE);
            Map<String, Integer> dictionary = new HashMap<>();
            String[] keys = new String[1];
            try {
                char[] buffer = new char[512];
                int read = reader.read(buffer);
                char[] arr = new char[1];
                int cnt = 0;
                while (read >= 0) {
                    for (int i = 0; i < read; i++) {
                        if (cnt >= arr.length) {
                            arr = Arrays.copyOf(arr, arr.length * 2);
                        }
                        arr[cnt++] = buffer[i];
                    }
                    read = reader.read(buffer);
                }
                int ptr = 0;
                int pos = -1;
                for (int i = 0; i <= cnt; i++) {
                    if (i == cnt || !(Character.isLetter(arr[i]) || (arr[i] == '\'') || Character.getType(arr[i]) == Character.DASH_PUNCTUATION)) {
                        if (pos != -1) {
                            int j = 0;
                            do {
                                String str = (new String(arr, pos + j, Math.min(3, i - pos))).toLowerCase();
                                int val = 0;
                                if (dictionary.containsKey(str) == false) {
                                    if (ptr >= keys.length) {
                                        keys = Arrays.copyOf(keys, keys.length*2);
                                    }
                                    keys[ptr] = str;
                                    ptr++;
                                } else {
                                    val = dictionary.get(str);
                                }
                                dictionary.put(str, val + 1);
                                j++;
                            } while (pos + j + SUBSTR_LEN <= i);
                            pos = -1;
                        }
                    } else {
                        if (pos == -1) {
                            pos = i;
                        }
                    }
                }
                keys = Arrays.copyOf(keys, ptr);
                Arrays.sort(keys);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8.name()), BUFFER_SIZE);
                try {
                    for (int i = 0; i < ptr; i++) {
                        writer.write(keys[i]);
                        writer.write(" ");
                        writer.write(Integer.toString(dictionary.get(keys[i])));
                        writer.newLine();
                    }                    
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The output file is not specified. " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Writer error: " + e.getMessage());
                } finally {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Reader error: " + e.getMessage());
            } finally {
                reader.close();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The input file is not specified. " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Reader error: Required file not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}