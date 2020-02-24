package pe.edu.continental.adaug.Clases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.continental.adaug.R

class IntroSliderAdapter(private val introSlide: List<IntroSlide>):
    RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return introSlide.size
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {

        holder.bind(introSlide[position])
    }

    inner class  IntroSlideViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val txtTitle = view.findViewById<TextView>(R.id.txtTitulo)
        private val txtDescrip = view.findViewById<TextView>(R.id.txtDescripcion)
        private val imgIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introSlide: IntroSlide){
            txtTitle.text=introSlide.title
            txtDescrip.text=introSlide.descripton
            imgIcon.setImageResource(introSlide.icon)

        }
    }
}