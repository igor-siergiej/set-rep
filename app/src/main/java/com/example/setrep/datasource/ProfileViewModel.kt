package com.example.setrep.datasource

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.setrep.model.Profile
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File

const val FILENAME = "profile.json"

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private var textFile = File(application.filesDir, FILENAME)
    var profile = Profile()

    fun doesFileExist(): Boolean {
        return textFile.exists()
    }

    fun saveProfile() {
        var gson = Gson()
        var json = gson.toJson(this.profile)
        textFile.writeText(json)
    }

    fun loadProfile() {
        var gson = Gson()
        val bufferedReader: BufferedReader = textFile.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        this.profile = gson.fromJson(inputString, Profile::class.java)
    }

    fun deleteProfile() {
        textFile.delete()
    }


}