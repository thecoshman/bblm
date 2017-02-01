package com.thecoshman.bblm.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Skill(
        val name: String,
        val skillGroup: String
)

data class TeamReRolls(
        val max: Int,
        val cost: Int
)

data class RookiePlayer(
        val maxQty: Int,
        val position: String,
        val cost: Int,
        val ma: Int,
        val st: Int,
        val ag: Int,
        val av: Int,
        val skills: Set<Skill>,
        val normalSkillGroups: Set<String>,
        val doubleSkillGroups: Set<String>
)

data class TeamOptions(
        val race: String,
        val reRolls: TeamReRolls,
        val rookiePlayers: Set<RookiePlayer>
)

enum class PermissionLevel {
    player, reporter, admin
}

data class Coach(
        @JsonProperty("name") val name: String,
        @JsonProperty("permission") val permission: PermissionLevel
)