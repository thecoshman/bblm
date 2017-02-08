package com.thecoshman.bblm.data

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class TeamData {
    lateinit var teams: Set<Team>
        private set

    @PostConstruct
    private fun init() {
        // dummy data
        teams = setOf(Team("Human", "Nuln Purple Eagles", 0, setOf(
                Player(1, "Jimmy two Feet", "Lineman"),
                Player(2, "Double Dodge Drake", "Catcher")
        )))
    }

    fun addTeam(newTeam: Team): Boolean {
        if (teams.map(Team::name).contains(newTeam.name))
            return false
        teams += newTeam
        return true
    }
}