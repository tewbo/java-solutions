package markup;

public interface Markdownable extends Texable {
    void toMarkdown(StringBuilder sb);
}
