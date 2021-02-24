package net.ddns.victorlundgren.sudokusolver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CellRowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CellRowFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "row";


    // TODO: Rename and change types of parameters
    private ArrayList<Integer> row;

    public CellRowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param row 3 columns in a row.
     * @return A new instance of fragment CellRowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CellRowFragment newInstance(ArrayList<Integer> row) {
        CellRowFragment fragment = new CellRowFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_PARAM1, row);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @return The cellRow
     */
    public ArrayList<Integer> getRow() {
        int[] ids = {R.id.Col1, R.id.Col2, R.id.Col3};
        row = new ArrayList<>();
        for (int id : ids) {
            EditText text = getActivity().findViewById(id);
            String temp = text.getText().toString();
            if (!"".equals(temp)) {
                row.add(Integer.parseInt(temp));
            } else {
                row.add(0);
            }
        }
        return row;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            row = getArguments().getIntegerArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cell_row, container, false);
    }


}