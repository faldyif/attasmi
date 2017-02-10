package id.preklit.attasmi;

import java.lang.reflect.Array;

/**
 * Created by FaldyAndZiv on 08/02/2017.
 */

public class Surah {
    // TODO : Uncomment the sample data model
//    private Verse[] verse;
//    private String name;
//    private String translation;
//
//    public Surah(Verse[] verse, String name, String translation) {
//        this.verse = verse;
//        this.name = name;
//        this.translation = translation;
//    }
//
//    public Integer getSumVerse(){
//        return Array.getLength(verse);
//    }
//
//    public Verse[] getVerse() {
//        return verse;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getTranslation() {
//        return translation;
//    }
    private Integer verseCount;
    private String name;
    private String translation;

    public Surah(String name, String translation, Integer verseCount) {
        this.verseCount = verseCount;
        this.name = name;
        this.translation = translation;
    }

    public Integer getSumVerse(){
        return verseCount;
    }

    public String getName() {
        return name;
    }

    public String getTranslation() {
        return translation;
    }
}
