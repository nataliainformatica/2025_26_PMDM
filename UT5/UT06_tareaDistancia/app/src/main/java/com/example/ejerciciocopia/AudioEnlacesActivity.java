package com.example.ejerciciocopia;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ejerciciocopia.model.CardItem;
import com.example.ejerciciocopia.model.DAOcardItem;

import java.util.Arrays;
import java.util.List;

public class AudioEnlacesActivity extends AppCompatActivity {
    private Context context;
    private ListView listView;

    private MediaPlayer mediaPlayer;
    private CardItem itemSeleccionado;
    private List<CardItem> items;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_audio_enlaces);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        context= this;

        listView = findViewById(R.id.listView);
        // URL del audio
        items = DAOcardItem.getCards();
        CardAdapter adapter = new CardAdapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el elemento seleccionado
                itemSeleccionado  =  items.get(position);

                // Mostrar un mensaje (puedes realizar otra acción aquí)
                Toast.makeText(context, "Seleccionaste: " + itemSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });

        Button playButton = findViewById(R.id.playButton);
        Button stopButton = findViewById(R.id.stopButton);



        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(itemSeleccionado.getDescription());
                        mediaPlayer.setOnPreparedListener(mp -> {
                            mediaPlayer.start();
                            Toast.makeText(context, "Reproduciendo audio", Toast.LENGTH_SHORT).show();
                        });
                        mediaPlayer.setOnCompletionListener(mp -> {
                            mediaPlayer.release();
                            mediaPlayer = null;
                            Toast.makeText(context, "Audio finalizado", Toast.LENGTH_SHORT).show();
                        });
                        mediaPlayer.prepareAsync(); // Carga el archivo de audio de forma asincrónicaitemSeleccionado
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error al reproducir audio", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Ya se está reproduciendo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    Toast.makeText(context, "Audio detenido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Libera los recursos
            mediaPlayer = null;
        }
        super.onDestroy();
    }
    class CardAdapter extends BaseAdapter {
        private final Context context;
        private final List<CardItem> items;

        public CardAdapter(Context context, List<CardItem> items) {

            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
            }
            ImageView imageView = convertView.findViewById(R.id.itemImage);
            TextView titleText = convertView.findViewById(R.id.itemTitle);
            CardItem item = items.get(position);
            titleText.setText(item.getTitle());
            imageView.setImageResource(item.getImageRes());



            return convertView;
        }
    }
}