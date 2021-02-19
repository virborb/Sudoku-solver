package net.ddns.victorlundgren.sudokusolver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CellRowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CellRowFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "col1";
    private static final String ARG_PARAM2 = "col2";
    private static final String ARG_PARAM3 = "col3";

    // TODO: Rename and change types of parameters
    private int col1;
    private int col2;
    private int col3;

    public CellRowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param col1 column 1.
     * @param col2 column 2.
     * @param col3 column 3.
     * @return A new instance of fragment CellRowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CellRowFragment newInstance(int col1, int col2, int col3) {
        CellRowFragment fragment = new CellRowFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, col1);
        args.putInt(ARG_PARAM2, col2);
        args.putInt(ARG_PARAM2, col3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            col1 = getArguments().getInt(ARG_PARAM1);
            col2 = getArguments().getInt(ARG_PARAM2);
            col3 = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cell_row, container, false);
    }
}