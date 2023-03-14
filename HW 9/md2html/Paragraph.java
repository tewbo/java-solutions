package md2html;

import java.util.List;

public class Paragraph extends HtmlableList {
    public Paragraph(List<Htmlable> list) {
        super(list, HtmlableList.PARAGRAPH);
    }

    @Override
    protected void putTag(StringBuilder sb) {
        sb.append('p');
    }
}
