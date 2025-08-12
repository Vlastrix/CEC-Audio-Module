package com.example.cecaudiomodule.ui.adapters

import android.content.Context
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cecaudiomodule.databinding.ItemSoundBinding
import com.example.cecaudiomodule.models.SoundItem

class SoundAdapter(
    private val items: List<SoundItem>,
    private val onPlay: (Int) -> Unit
) : RecyclerView.Adapter<SoundAdapter.VH>() {

    inner class VH(val b: ItemSoundBinding) : RecyclerView.ViewHolder(b.root) {
        var lastClickTs = 0L
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemSoundBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.b.title.text = item.title

        holder.b.root.setOnClickListener { view ->
            val now = android.os.SystemClock.uptimeMillis()
            if (now - holder.lastClickTs < 120) return@setOnClickListener
            holder.lastClickTs = now

            onPlay(item.resId)

            view.hapticFeedback()

            view.animate()
                .scaleX(0.95f).scaleY(0.95f).setDuration(70)
                .withEndAction {
                    view.animate().scaleX(1f).scaleY(1f).setDuration(70).start()
                }
                .start()
        }
    }

    override fun getItemCount() = items.size
}

private fun View.hapticFeedback() {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (vibrator.hasVibrator()) {
        vibrator.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}
