import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppCountLastL {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(args[0]));
            try {
                Map<String, IntList> dictionary = new HashMap<>();
                int wordsCnt = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Map<String, IntList> entrInLine = new HashMap<>();
                    int wordsCntInLine = 0;
                    int pos = -1;
                    for (int i = 0; i <= line.length(); i++) {
                        if (i == line.length() || !(Character.isLetter(line.charAt(i)) || (line.charAt(i) == '\'') || Character.getType(line.charAt(i)) == Character.DASH_PUNCTUATION)) {
                            if (pos != -1) {
                                String word = line.substring(pos, i).toLowerCase();
                                IntList wordEntr = entrInLine.get(word);
                                if (wordEntr == null) {
                                    wordEntr = new IntList();
                                    wordEntr.add(1);    // first element = count of entrances
                                    wordEntr.add(++wordsCnt);   // first entrance
                                    wordEntr.add(++wordsCntInLine);     // last entrance
                                    entrInLine.put(word, wordEntr);
                                } else {
                                    wordEntr.set(0, wordEntr.get(0)+1);
                                    wordEntr.set(2, ++wordsCntInLine);
                                }
                                pos = -1;
                            }
                        } else {
                            if (pos == -1) {
                                pos = i;
                            }
                        }
                    }
                    for (Map.Entry<String, IntList> entry : entrInLine.entrySet()) {
                        IntList wordAllEntr = dictionary.get(entry.getKey());
                        if (wordAllEntr == null) {
                            dictionary.put(entry.getKey(), entry.getValue());
                        } else {
                            wordAllEntr.set(0, wordAllEntr.get(0) + entry.getValue().get(0));
                            wordAllEntr.add(entry.getValue().get(2));
                        }
                    }
                }
                List<Map.Entry<String, IntList>> entries = new ArrayList<>(dictionary.entrySet());
                entries.sort(Map.Entry.comparingByValue());

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8), 512);
                try {
                    for (Map.Entry<String, IntList> entry : entries) {
                        IntList wordEntr = entry.getValue();
                        writer.write(entry.getKey() + " " + wordEntr.get(0));
                        for (int j = 2; j < wordEntr.size(); j++) {
                            writer.write(" " + wordEntr.get(j));
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
