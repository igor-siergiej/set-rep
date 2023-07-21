package com.example.setrep.datasource

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.setrep.model.Exercise
import com.example.setrep.model.Workout

class WorkoutViewModel : ViewModel() {
    var workout = mutableStateOf(Workout())

    fun addExercise(exercise: Exercise) {
        workout.value.exercises.add(exercise)
    }

    fun getExercises(): ArrayList<Exercise> {
        return workout.value.exercises
    }

    fun clear() {
        workout.value.exercises.clear()
    }
}