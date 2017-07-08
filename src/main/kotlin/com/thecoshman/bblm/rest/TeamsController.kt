package com.thecoshman.bblm.rest

import com.thecoshman.bblm.data.Team
import com.thecoshman.bblm.data.TeamData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET

@RestController
@RequestMapping("/rest/v1.0/teams")
class TeamsController internal constructor() {
    @Autowired
    private lateinit var data: TeamData

    @RequestMapping("", method = arrayOf(GET))
    fun getTeamNames() = data.teams.map(Team::name)

    @RequestMapping("/{teamName}", method = arrayOf(GET))
    fun getTeam(@PathVariable(value = "teamName") teamName: String): Team {
        if (getTeamNames().contains(teamName)) return data.teams.first { it.name == teamName }
        throw ItemNotFoundException()
    }

    @RequestMapping("", method = arrayOf(RequestMethod.POST))
    private fun registerTeam(@RequestBody newTeam: Team) =
            if (data.addTeam(newTeam)) getTeam(newTeam.name)
            else throw DuplicateItemException()
}