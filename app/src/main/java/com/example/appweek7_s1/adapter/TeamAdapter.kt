package com.example.appweek7_s1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.appweek7_s1.R
import com.example.appweek7_s1.models.Team
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class TeamAdapter(val teams: List<Team>, val context: Context, val itemClickListener: OnItemClickListener)
    : Adapter<TeamAdapter.ViewHolder>(){

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val ivLogo = view.findViewById<ImageView>(R.id.ivLogo)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val cvTeam = view.findViewById<CardView>(R.id.cvTeam)
    }

    interface OnItemClickListener {
        fun onItemClicked(team: Team)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder {
        //Todo: Create a view for each item in the list
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_team, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.tvName.text = team.name

        //Upload the image
        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(team.logo)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivLogo)

        holder.cvTeam.setOnClickListener{
            itemClickListener.onItemClicked(team)
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}
