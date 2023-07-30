package com.example.setrep.model

import java.time.LocalDateTime

class Workout {
    var timeTaken = 0
    var date = LocalDateTime.now()
    var exercises = ArrayList<Exercise>()
}