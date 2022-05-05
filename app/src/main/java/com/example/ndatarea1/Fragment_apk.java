package com.example.ndatarea1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_apk#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_apk extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_apk() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_apk.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_apk newInstance(String param1, String param2) {
        Fragment_apk fragment = new Fragment_apk();
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
    ImageButton correo,yt,camara,maps;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apk, container, false);
        correo = view.findViewById(R.id.ibgmail);
        yt =view.findViewById(R.id.ibyoutube);
        camara= view.findViewById(R.id.ibcamara);
        maps= view.findViewById(R.id.ibmaps);
        correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open2 = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(open2);
            }
        });
        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open2 = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                startActivity(open2);
            }
        });
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open2 = getActivity().getPackageManager().getLaunchIntentForPackage("com.android.camera2");
                startActivity(open2);
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open2 = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
                startActivity(open2);
            }
        });


        return  view;
    }
}