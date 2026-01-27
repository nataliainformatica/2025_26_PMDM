package com.example.ejerciciocopia.model;

import com.example.ejerciciocopia.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAOcardItem {


    public static List<CardItem> getCards() {
        List<String> items;
        List<Integer> imagenes;
        List<CardItem> cards = new ArrayList<>();
        String audioUrl = "https://www.example.com/audiofile.mp3";
        items = Arrays.asList("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-12.mp3",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3",
                "https://www.soundhelix.com/examples/mp3/-Song-4.mp3",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3"
               );
        //  "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3"

        imagenes = Arrays.asList(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6);
        for (int i = 0; i < items.size(); i++) {
            cards.add(new CardItem(imagenes.get(i), ""+ i, items.get(i)));


        }
        return cards;
    }
}

