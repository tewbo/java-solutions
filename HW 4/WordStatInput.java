import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class WordStatInput {
    
    public static void main(String args[]) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf8"), 512);
            try {
                char[] buffer = new char[512];
                int read = reader.read(buffer);
                char[] arr = new char[1];
                int cnt = 0;
                long M = 1_000_000_007;
                long base = 9973;
                while (read >= 0) {
                    for (int i = 0; i < read; i++) {
                        if (cnt >= arr.length) {
                            arr = Arrays.copyOf(arr, arr.length*2);
                        }
                        arr[cnt++] = buffer[i];
                    }
                    read = reader.read(buffer);
                }
                
                int ptr = 0;
                int[] hashes = new int[1];
                int[] wordPos = new int[1];
                int[] wordLen = new int[1];
                int pos = -1;
                for (int i = 0; i <= cnt; i++) {
                    if (i == cnt || !(Character.isLetter(arr[i]) || (arr[i] == '\'') || Character.getType(arr[i]) == 20)) {
                        if (pos != -1) {
                            String str = (new String(arr, pos, i-pos)).toLowerCase();
                            char[] charArr = str.toCharArray();
                            long hash = 0;
                            for (int j = 0; j < i - pos; j++) {
                                hash *= base;
                                hash %= M;
                                hash += Character.codePointAt(charArr, j);
                                hash %= M;
                            }
                            if (ptr >= hashes.length) {
                                hashes = Arrays.copyOf(hashes, hashes.length*2);
                                wordPos = Arrays.copyOf(wordPos, wordPos.length*2);
                                wordLen = Arrays.copyOf(wordLen, wordLen.length*2);
                            }
                            hashes[ptr] = (int)hash;
                            wordPos[ptr] = pos;
                            wordLen[ptr] = i-pos;
                            ptr++;
                            pos = -1;
                        }
                    } else {
                        if (pos == -1) {
                            pos = i;
                        }
                    }
                }
                int[] sortedHashes = Arrays.copyOf(hashes, ptr);
                Arrays.sort(sortedHashes);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF8"), 512);
                try {
                    int[] used = new int[ptr];
                    for (int i = 0; i < ptr; i++) {
                        int l = -1, r = ptr-1;
                        while (l + 1 < r) {
                            int mid = (l + r) / 2;
                            if (sortedHashes[mid] >= hashes[i]) {
                                r = mid;
                            } else {
                                l = mid;
                            }
                        }
                        int lowerBound = r;
                        if (used[lowerBound] == 1) {
                            continue;
                        }
                        l = 0;
                        r = ptr;
                        while (l + 1 < r) {
                            int mid = (l + r) / 2;
                            if (sortedHashes[mid] <= hashes[i]) {
                                l = mid;
                            } else {
                                r = mid;
                            }
                        }
                        int upperBound = r;
                        used[lowerBound] = 1;
                        writer.write((new String(arr, wordPos[i], wordLen[i])).toLowerCase());
                        writer.write(" ");
                        writer.write(Integer.toString(upperBound-lowerBound));
                        writer.newLine();
                    }
                } finally {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Required file not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}