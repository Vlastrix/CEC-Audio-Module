package com.example.cecaudiomodule.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cecaudiomodule.R
import com.example.cecaudiomodule.databinding.ActivityMainBinding
import com.example.cecaudiomodule.repositories.SoundRepository
import com.example.cecaudiomodule.ui.adapters.GridSpacingItemDecoration
import com.example.cecaudiomodule.ui.adapters.SoundAdapter
import com.example.cecaudiomodule.utils.MediaPlayerEngine
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    private var keepSplash = true
    private lateinit var b: ActivityMainBinding
    private lateinit var engine: MediaPlayerEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash: SplashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splash.setKeepOnScreenCondition { keepSplash }

        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        engine = MediaPlayerEngine(this, allowOverlap = true)

        val data = SoundRepository.presets()
        engine.prewarm(data.map { it.resId })

        val span = 2
        b.grid.layoutManager = GridLayoutManager(this, span)
        if (b.grid.itemDecorationCount == 0) {
            val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
            b.grid.addItemDecoration(GridSpacingItemDecoration(span, spacing, includeEdge = true))
        }
        b.grid.adapter = SoundAdapter(data) { resId -> engine.play(resId) }

        keepSplash = false
    }

    override fun onDestroy() {
        super.onDestroy()
        engine.release()
    }
}
