package net.ddns.victorlundgren.sudokusolver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CellGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CellGroupFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "cells";

    private int[] cells;

    public CellGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cells numbers to put in cells.
     * @return A new instance of fragment CellGroupFragment.
     */
    public static CellGroupFragment newInstance(int[] cells) {
        CellGroupFragment fragment = new CellGroupFragment();
        Bundle args = new Bundle();
        args.putIntArray(ARG_PARAM1, cells);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cells = getArguments().getIntArray(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cell_group, container, false);
    }
}