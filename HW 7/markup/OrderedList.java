package markup;

import java.util.List;

public class OrderedList extends AbstractTexList {
    public OrderedList(List<ListItem> list) {
        super(list);
    }

    @Override
    protected void putTexListString(StringBuilder sb) {
        sb.append("enumerate");
    }
}
