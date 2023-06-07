package com.example.elvishtranslator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class WordArrayAdapter (context : Context, viewToPaint : Int, private val wordList : ArrayList<Word>)
    : ArrayAdapter<Word> (context, viewToPaint, wordList) {
    // override
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //Log.i("Demostración", "entrando en el getView")

        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.item_list_words, null)

        val textViewSpanishWord = currentListItem.findViewById<TextView>(R.id.textViewSpanishWord)
        val textViewElfishWord = currentListItem.findViewById<TextView>(R.id.textViewElfishWord)
        val imageViewVisualExample = currentListItem.findViewById<ImageView>(R.id.imageViewVisualExample)
        val imageViewPlay = currentListItem.findViewById<ImageView>(R.id.imageViewPlay)

        textViewSpanishWord.text = wordList[position].spanishWord
        textViewElfishWord.text = wordList[position].elfishWord
        imageViewVisualExample.setImageResource(wordList[position].imageID)

        imageViewPlay.setImageResource(R.drawable.button_play)

        return currentListItem
    }
}