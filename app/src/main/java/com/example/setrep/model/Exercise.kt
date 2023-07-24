package com.example.setrep.model

class Exercise {
    var movement = Movement()
    var sets = ArrayList<Int>()
    var weights = ArrayList<Int>()

    constructor(movement: Movement, sets: ArrayList<Int>, weights: ArrayList<Int>) {
        this.movement = movement
        this.sets = sets
        this.weights = weights
    }
}