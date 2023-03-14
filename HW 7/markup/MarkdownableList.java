package markup;

import java.util.List;

public abstract class MarkdownableList implements Markdownable {
    protected final List<Markdownable> list;
    public MarkdownableList(List<Markdownable> list) {
        this.list = list;
    }

    protected abstract void putMarkedChar(StringBuilder sb);
    protected abstract void putTexString(StringBuilder sb);

    @Override
    public void toMarkdown(StringBuilder sb) {
        putMarkedChar(sb);
        for (Markdownable markdownable : list) {
            markdownable.toMarkdown(sb);
        }
        putMarkedChar(sb);
    }

    @Override
    public void toTex(StringBuilder sb) {
        putTexString(sb);
        sb.append('{');
        for (Texable texable : list) {
            texable.toTex(sb);
        }
        sb.append('}');
    }
}
