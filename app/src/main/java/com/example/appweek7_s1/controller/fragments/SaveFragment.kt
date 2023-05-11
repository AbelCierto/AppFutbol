package com.example.appweek7_s1.controller.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appweek7_s1.R
import com.example.appweek7_s1.adapter.TeamAdapter
import com.example.appweek7_s1.controller.activities.TeamDetail
import com.example.appweek7_s1.database.TeamDB
import com.example.appweek7_s1.models.Team

class SaveFragment : Fragment(), TeamAdapter.OnItemClickListener {
    var team: List<Team> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false)
    }
    //Ctrl O
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        team = TeamDB.getInstance(view.context).getTeamDao().getAllTeams()
        recyclerView = view.findViewById(R.id.rvTeamSave)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = TeamAdapter(team, view.context, this)
    }

    override fun onItemClicked(team: Team) {
        val intent = Intent(context, TeamDetail::class.java)
        intent.putExtra("team", team)
        startActivity(intent)
    }


}