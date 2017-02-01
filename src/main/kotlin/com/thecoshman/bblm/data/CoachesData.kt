package com.thecoshman.bblm.data

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class CoachesData {
    lateinit var coaches: Set<Coach>
        private set


    @PostConstruct
    private fun init() {
        // dummy data
        coaches = setOf(Coach("Frank", PermissionLevel.player), Coach("Lucy", PermissionLevel.reporter))
    }

    fun addCoach(newCoach: Coach): Boolean {
        if (coaches.any { it.name == newCoach.name })
            return false
        coaches += newCoach
        return true
    }

    fun removeCoach(coachToRemove: String) {
        coaches = coaches.filterNot { it.name == coachToRemove }.toSet()
    }
}

