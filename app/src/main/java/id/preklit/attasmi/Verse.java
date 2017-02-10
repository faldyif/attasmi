package id.preklit.attasmi;

/**
 * Created by Ziv on 10/02/2017.
 */

public class Verse {
    private String text;
    private String translation;

    public Verse(String text, String translation) {
        this.text = text;
        this.translation = translation;
    }

    public String getText() {
        return text;
    }

    public String getTranslation() {
        return translation;
    }
}
