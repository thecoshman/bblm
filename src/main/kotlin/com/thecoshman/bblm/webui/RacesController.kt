package com.thecoshman.bblm.webui

import com.thecoshman.bblm.data.StaticConfigData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class RacesWebController : WebUI() {
    @Autowired
    private lateinit var config: StaticConfigData

    @ModelAttribute("allRaces")
    fun getRaces() = config.races

    @RequestMapping("/races")
    fun racesPage() = "races"
}