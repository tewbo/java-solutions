package md2html;

public class Text implements Htmlable {
    String text;
    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(text);
    }
}
