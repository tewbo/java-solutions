package markup;

import java.util.List;

public class Strikeout extends MarkdownableList {
    public Strikeout(List<Markdownable> list) {
        super(list);
    }


    @Override
    protected void putMarkedChar(StringBuilder sb) {
        sb.append('~');
    }

    @Override
    protected void putTexString(StringBuilder sb) {
        sb.append("\\textst");
    }
}
