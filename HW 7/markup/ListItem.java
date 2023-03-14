package markup;

import java.util.List;

public class ListItem {
    List<ListOrPar> list;

    public ListItem(List<ListOrPar> list) {
        this.list = list;
    }

    public void toTex(StringBuilder sb) {
        sb.append("\\item ");
        for (ListOrPar uppedTexable : list) {
            uppedTexable.toTex(sb);
        }
    }
}
