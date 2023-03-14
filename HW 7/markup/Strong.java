package markup;

import java.util.List;

public class Strong extends MarkdownableList {
    public Strong(List<Markdownable> list) {
        super(list);
    }


    @Override
    protected void putMarkedChar(StringBuilder sb) {
        sb.append("__");
    }

    @Override
    protected void putTexString(StringBuilder sb) {
        sb.append("\\textbf");
    }
}
