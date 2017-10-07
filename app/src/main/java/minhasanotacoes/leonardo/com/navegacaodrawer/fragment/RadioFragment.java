package minhasanotacoes.leonardo.com.navegacaodrawer.fragment;


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import minhasanotacoes.leonardo.com.navegacaodrawer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RadioFragment extends Fragment {
    private String url_radio = "http://media-ice.musicradio.com/ClassicFMMP3";
    private Button buttonPlay;
    private MediaPlayer player;


    public RadioFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tootView =  inflater.inflate(R.layout.fragment_radio, container, false);

        initializeMediaPlayer();


        buttonPlay =  tootView.findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (player.isPlaying()) {
                    try {
                        stopPlaying();
                        buttonPlay.setText("Tocar");
                        buttonPlay.setTextColor(Color.parseColor("white"));
                        buttonPlay.setBackgroundColor(Color.parseColor("#b71c1c"));
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Toast.makeText(getContext(), "Parando rádio online", Toast.LENGTH_SHORT).show();
                }else {
                    startPlaying();
                    buttonPlay.setTextColor(Color.parseColor("#f05545"));
                    buttonPlay.setBackgroundColor(Color.parseColor("white"));
                    buttonPlay.setText("Parar");
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                            .setSmallIcon( R.drawable.logo )
                            .setTicker( "Título  de teste" )
                            .setContentTitle( "Rádio IPAD Ministério Restauração" )
                            .setContentText( "Ao vivo!" )
                            .setAutoCancel( true );

                    int id = 1;

                    NotificationManager notifyManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    assert notifyManager != null;
                    notifyManager.notify( id, builder.build() );
                    Toast.makeText(getContext(), "Tocando rádio online", Toast.LENGTH_SHORT).show();
                }

            }
        });



    return tootView;
    }


    @SuppressLint("ResourceAsColor")
    private void startPlaying() {

        player.prepareAsync();

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {

                player.start();

            }
        });

    }

    @SuppressLint("ResourceAsColor")
    private void stopPlaying() {
        if (player.isPlaying()) {
            player.stop();
            player.release();
            initializeMediaPlayer();
        }
    }

    private void initializeMediaPlayer() {
        player = new MediaPlayer();
        try {
            player.setDataSource(url_radio);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
