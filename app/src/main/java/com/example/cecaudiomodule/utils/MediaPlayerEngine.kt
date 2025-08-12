package com.example.cecaudiomodule.utils

import android.content.Context
import android.media.*

class MediaPlayerEngine(
    private val context: Context,
    private val allowOverlap: Boolean = true
) {
    private data class Entry(val mp: MediaPlayer, var ready: Boolean)

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private val entries = mutableMapOf<Int, Entry>()
    private val temps = mutableListOf<MediaPlayer>()

    private val attrs = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build()

    private val focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
        .setAudioAttributes(attrs)
        .setOnAudioFocusChangeListener { change ->
            when (change) {
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> setVolumeAll(0.2f)
                AudioManager.AUDIOFOCUS_GAIN -> setVolumeAll(1f)
                AudioManager.AUDIOFOCUS_LOSS -> stopAll()
            }
        }
        .build()

    fun prewarm(resIds: List<Int>) {
        resIds.forEach { resId ->
            if (entries.containsKey(resId)) return@forEach
            val afd = context.resources.openRawResourceFd(resId) ?: return@forEach
            val mp = MediaPlayer().apply {
                setAudioAttributes(attrs)
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                setOnPreparedListener { entries[resId]?.ready = true }
                setOnCompletionListener { /* la instancia precargada no se libera */ }
                prepareAsync()
            }
            afd.close()
            entries[resId] = Entry(mp, ready = false)
        }
    }

    fun play(resId: Int) {
        if (audioManager.requestAudioFocus(focusRequest)
            != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) return

        val e = entries[resId]
        if (e != null && e.ready) {
            if (allowOverlap && e.mp.isPlaying) {
                playTemp(resId)
            } else {
                e.mp.seekTo(0)
                e.mp.start()
            }
            return
        }
        playTemp(resId)
    }

    private fun playTemp(resId: Int) {
        val mp = MediaPlayer.create(context, resId) ?: run {
            abandonFocusIfIdle()
            return
        }
        mp.setOnCompletionListener { p ->
            try {
                if (p.isPlaying) p.stop()
            } catch (_: Throwable) {}
            p.release()
            temps.remove(p)
            abandonFocusIfIdle()
        }
        temps.add(mp)
        mp.start()
    }

    fun stopAll() {
        entries.values.forEach { e ->
            runCatching {
                if (e.mp.isPlaying) e.mp.pause()
                e.mp.seekTo(0)
            }
        }
        temps.toList().forEach { p ->
            runCatching { if (p.isPlaying) p.stop() }
            p.release()
            temps.remove(p)
        }
        abandonFocusIfIdle()
    }

    fun release() {
        stopAll()
        entries.values.forEach { e -> e.mp.release() }
        entries.clear()
    }

    private fun setVolumeAll(v: Float) {
        entries.values.forEach { it.mp.setVolume(v, v) }
        temps.forEach { it.setVolume(v, v) }
    }

    private fun abandonFocusIfIdle() {
        val anyEntryPlaying = entries.values.any { it.mp.isPlaying }
        val anyTempPlaying = temps.any { it.isPlaying }
        if (!anyEntryPlaying && !anyTempPlaying) {
            audioManager.abandonAudioFocusRequest(focusRequest)
        }
    }
}
