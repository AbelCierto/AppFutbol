package com.example.appweek7_s1.controller.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.appweek7_s1.R
import com.example.appweek7_s1.database.TeamDB
import com.example.appweek7_s1.models.Team
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class TeamDetail : AppCompatActivity() {

    lateinit var ivLogoDetail: ImageView
    lateinit var tvNameDetail: TextView
    lateinit var tvVenueName: TextView
    lateinit var fabSave: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        ivLogoDetail = findViewById(R.id.ivLogoDetail)
        tvNameDetail = findViewById(R.id.tvNameDetail)
        tvVenueName = findViewById(R.id.tvVenueName)
        fabSave = findViewById(R.id.fabSave)

        initFields(this)
    }

    private fun initFields(context: Context) {
        val teamObject: Team? = intent.getSerializableExtra("team") as Team?

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(teamObject?.logo)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(ivLogoDetail)

        tvNameDetail.text = teamObject?.name
        tvVenueName.text = teamObject?.venue_name

        fabSave.setOnClickListener{
            saveTeam(teamObject)
            finish()
        }

    }

    private fun saveTeam(teamObject: Team?) {
        if (teamObject != null) {
            TeamDB.getInstance(this).getTeamDao().insertTeam(teamObject)
        }

    }
}