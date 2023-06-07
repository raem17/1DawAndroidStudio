package com.example.petdaycarekotandfire

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class PetArrayAdapter (context: Context, viewToPaint: Int, private val petList: ArrayList<Pet>)
    : ArrayAdapter<Pet> (context, viewToPaint, petList) {
    //override
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //Log.i("Demostración", "entrando en el getView")

        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.list_of_pets, null)

        val imageViewSex = currentListItem.findViewById<ImageView>(R.id.imageViewSex)
        val textViewNombre = currentListItem.findViewById<TextView>(R.id.textViewNombre)
        val textViewRaza = currentListItem.findViewById<TextView>(R.id.textViewRaza)

        textViewNombre.text = petList[position].nombre
        textViewRaza.text = petList[position].raza
        imageViewSex.setImageResource(petList[position].imageSex)

        return currentListItem
    }
}