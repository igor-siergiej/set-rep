package com.example.setrep.model

class Profile {
    private var name = ""
    private var workouts = ArrayList<Workout>()

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return this.name
    }

    fun addWorkout(workout: Workout) {
        this.workouts.add(workout)
    }

    fun getAllWorkouts(): ArrayList<Workout> {
        return this.workouts
    }
}