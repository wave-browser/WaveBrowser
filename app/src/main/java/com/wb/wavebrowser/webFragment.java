package com.wb.wavebrowser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link webFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class webFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "test2";
    private static final String ARG_PARAM2 = "test1";

    private String test2;
    private String test1;

    public webFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param test2 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment webFragment.
     */
=    public static webFragment newInstance(String test2, String param2) {
        webFragment fragment = new webFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, test2);
        args.putString(ARG_PARAM2, test2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            test2 = getArguments().getString(ARG_PARAM1);
            test1 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web, container, false);



    }
}