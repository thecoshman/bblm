package com.thecoshman.bblm.data

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * Class to manage loading the core data that is not going to change
 */
@Component
class StaticConfigData {
    var teamOptions = emptyMap<String, TeamOptions>()
        private set

    val races: Set<String>
        get() = teamOptions.keys

    var skills = emptyMap<String, Set<String>>()
        private set

    private val configIssues_ = mutableSetOf<String>()
    val configIssues: Set<String>
        get() = configIssues_

    @PostConstruct
    private fun init() {
        // This is all dummy data for now
        initSkills()

        createTeam("Dark Elf", TeamReRolls(8, 70000), setOf(
                RookiePlayer(16, "Broken Linemen", 70000, 6, 3, 4, 8, setOf("Fart"), "GAX", "SP"),
                RookiePlayer(16, "Linemen", 70000, 6, 3, 4, 8, setOf("Dodge"), "GA", "SP")
        ))

        createTeam("Human", TeamReRolls(8, 50000), setOf(
                RookiePlayer(16, "Linemen", 70000, 6, 3, 4, 8, emptySet(), "GA", "SP"),
                RookiePlayer(4, "Thrower", 70000, 6, 3, 4, 8, setOf("Throw"), "GA", "SP")
        ))
    }

    private fun initSkills() {
        skills += Pair("G", setOf(
                "Block", "Run"
        ))
        skills += Pair("A", setOf(
                "Dodge", "Throw"
        ))
        skills += Pair("P", setOf())
        skills += Pair("S", setOf())
        skills += Pair("E", setOf())
    }

    /**
     * Adds a use if it passes some basic validation. Any validation failures are logged in 'configIssues_'
     */
    private fun createTeam(race: String, reRolls: TeamReRolls, rookiePlayers: Set<RookiePlayer>) {
        val allSkills = skills.flatMap { it.value }
        var validRookiePlayers = emptySet<RookiePlayer>()
        rookiePlayers.forEach {
            val raceAndPos = "$race ${it.position}"
            val unrecognisedSkills = it.skillNames.filterNot { allSkills.contains(it) }
            unrecognisedSkills.forEach { configIssues_ += "'$it' is not a recognised skill for $raceAndPos" }
            val unrecognisedNormalGroups = it.normalSkillGroups.filterNot { skills.keys.contains(it.toString()) }
            unrecognisedNormalGroups.forEach { configIssues_ += "'$it'  is not a recognised normal skill group for $raceAndPos" }
            val unrecognisedDoubleGroups = it.doubleSkillGroups.filterNot { skills.keys.contains(it.toString()) }
            unrecognisedDoubleGroups.forEach { configIssues_ += "'$it'  is not a recognised double skill group for $raceAndPos" }

            if (unrecognisedSkills.isEmpty() && unrecognisedNormalGroups.isEmpty() && unrecognisedDoubleGroups.isEmpty())
                validRookiePlayers += it
        }
        teamOptions += Pair(race, TeamOptions(race, reRolls, validRookiePlayers))
    }
}

