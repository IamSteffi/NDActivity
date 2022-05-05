package com.example.ndatarea1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_generos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_generos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_generos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_generos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_generos newInstance(String param1, String param2) {
        Fragment_generos fragment = new Fragment_generos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String genero = bundle.getString("genero");
            }
        });

    }
    MediaPlayer mp;// = MediaPlayer.create(getActivity(),R.raw.i3);
    ImageButton playpause;
    TextView duracion;
    TextView tvGenero;
    ProgressBar progreso;
    ImageView portada;
    TextView titulo;
    TextView descripcion;

    Boolean play = false;
    Integer contador = 0;
    String genero = "";
    String url = "";

    CountDownTimer timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generos, container, false);
        genero=getArguments().getString("genero");
        playpause = (ImageButton) view.findViewById(R.id.playpause);
        duracion = (TextView)view.findViewById(R.id.Duracion);
        tvGenero = (TextView)view.findViewById(R.id.txvGenero);
        progreso = (ProgressBar)view.findViewById(R.id.progressBar);
        portada = (ImageView) view.findViewById(R.id.ImgPortada);
        titulo = (TextView)view.findViewById(R.id.TituloCancion);
        descripcion = (TextView)view.findViewById(R.id.Descripcion);

        tvGenero.setText(genero);
        mp = MediaPlayer.create(getActivity(),R.raw.rr1);
        Reproducir(contador,genero);
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        if(play)
        {
            playpause.setImageResource(R.drawable.playbutton);
            play = false;
            mp.pause();
            timer.cancel();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        playpause.setImageResource(R.drawable.pausebutton);
        play = true;
        mp.start();
    }
    public void startTimer()
    {
        timer = new CountDownTimer(mp.getDuration(),1000) {
            @Override
            public void onTick(long l) {
                Duracion();
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void Duracion()
    {
        double tiempo = mp.getDuration();
        tiempo /= 1000;
        int minutos = (int)(tiempo / 60.0);
        int segundos = (int)(tiempo % 60.0);
        String total = String.format("%02d:%02d",minutos,segundos);
        String actual = "";
        tiempo = mp.getCurrentPosition();
        tiempo /= 1000;
        minutos = (int)(tiempo / 60.0);
        segundos = (int)(tiempo % 60.0);
        actual = String.format("%02d:%02d",minutos,segundos);
        duracion.setText(total + " / "+actual);
        progreso.setProgress((int)tiempo);
    }


    public void playpause(View view) {

        if(play)
        {
            playpause.setImageResource(R.drawable.playbutton);
            play = false;
            mp.pause();
            timer.cancel();
        }
        else
        {
            playpause.setImageResource(R.drawable.pausebutton);
            play = true;
            mp.start();

        }
    }

    public void Stop(View view) {
        mp.seekTo(0);
        mp.pause();
        playpause.setImageResource(R.drawable.playbutton);
        play = false;
    }

    public void Avanzar(View view) {

        contador++;
        if(contador == 5)
            contador = 0;
        Reproducir(contador,genero);
    }
    public void Retroceder(View view) {
        contador--;
        if(contador == -1)
            contador = 4;
        Reproducir(contador,genero);
    }
    public void play()
    {
        mp.start();
        playpause.setImageResource(R.drawable.pausebutton);
        play = true;
    }
    public void paginaurl(View view) {
        if(url!=null)
        {
            Uri link = Uri.parse(url);
            Intent i = new Intent(Intent.ACTION_VIEW,link);
            startActivity(i);
        }
    }
    public void Reproducir(Integer nro,String genero)
    {
        switch (genero)
        {
            case "Rock":
            {
                switch (nro)
                {
                    case 0:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rr1);
                        play();
                        portada.setImageResource(R.drawable.rr1);
                        titulo.setText("Avenged Sevenfold -  Hail to the king");
                        url = "http://www.avengedsevenfold.com/";
                        descripcion.setText("Avenged Sevenfold (frecuentemente abreviado como A7X) es una banda estadounidense de heavy metal originaria de Huntington Beach, California, fundada en 1999.");
                        break;
                    }
                    case 1:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rr2);
                        play();
                        portada.setImageResource(R.drawable.rr2);
                        titulo.setText("AC/DC - Back in Black");
                        url = "http://www.acdc.com/";
                        descripcion.setText("AC/DC es una banda de hard rock británica-australiana, formada en 1973 en Australia por los hermanos escoceses Malcolm y Angus Young.");
                        break;
                    }
                    case 2:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rr3);
                        play();
                        portada.setImageResource(R.drawable.rr3);
                        titulo.setText("Radiohead -  Creep");
                        url = "https://www.radiohead.com/";
                        descripcion.setText("Radiohead es una banda británica de rock alternativo y art rock originaria de Abingdon-on-Thames, Inglaterra, formada en 1985. Está integrada por Thom Yorke");
                        break;
                    }
                    case 3:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rr4);
                        play();
                        portada.setImageResource(R.drawable.rr4);
                        titulo.setText("The White Stripes - Seven nation army");
                        url = "http://www.whitestripes.com/";
                        descripcion.setText("The White Stripes fue una banda estadounidense de rock alternativo, también algunas veces considerada dentro del movimiento del garage rock. Fue formada en 1997 en Detroit");
                        break;
                    }
                    case 4:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rr5);
                        play();
                        portada.setImageResource(R.drawable.rr5);
                        titulo.setText("Jimmy Eat World -  The Middle");
                        url = "http://www.jimmyeatworld.com/";
                        descripcion.setText("Jimmy Eat World es una banda estadounidense de rock alternativo formada en febrero de 1994 en Mesa, Arizona. Ha estado compuesta, prácticamente desde sus inicios, por Jim Adkins como cantante y primer guitarrista");
                        break;
                    }
                    default:{}
                }
                break;
            }/*===================================================================================*/
            case "Rock-Pop":
            {
                switch (nro)
                {
                    case 0:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rp1);
                        play();
                        portada.setImageResource(R.drawable.rp1);
                        titulo.setText("Maroon 5 - She Will Be Loved");
                        url = "http://www.maroon5.com/";
                        descripcion.setText("Maroon 5 es una banda musical de pop rock estadounidense. El grupo se formó originalmente entre 1994 y 1995 como Kara's Flowers mientras sus integrantes cursaban la secundaria.");
                        break;
                    }
                    case 1:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rp2);
                        play();
                        portada.setImageResource(R.drawable.rp2);
                        titulo.setText("Black Eyed Peas - Where Is The Love");
                        url = "http://www.blackeyedpeas.com/";
                        descripcion.setText("Black Eyed Peas (también conocidos como The Black Eyed Peas) es una banda estadounidense de hip hop/pop formada en 1995 en Los Ángeles, California.");
                        break;
                    }
                    case 2:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rp3);
                        play();
                        portada.setImageResource(R.drawable.rp3);
                        titulo.setText("The Cranberries -  Zombie");
                        url = "http://www.cranberries.com/";
                        descripcion.setText("The Cranberries fue una banda irlandesa de rock alternativo que estuvo en activo desde 1989 hasta 2019. Fue fundada en Limerick, Irlanda en 1989 bajo el nombre The Cranberry Saw Us");
                        break;
                    }
                    case 3:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rp4);
                        play();
                        portada.setImageResource(R.drawable.rp4);
                        titulo.setText("Soda Stereo - Persiana americana");
                        url = "http://www.sodastereo.com/";
                        descripcion.setText("Soda Stereo fue una banda argentina de rock formada originalmente en Buenos Aires en 1982 por Gustavo Cerati (voz, guitarra), Héctor «Zeta» Bosio (bajo) y Carlos Alberto Ficicchia (batería).");
                        break;
                    }
                    case 4:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.rp5);
                        play();
                        portada.setImageResource(R.drawable.rp5);
                        titulo.setText("U2 -  Beatiful Day");
                        url = "http://www.u2.com/";
                        descripcion.setText("U2 es una banda de rock alternativo originaria de Dublín (Irlanda), formada en 1976 por Bono (voz), The Edge (guitarra, teclado y voz), Adam Clayton (bajo), y Larry Mullen Jr. (batería).");
                        break;
                    }
                    default:
                }
                break;
            }/*===================================================================================*/
            case "Pop":
            {
                switch (nro)
                {
                    case 0:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.p1);
                        play();
                        portada.setImageResource(R.drawable.p1);
                        titulo.setText("Katy Perry - California Gurls");
                        url = "https://www.katyperry.com/";
                        descripcion.setText("Katheryn Elizabeth Hudson (Santa Bárbara, California; 25 de octubre de 1984), más conocida por su nombre artístico Katy Perry, es una cantante, compositora, actriz, empresaria y modelo estadounidense");
                        break;
                    }
                    case 1:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.p2);
                        play();
                        portada.setImageResource(R.drawable.p2);
                        titulo.setText("Britney Spears - Toxic");
                        url = "http://www.britneyspears.com/";
                        descripcion.setText("Britney Jean Spears (McComb, Misisipi; 2 de diciembre de 1981) es una cantante, bailarina, compositora, modelo, actriz, diseñadora de modas y empresaria estadounidense.");
                        break;
                    }
                    case 2:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.p3);
                        play();
                        portada.setImageResource(R.drawable.p3);
                        titulo.setText("Lady Gaga - Poker Face");
                        url = "http://www.ladygaga.com/";
                        descripcion.setText("Stefani Joanne Angelina Germanotta (Nueva York, 28 de marzo de 1986), más conocida por su nombre artístico Lady Gaga, es una cantante, compositora, productora, bailarina, actriz, activista y diseñadora de moda estadounidense.");
                        break;
                    }
                    case 3:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.p4);
                        play();
                        portada.setImageResource(R.drawable.p4);
                        titulo.setText("Miranda! - Hola!");
                        url = "http://www.mirandaenvivo.com/";
                        descripcion.setText("Miranda! es una banda argentina de pop liderada por los cantantes Alejandro Sergi y Juliana Gattas. Se formó a mediados de 2001 y su nombre es un homenaje al actor argentino Osvaldo Miranda.");
                        break;
                    }
                    case 4:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.p5);
                        play();
                        portada.setImageResource(R.drawable.p5);
                        titulo.setText("Belanova -  Rosa Pastel");
                        url = "http://belanova.com.mx/";
                        descripcion.setText("Belanova es un grupo de electro pop y synth pop mexicano, originario de Guadalajara, Jalisco, El grupo está integrado por Denisse Guerrero (voz), Edgar Huerta (teclado) y Ricardo Arreola (bajo).");
                        break;
                    }
                    default:
                }
                break;
            }/*===================================================================================*/
            case "Indie":
            {
                switch (nro)
                {
                    case 0:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.i1);
                        play();
                        portada.setImageResource(R.drawable.i1);
                        titulo.setText("The Killers - Shadowplay");
                        url = "http://www.thekillersmusic.com/";
                        descripcion.setText("The Killers es una banda de rock estadounidense formada en 2001 en Las Vegas (Nevada) por el vocalista, teclista y bajista Brandon Flowers y el guitarrista Dave Keuning.");
                        break;
                    }
                    case 1:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.i2);
                        play();
                        portada.setImageResource(R.drawable.i2);
                        titulo.setText("The Ting Tings - that's not my name");
                        url = "http://www.thetingtings.com/";
                        descripcion.setText("The Ting Tings es un dúo británico de indie pop, nominado a un premio Grammy e integrado por Jules de Martino (batería, guitarra principal, bajo, voz, piano) y Katie White (voz, guitarra, bajo, bombo, cencerro)");
                        break;
                    }
                    case 2:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.i3);
                        play();
                        portada.setImageResource(R.drawable.i3);
                        titulo.setText("Muse - Time is running out");
                        url = "http://www.muse.mu/";
                        descripcion.setText("Muse es una banda británica de rock alternativo formada en 1994 en Inglaterra. Desde su formación en la década de los 90, sus integrantes son: Matt Bellamy ; Dominic Howard ; y Christopher Wolstenholme .");
                        break;
                    }
                    case 3:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.i4);
                        play();
                        portada.setImageResource(R.drawable.i4);
                        titulo.setText("The Strokes - Adults are talking");
                        url = "http://thestrokes.com/";
                        descripcion.setText("The Strokes es una banda de rock estadounidense de la ciudad de Nueva York. Formada en 1998, la banda está compuesta por el cantante Julian Casablancas");
                        break;
                    }
                    case 4:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.i5);
                        play();
                        portada.setImageResource(R.drawable.i5);
                        titulo.setText("Interpol -  Obstacle 1");
                        url = "http://www.interpolnyc.com/";
                        descripcion.setText("Interpol es una banda de post punk estadounidense de Manhattan, Nueva York, formada en 1997. Su formación original consistía en Paul Banks (voz, guitarra rítmica), Daniel Kessler (guitarra principal, voz)");
                        break;
                    }
                    default:
                }
                break;
            }/*===================================================================================*/
            case "Alternativo":
            {
                switch (nro)
                {
                    case 0:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.pe1);
                        play();
                        portada.setImageResource(R.drawable.pe1);
                        titulo.setText("Mar de Copas - Suna ");
                        url = "http://mardecopas.pe/";
                        descripcion.setText("La banda fue creada en 1991 en Lima. Estuvo compuesta por Luis Wicho García , Manolo Barrios , Eduardo Toto Leverone , Félix Torrealva (bajo), Phoebe Condos  y Claudia Salem .");
                        break;
                    }
                    case 1:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.pe2);
                        play();
                        portada.setImageResource(R.drawable.pe2);
                        titulo.setText("Yeah Yeah Yeahs - Heads Will Roll");
                        url = "http://www.yeahyeahyeahs.com/";
                        descripcion.setText("Yeah Yeah Yeahs es una banda de indie rock formada en Nueva York. Desde sus inicios, la banda ha estado compuesta por la vocalista y pianista Karen O, el guitarrista Nick Zinner y el baterista Brian Chase");
                        break;
                    }
                    case 2:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.pe3);
                        play();
                        portada.setImageResource(R.drawable.pe3);
                        titulo.setText("Brie Larson - Black Sheep");
                        url = "http://brie-larson.org/";
                        descripcion.setText("Brianne Sidonie Desaulniers (Sacramento, California; 1 de octubre de 1989), más conocida como Brie Larson, es una actriz, cantante, directora, productora, guionista, modelo y Youtuber estadounidense.");
                        break;
                    }
                    case 3:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.pe4);
                        play();
                        portada.setImageResource(R.drawable.pe4);
                        titulo.setText("Talking Heads - Psycho Killer");
                        url = "http://www.talkingheadsbestof.com/";
                        descripcion.setText("Talking Heads fue una banda de rock formada por David Byrne en el año 1974 junto a Chris Frantz y Tina Weymouth.");
                        break;
                    }
                    case 4:
                    {
                        mp.stop();
                        mp = MediaPlayer.create(getActivity(),R.raw.pe5);
                        play();
                        portada.setImageResource(R.drawable.pe5);
                        titulo.setText("Queens Of The Stone Age - No One Knows");
                        url = "http://www.qotsa.com/";
                        descripcion.setText("Queens of the Stone Age (también conocidos como QOTSA o simplemente Queens) es una banda estadounidense de stoner rock. y rock alternativo Fue formada en 1997 por Josh Homme");
                        break;
                    }
                    default:
                }
                break;
            }/*===================================================================================*/
        }
        progreso.setMax((int)(mp.getDuration()/1000));
        startTimer();
    }
}