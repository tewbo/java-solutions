package md2html;

import java.util.List;

public class Emphasis extends HtmlableList {
    public Emphasis(List<Htmlable> list, int type) {
        super(list, type);
    }

    @Override
    protected void putTag(StringBuilder sb) {
        sb.append("em");
    }
}
