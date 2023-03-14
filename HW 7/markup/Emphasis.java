package markup;

import java.util.List;

public class Emphasis extends MarkdownableList {
    public Emphasis(List<Markdownable> list) {
        super(list);
    }

    @Override
    protected void putMarkedChar(StringBuilder sb) {
        sb.append('*');
    }

    @Override
    protected void putTexString(StringBuilder sb) {
        sb.append("\\emph");
    }
}
