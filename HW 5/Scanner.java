import java.io.*;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class Scanner {
    private final Reader reader;
    private final char[] buffer;
    private final int BUFFER_SIZE = 1024;
    private int ptr;
    private int read;

    public Scanner(InputStream source) {
        reader = new InputStreamReader(source, StandardCharsets.UTF_8);
        buffer = new char[BUFFER_SIZE];
        ptr = 0;
        read = 0;
    }

    public Scanner(String source) {
        reader = new StringReader(source);
        buffer = new char[BUFFER_SIZE];
        ptr = 0;
        read = 0;
    }

    private void bufferUpdate() throws IOException {
        if (ptr == read) {
            read = reader.read(buffer);
            ptr = 0;
        }
    }

    public boolean hasNextLine() throws IOException {
        bufferUpdate();
        return read != -1;
    }

    public boolean hasNext() throws IOException {
        bufferUpdate();
        while (read != -1 && Character.isWhitespace(buffer[ptr])) {
            ptr++;
            bufferUpdate();
        }
        return read != -1;
    }

    public String next() throws IOException {
        if (!hasNext()) {
            throw new NoSuchElementException("There is no input data");
        }
        char[] array = new char[1];
        int charPtr = 0;
        while (read != -1 && !Character.isWhitespace(buffer[ptr])) {
            if (charPtr == array.length) {
                array = Arrays.copyOf(array, array.length * 2);
            }
            array[charPtr++] = buffer[ptr++];
            bufferUpdate();
        }
        return new String(array, 0, charPtr);
    }

    public String nextLine() throws IOException {
        char[] line = new char[1];
        int linePtr = 0;
        bufferUpdate();
        while (buffer[ptr] != '\n' && buffer[ptr] != '\r' && read != -1) {
            if (linePtr >= line.length) {
                line = Arrays.copyOf(line, line.length * 2);
            }
            line[linePtr++] = buffer[ptr++];
            bufferUpdate();
        }
        if (read != -1 && buffer[ptr] == '\r') {
            ptr++;
            bufferUpdate();
        }
        if (read != -1 && buffer[ptr] == '\n') {
            ptr++;
            bufferUpdate();
        }
        return new String(line, 0, linePtr);
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next(), 10);
    }

    public int nextIntOct() throws IOException {
        String word = next();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i)) || word.charAt(i) == '-') {
                sb.append(word.charAt(i));
            } else if (Character.isLetter(word.charAt(i))) {
                if (word.charAt(i) - 'a' < 10 && word.charAt(i) - 'a' >= 0) {
                    sb.append((char) (word.charAt(i) - 'a' + '0'));
                } else if (i != word.length() - 1 || (word.charAt(i) != 'o' && word.charAt(i) != 'O')) {
                    throw new NumberFormatException("Incorrect int format");
                }
            } else {
                throw new NumberFormatException("Incorrect int format");
            }
        }
        if (word.charAt(word.length() - 1) == 'O' || word.charAt(word.length() - 1) == 'o') {
            return Integer.parseUnsignedInt(sb.toString(), 8);
        }
        return Integer.parseInt(sb.toString(), 10);
    }

    public void close() throws IOException {
        reader.close();
    }
}