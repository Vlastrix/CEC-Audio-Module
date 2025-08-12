package com.example.cecaudiomodule.repositories

import com.example.cecaudiomodule.R
import com.example.cecaudiomodule.models.SoundItem

object SoundRepository {
    fun presets() = listOf(
        SoundItem("DS obj added", R.raw.dead_space_obj_added),
        SoundItem("DS obj complete", R.raw.dead_space_obj_complete),
        SoundItem("DS zero gravity", R.raw.ds_zero_gravity),
        SoundItem("DS vacuum", R.raw.ds_entering_vacuum),
        SoundItem("Among Us", R.raw.among_us_role_reveal),
        SoundItem("Evento Canon", R.raw.canon_event_sm),
        SoundItem("Uwu", R.raw.uwu),
        SoundItem("Gemi2", R.raw.gemi2),
        SoundItem("Conchetumareee", R.raw.conchetumare),
        SoundItem("Yametekudasai", R.raw.yametekudasai),
        SoundItem("OMG", R.raw.omg),
        SoundItem("Ey ey ey pequeña", R.raw.ey_ey_ey_peque),
        SoundItem("Aaaaaoaaaa", R.raw.aaaaoaaa),
        SoundItem("Despierta", R.raw.despierta),
        SoundItem("Fornite", R.raw.fornite),
        SoundItem("Papu papu", R.raw.papu_papu),
        SoundItem("Que pro", R.raw.que_pro),
        SoundItem("Me hace frio", R.raw.me_hace_frio),
        SoundItem("El pepe", R.raw.el_pepe),
        SoundItem("Diamantes pal free", R.raw.diamantes_pal_free),
        SoundItem("Unitel", R.raw.unitel),
        SoundItem("Vieja de m*****", R.raw.vieja_de_m),
        SoundItem("No tienes enemigos", R.raw.no_tienes_enemigos),
        SoundItem("Old hardware", R.raw.old_hardware),
    )
}
