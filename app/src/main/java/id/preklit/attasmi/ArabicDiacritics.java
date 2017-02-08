package id.preklit.attasmi;

import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
 *
 * @author Ibbtek <http://ibbtek.altervista.org/>
 */
public class ArabicDiacritics {

    private String input;
    private final String output;

    /**
     * ArabicDiacritics constructor
     * @param input String
     */
    public ArabicDiacritics(String input){
        this.input=input;
        this.output=normalize();
    }

    /**
     * normalize Method
     * @return String
     */
    private String normalize(){

        input = Normalizer.normalize(input, Form.NFKD)
                .replaceAll("\\p{M}", "");

        return input;
    }

    /**
     * @return the output
     */
    public String getOutput() {
        return output;
    }

    public static void main(String[] args) {
        String test = "كَلَّا لَا تُطِعْهُ وَاسْجُدْ وَاقْتَرِبْ ۩";
        System.out.println("Before: "+test);
        test=new ArabicDiacritics(test).getOutput();
        System.out.println("After: "+test);
    }
}
