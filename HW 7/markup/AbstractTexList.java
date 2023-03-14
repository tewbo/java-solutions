package markup;

import java.util.List;

public abstract class AbstractTexList implements ListOrPar {
    protected final List<ListItem> list;

    protected AbstractTexList(List<ListItem> list) {
        this.list = list;
    }

    protected abstract void putTexListString(StringBuilder sb);

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\begin{");
        putTexListString(sb);
        sb.append("}");
        for (ListItem listItem : list) {
            listItem.toTex(sb);
        }
        sb.append("\\end{");
        putTexListString(sb);
        sb.append("}");
    }
}
