package markup;

import java.util.List;

public class Paragraph implements ListOrPar {
    List<Markdownable> list;
    public Paragraph(List<Markdownable> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder sb) {
        for (Markdownable markdownable : list) {
            markdownable.toMarkdown(sb);
        }
    }

    @Override
    public void toTex(StringBuilder sb) {
        for (Markdownable texable : list) {
            texable.toTex(sb);
        }
    }
}
