package com.thecoshman.bblm.data

import com.fasterxml.jackson.annotation.JsonProperty

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
        val skillNames: Set<String>,
        val normalSkillGroups: String,
        val doubleSkillGroups: String
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

data class Team(
        @JsonProperty("race") val race: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("reRolls") val reRolls: Int,
        @JsonProperty("players") val players: Set<Player> = emptySet(),
        @JsonProperty("coachName") val coachName: String? = null
)

data class Player(
        @JsonProperty("number") val number: Int,
        @JsonProperty("name") val name: String,
        @JsonProperty("position") val position: String
)