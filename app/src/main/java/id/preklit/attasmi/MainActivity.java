package id.preklit.attasmi;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mText;
    private TextView mText2;
    private TextView tampilanAyat;
    private SpeechRecognizer sr;
    private static final String TAG = "TestRecognizeAttasmi";
    private static final Double confidence = 0.9;
    private String str, str2, testCaseSimplified;
    private Integer ayat;
    private Button cekButton;
    String[] testCases = {"بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ",
            "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
            "الرَّحْمَنِ الرَّحِيمِ",
            "مَالِكِ يَوْمِ الدِّينِ",
            "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
            "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ",
            "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
            "Berhasil"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ayat = 0;
        testCaseSimplified = new ArabicDiacritics(testCases[ayat]).getOutput();
        cekButton = (Button) findViewById(R.id.cek);
        tampilanAyat = (TextView) findViewById(R.id.test_case);
        tampilanAyat.setText(testCases[ayat]);
        mText = (TextView) findViewById(R.id.teks);
        mText2 = (TextView) findViewById(R.id.teks2);
        mText.setText(str);
        cekButton.setOnClickListener(this);
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Log.d(TAG, "onReadyForSpeech");
            }

            @Override
            public void onBeginningOfSpeech() {
                Log.d(TAG, "onBeginningOfSpeech");
                cekButton.setText("Mendengarkan...");
            }

            @Override
            public void onRmsChanged(float rmsdB) {
                Log.d(TAG, "onRmsChanged");
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                Log.d(TAG, "onBufferReceived");
            }

            @Override
            public void onEndOfSpeech() {
                Log.d(TAG, "onEndofSpeech");
                cekButton.setText("Cek");
            }

            @Override
            public void onError(int error) {
                Log.d(TAG,  "error " +  error);
                mText.setText("error " + error);
                cekButton.setText("Cek");
            }

            @Override
            public void onResults(Bundle results) {
                String str = new String();
                Log.d(TAG, "onResults " + results);
                ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                for (int i = 0; i < 1; i++)
                {
                    Log.d(TAG, "result " + data.get(i));
                    str += data.get(i);
//                    if(i != data.size() - 1) {
//                        str += " || ";
//                    }
                }
                mText.setText(str);
                cekButton.setText("Cek");
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                Log.d(TAG, "onPartialResults");
                String str2 = new String();
                Log.d(TAG, "onResults " + partialResults);
                ArrayList data = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                for (int i = 0; i < data.size(); i++)
                {
                    Log.d(TAG, "result " + data.get(i));
                    str2 += data.get(i);
                }
//                char[] strChars = str2.toCharArray();
//                for(int i=0; i < str2.length(); i++) {
//                    if(strChars[i] == ' ') {
//                        strChars[i] = '_';
//                    }
//                }
//                str2 = String.valueOf(strChars);
                Log.d(TAG, str2);
                mText2.setText(str2);

                if(StringSimilarity.similarity(testCaseSimplified, str2) >= confidence) {
                    mText2.setText("\nBenar, tingkat kecocokan: " + StringSimilarity.similarity(testCaseSimplified, str2));
                    sr.stopListening();
                    if(ayat != 6) ayat++;
                    testCaseSimplified = new ArabicDiacritics(testCases[ayat]).getOutput();
                    tampilanAyat.setText(testCases[ayat]);
                } else {
                    mText2.setText("\nSalah, tingkat kecocokan: " + StringSimilarity.similarity(testCaseSimplified, str2));
                    Log.d(TAG, str + "|" + testCaseSimplified);
                }

            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                Log.d(TAG, "onEvent " + eventType);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cek) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-SA");
            intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
            sr.startListening(intent);
            cekButton.setText("Mendengarkan...");
            Log.i("111111","11111111");
        }
    }
}
