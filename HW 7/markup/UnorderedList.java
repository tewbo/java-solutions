package markup;

import java.util.List;

public class UnorderedList extends AbstractTexList{
    public UnorderedList(List<ListItem> list) {
        super(list);
    }

    @Override
    protected void putTexListString(StringBuilder sb) {
        sb.append("itemize");
    }
}
