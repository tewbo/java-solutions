package md2html;

import java.util.List;

public class Strong extends HtmlableList {
    public Strong(List<Htmlable> list, int type) {
        super(list, type);
    }

    @Override
    protected void putTag(StringBuilder sb) {
        sb.append("strong");
    }
}
