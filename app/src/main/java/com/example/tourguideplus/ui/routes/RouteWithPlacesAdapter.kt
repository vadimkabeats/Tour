package com.example.tourguideplus.ui.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tourguideplus.R
import com.example.tourguideplus.data.model.RouteWithPlaces

class RouteWithPlacesAdapter(
    private val onClick: (RouteWithPlaces) -> Unit,
    private val onDelete: (RouteWithPlaces) -> Unit
) : ListAdapter<RouteWithPlaces, RouteWithPlacesAdapter.VH>(Diff()) {


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(itemView: View,
             val onClick: (RouteWithPlaces) -> Unit,
             val onDelete: (RouteWithPlaces) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameTv: TextView = itemView.findViewById(R.id.tv_route_name)
        private val countTv: TextView = itemView.findViewById(R.id.tv_route_count)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btn_delete_route)

        fun bind(rwp: RouteWithPlaces) {
            nameTv.text = rwp.route.name
            countTv.text = "${rwp.places.size} мест"
            itemView.setOnClickListener { onClick(rwp) }
            btnDelete.setOnClickListener { onDelete(rwp) }    // ← сюда
        }
    }

    class Diff : DiffUtil.ItemCallback<RouteWithPlaces>() {
        override fun areItemsTheSame(old: RouteWithPlaces, new: RouteWithPlaces) =
            old.route.id == new.route.id
        override fun areContentsTheSame(old: RouteWithPlaces, new: RouteWithPlaces) =
            old == new
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_route_with_places, parent, false)
        return VH(v, onClick, onDelete)
    }
}
