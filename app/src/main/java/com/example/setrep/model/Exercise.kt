package com.example.setrep.model

class Exercise {
    var movement = Movement()
    var sets = ArrayList<Int>()

    constructor(movement: Movement, sets: ArrayList<Int>) {
        this.movement = movement
        this.sets = sets
    }
}