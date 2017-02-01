package com.thecoshman.bblm.data

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DivisionsData {
    lateinit var divisions: Set<String>
        private set

    @PostConstruct
    private fun init() {
        // dummy data
        divisions = setOf("Warp-Stone", "Black Fang")
    }

    fun addDivision(newDivision: String): Boolean {
        if (divisions.contains(newDivision))
            return false
        divisions += newDivision
        return true
    }

    fun removeDivision(divisionToRemove: String) {
        divisions -= divisionToRemove
    }
}

