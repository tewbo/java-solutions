package md2html;

import java.util.List;

public class Link extends HtmlableList{
    public Link(List<Htmlable> list) {
        super(list, HtmlableList.NOT_FINISHED_LINK);
    }

    @Override
    protected void putTag(StringBuilder sb) {
        // not used
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<a href='");
        list.get(list.size()-1).toHtml(sb);
        sb.append("'>");
        for (int i = 0; i < list.size()-1; i++) {
            list.get(i).toHtml(sb);
        }
        sb.append("</a>");
    }
}
