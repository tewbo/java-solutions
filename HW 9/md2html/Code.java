package md2html;

import java.util.List;

public class Code extends HtmlableList {
    public Code(List<Htmlable> list, int type) {
        super(list, type);
    }

    @Override
    protected void putTag(StringBuilder sb) {
        sb.append("code");
    }
}
