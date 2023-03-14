import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(args[0]));
            try {
                Map<String, IntList> dictionary = new LinkedHashMap<>();
                int wordsCnt = 0;
                while (scanner.hasNext()) {
                    String str = scanner.next();
                    int pos = -1;
                    for (int i = 0; i <= str.length(); i++) {
                        if (i == str.length() || !(Character.isLetter(str.charAt(i)) || (str.charAt(i) == '\'') || Character.getType(str.charAt(i)) == Character.DASH_PUNCTUATION)) {
                            if (pos != -1) {
                                String word = str.substring(pos, i).toLowerCase();
                                wordsCnt++;
                                IntList wordInstances = dictionary.getOrDefault(word, null);
                                if (wordInstances == null) {
                                    wordInstances = new IntList();
                                    wordInstances.add(wordsCnt);
                                    dictionary.put(word, wordInstances);
                                } else {
                                    wordInstances.add(wordsCnt);
                                }
                                pos = -1;
                            }
                        } else {
                            if (pos == -1) {
                                pos = i;
                            }
                        }
                    }
                }
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8), 512);
                try {
                    for (Map.Entry<String, IntList> entry : dictionary.entrySet()) {
                        IntList wordInstances = entry.getValue();
                        writer.write(entry.getKey() + " " + wordInstances.size());
                        for (int j = 0; j < wordInstances.size(); j++) {
                            writer.write(" " + wordInstances.get(j));
                        }
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
                scanner.close();
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
