package id.preklit.attasmi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faldy on 10/02/2017.
 */

public class SurahAdapter extends ArrayAdapter<Surah> implements View.OnClickListener {

    private ArrayList<Surah> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView surahNumber;
        TextView surahName;
        TextView surahTranslation;
        TextView surahVersesCount;
    }

    public SurahAdapter(ArrayList<Surah> data, Context context) {
        super(context, R.layout.activity_listview_surah, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "KeKlik di " + v.getTag().toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Surah surah = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_listview_surah, parent, false);
            viewHolder.surahNumber = (TextView) convertView.findViewById(R.id.surah_number);
            viewHolder.surahName = (TextView) convertView.findViewById(R.id.label);
            viewHolder.surahTranslation = (TextView) convertView.findViewById(R.id.translation);
            viewHolder.surahVersesCount = (TextView) convertView.findViewById(R.id.verse_count);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.surahNumber.setText(position+1 + ".");
        viewHolder.surahName.setText(surah.getName());
        viewHolder.surahTranslation.setText(surah.getTranslation());
        viewHolder.surahVersesCount.setText("(" + surah.getSumVerse().toString() + " ayat)");
        // Return the completed view to render on screen
        return convertView;
//        return super.getView(position, convertView, parent);
    }
}
