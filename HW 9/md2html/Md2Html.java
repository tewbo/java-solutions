package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    private static void unload(StringBuilder sb, Stack<HtmlableList> elements) {
        if (!sb.isEmpty()) {
            elements.peek().list.add(new Text(sb.toString()));
            sb.setLength(0);
        }
    }

    private static Boolean isText(int i, String line, Stack<HtmlableList> elements) {
        char c = line.charAt(i);
        if ((c == '(' || c == ')') && elements.peek().type != HtmlableList.LINK) {
            return true;
        }
        if (c != '(' && c != ')' && elements.peek().type == HtmlableList.LINK) {
            return true;
        }
        if (c != '*' && c != '_' && c != '[' && c != ']' && c != '(' && c != ')' && c != '-' && c != '`') {
            return true;
        }
        if (c == '-' && line.charAt(i + 1) != '-') {
            return true;
        }
        return (c == '*' || c == '_') && (i + 1 == line.length() || Character.isWhitespace(line.charAt(i + 1))) && (i == 0 || Character.isWhitespace(line.charAt(i - 1)));
    }

    private static void closeElement(Stack<HtmlableList> elements) {
        Htmlable element = elements.peek();
        elements.pop();
        elements.peek().list.add(element);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(args[0]));
            try {
                List<HtmlableList> content = new ArrayList<>();
                Stack<HtmlableList> elements = new Stack<>();
                StringBuilder sb = new StringBuilder();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        if (elements.size() > 1) {
                            System.err.println("Error: Input is not correct");
                            return;
                        } else if (elements.size() == 1) {
                            unload(sb, elements);
                            content.add(elements.peek());
                            elements.clear();
                        }
                        continue;
                    }

                    int i = 0;
                    int hashCnt = 0;
                    if (elements.size() == 0) {
                        while (hashCnt < line.length() && line.charAt(hashCnt) == '#') {
                            hashCnt++;
                        }
                        if (hashCnt > 0 && hashCnt < line.length() && Character.isWhitespace(line.charAt(hashCnt))) {
                            elements.add(new Header(new ArrayList<>(), hashCnt));
                            i = hashCnt + 1;
                        } else {
                            elements.push(new Paragraph(new ArrayList<>()));
                        }
                    } else {
                        sb.append(System.lineSeparator());
                    }

                    for (; i < line.length(); i++) {
                        char c = line.charAt(i);
                        if (isText(i, line, elements)) {
                            if (c == '<') {
                                sb.append("&lt;");
                            } else if (c == '>') {
                                sb.append("&gt;");
                            } else if (c == '&') {
                                sb.append("&amp;");
                            } else if (c == '\\' && i + 1 < line.length()) {
                                sb.append(line.charAt(i + 1));
                                i++;
                            } else {
                                sb.append(c);
                            }
                            continue;
                        }

                        unload(sb, elements);

                        if (line.charAt(i) == '*' || line.charAt(i) == '_') {
                            int type;
                            int typeDouble;
                            if (line.charAt(i) == '*') {
                                type = HtmlableList.ASTERISK;
                                typeDouble = HtmlableList.DOUBLE_ASTERISK;
                            } else {
                                type = HtmlableList.UNDERSCORE;
                                typeDouble = HtmlableList.DOUBLE_UNDERSCORE;
                            }

                            if (i + 1 < line.length() && line.charAt(i + 1) == line.charAt(i)) {
                                if (elements.peek().type == typeDouble) {
                                    closeElement(elements);
                                } else {
                                    elements.add(new Strong(new ArrayList<>(), typeDouble));
                                }
                                i++;
                            } else if (elements.peek().type == type) {
                                closeElement(elements);
                            } else {
                                elements.add(new Emphasis(new ArrayList<>(), type));
                            }
                        } else if (line.charAt(i) == '[') {
                            elements.push(new Link(new ArrayList<>()));
                        } else if (line.charAt(i) == ']') {
                            elements.peek().type = HtmlableList.LINK;
                        } else if (line.charAt(i) == ')') {
                            closeElement(elements);
                        } else if (line.charAt(i) == '-') {
                            if (elements.peek().type == HtmlableList.DASH) {
                                closeElement(elements);
                            } else {
                                elements.push(new Strikeout(new ArrayList<>(), HtmlableList.DASH));
                            }
                            i++;
                        } else if (line.charAt(i) == '`') {
                            if (elements.peek().type == HtmlableList.BACKTICK) {
                                HtmlableList element = elements.peek();
                                elements.pop();
                                elements.peek().list.add(element);
                            } else {
                                elements.push(new Code(new ArrayList<>(), HtmlableList.BACKTICK));
                            }
                        }
                    }
                }

                unload(sb, elements);
                if (elements.size() == 1) {
                    content.add(elements.peek());
                } else if (elements.size() > 1) {
                    System.err.println("Error: Input is not correct");
                    return;
                }
                try {
                    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                        for (Htmlable htmlable : content) {
                            StringBuilder stringBuilderOutput = new StringBuilder();
                            htmlable.toHtml(stringBuilderOutput);
                            writer.write(stringBuilderOutput.toString());
                            writer.write(System.lineSeparator());
                        }

                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Output file is not specified" + e.getMessage());
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Output file with this name does not exist" + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Writer error: " + e.getMessage());
                }


            } finally {
                scanner.close();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Input file is not specified. " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file with this name does not exist. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Reader error: " + e.getMessage());
        }
    }
}
