package com.store.shopp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

internal class MoviesAdapter(var moviesList: List<MovieModel>, var listener: OnItemClickListener) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        var title: TextView = view.findViewById(R.id.title1)
        var price: TextView = view.findViewById(R.id.price)
        var detail: TextView = view.findViewById(R.id.detail)
        var color: TextView = view.findViewById(R.id.color)
        var image: ImageView = view.findViewById(R.id.image1)
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            var position=adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onclick(position)
            }

        }
    }
    interface OnItemClickListener{
        fun onclick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.title.text = movie.title
        holder.price.text = movie.price
        holder.detail.text = movie.detail
        holder.color.text = movie.color
        Picasso.get().load(movie.image).into(holder.image)
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
}

