package md2html;

import java.util.List;

public abstract class HtmlableList implements Htmlable {
    protected final List<Htmlable> list;
    public static final int ASTERISK = 1;
    public static final int DOUBLE_ASTERISK = 2;
    public static final int UNDERSCORE = 3;
    public static final int DOUBLE_UNDERSCORE = 4;
    public static final int DASH = 5;
    public static final int BACKTICK = 6;
    public static final int HEADER = 7;
    public static final int PARAGRAPH = 8;
    public static final int NOT_FINISHED_LINK = 9;
    public static final int LINK = 10;

    public int type;
    public HtmlableList(List<Htmlable> list, int type) {
        this.list = list;
        this.type = type;
    }

    protected abstract void putTag(StringBuilder sb);

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append('<');
        putTag(sb);
        sb.append('>');
        for (Htmlable htmlable : list) {
            htmlable.toHtml(sb);
        }
        sb.append("</");
        putTag(sb);
        sb.append(">");
    }
}
