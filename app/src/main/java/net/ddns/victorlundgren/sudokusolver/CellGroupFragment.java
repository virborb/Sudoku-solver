package net.ddns.victorlundgren.sudokusolver;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CellGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CellGroupFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "cells";

    private ArrayList<Integer> cells;

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

    public ArrayList<Integer> getCells() {
        List<Fragment> list = getChildFragmentManager().getFragments();
        cells = new ArrayList<>();
        for (Fragment f: list) {
            CellRowFragment cellRow = (CellRowFragment) f;
            cells.addAll(cellRow.getRow());
        }
        return cells;
    }

    public void setCells(List<Integer> cells) {
        List<Fragment> list = getChildFragmentManager().getFragments();
        for (Fragment f: list) {
            CellRowFragment cellRow = (CellRowFragment) f;
             cellRow.setRow(cells);
             cells = cells.subList(3, cells.size());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cells = getArguments().getIntegerArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cell_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int ids[] = {R.id.Row1, R.id.Row2, R.id.Row3};
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        for (int i = 0; i < 3; i++) {
            transaction.add(ids[i], new CellRowFragment(), Integer.toString(i));
            transaction.addToBackStack(Integer.toString(i));
        }
        transaction.commit();
    }
}