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

        val GA = setOf("G", "A")
        val SP = setOf("S", "P")

        createTeam("Dark Elf", TeamReRolls(8, 70000), setOf(
                RookiePlayer(16, "Linemen", 70000, 6, 3, 4, 8, emptySet(), GA, SP)
        ))

        createTeam("Human", TeamReRolls(8, 50000), setOf(
                RookiePlayer(16, "Linemen", 70000, 6, 3, 4, 8, emptySet(), GA, SP)
        ))
    }

    private fun initSkills() {
        skills += Pair("G", setOf(
                "Block", "Run"
        ))
        skills += Pair("A", setOf(
                "Dodge"
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
        val validRookiePlayers = mutableSetOf<RookiePlayer>()
        rookiePlayers.forEach {
            val raceAndPos = "$race ${it.position}"
            val unrecognisedSkills = it.skills.filter { allSkills.contains(it.name) }
            unrecognisedSkills.forEach { configIssues_ += "'${it.name}' is not a recognised skill $raceAndPos" }
            val unrecognisedNormalGroups = it.normalSkillGroups.filter { skills.keys.contains(it) }
            unrecognisedNormalGroups.forEach { configIssues_ += "'$it'  is not a recognised 'normal' skill group $raceAndPos" }
            val unrecognisedDoubleGroups = it.doubleSkillGroups.filter { skills.keys.contains(it) }
            unrecognisedDoubleGroups.forEach { configIssues_ += "'$it'  is not a recognised 'double' skill group $raceAndPos" }

            if (unrecognisedSkills.isEmpty() && unrecognisedNormalGroups.isEmpty() && unrecognisedDoubleGroups.isEmpty())
                validRookiePlayers + it
        }
        teamOptions += Pair(race, TeamOptions(race, reRolls, validRookiePlayers))
    }
}

