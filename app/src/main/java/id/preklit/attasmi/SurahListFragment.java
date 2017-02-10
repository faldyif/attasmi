package id.preklit.attasmi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SurahListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SurahListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SurahListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String[] surahNames = {"Al-Fatihah","Al-Baqarah","Al-'Imran","An-Nisa",
            "Al-Ma'idah","Al-An'am","Al-A'raf","Al-Anfal"};
    String[] surahTranslations = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    Integer[] surahVersesCount = {7, 286, 200, 176, 120, 165, 206, 75};
    ArrayList<Surah> surahs;

    private OnFragmentInteractionListener mListener;

    public SurahListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SurahListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SurahListFragment newInstance(String param1, String param2) {
        SurahListFragment fragment = new SurahListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_surah_list, container, false);

        surahs = new ArrayList<>();

        surahs.add(new Surah("Al-Fatihah", "Pembukaan", 7));
        surahs.add(new Surah("Al-Baqarah", "Sapi Betina", 286));
        surahs.add(new Surah("Al-'Imran", "Keluarga Imran", 200));
        surahs.add(new Surah("Al-Nisa'", "Wanita", 176));
        surahs.add(new Surah("Al-Maidah", "Hidangan", 120));
        surahs.add(new Surah("Al-An'am", "Binatang Ternak", 165));
        surahs.add(new Surah("Al-A'raf", "Tempat Tertinggi", 206));
        surahs.add(new Surah("Al-Anfal", "Harta Rampasan Perang", 75));

        SurahAdapter adapter = new SurahAdapter(surahs, getActivity().getApplicationContext());

        ListView listView = (ListView) view.findViewById(R.id.surah_list);
        listView.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
            OnFragmentInteractionListener apagitu = new OnFragmentInteractionListener() {
                @Override
                public void onFragmentInteraction(Uri uri) {

                }
            };
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
