package com.thecoshman.bblm.webui

import com.thecoshman.bblm.data.TeamData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class TeamsWebController : WebUI() {
    @Autowired
    private lateinit var data: TeamData

    @ModelAttribute("allTeams")
    fun getRaces() = data.teams

    @RequestMapping("/teams")
    fun racesPage() = "teams"
}