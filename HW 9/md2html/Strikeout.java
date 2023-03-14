package md2html;

import java.util.List;

public class Strikeout extends HtmlableList {
    public Strikeout(List<Htmlable> list, int type) {
        super(list, type);
    }

    @Override
    protected void putTag(StringBuilder sb) {
        sb.append('s');
    }
}
