package com.example.ndatarea1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_reproductor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_reproductor extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_reproductor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentReproductor.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_reproductor newInstance(String param1, String param2) {
        Fragment_reproductor fragment = new Fragment_reproductor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    Button button;
    Spinner spiner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reproductor, container, false);
        spiner = (Spinner) view.findViewById(R.id.spinner);
        button = (Button) view.findViewById(R.id.button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.generos, android.R.layout.simple_spinner_item);
        spiner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String genero =  (spiner.getSelectedItem().toString().trim());

                //Crear bundle, que son los datos que pasaremos
                Bundle bundle = new Bundle();
                bundle.putString("genero",genero);
                Fragment fragmento = new Fragment_generos();
                // ¡Importante! darle argumentos
                fragmento.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_reproductor, fragmento);
                fragmentTransaction.addToBackStack(null);

// Terminar transición y nos vemos en el fragmento de destino
                fragmentTransaction.commit();


            }
        });

        return view;
    }

    }

