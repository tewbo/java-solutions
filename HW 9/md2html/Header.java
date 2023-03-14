package md2html;

import java.util.List;

public class Header extends HtmlableList {
    public final int level;
    public Header(List<Htmlable> list, int level) {
        super(list, HtmlableList.HEADER);
        this.level = level;
    }

    @Override
    protected void putTag(StringBuilder sb) {
        sb.append('h');
        sb.append(level);
    }
}
