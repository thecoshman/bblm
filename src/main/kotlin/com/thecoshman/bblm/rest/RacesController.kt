package com.thecoshman.bblm.rest

import com.thecoshman.bblm.data.StaticConfigData
import com.thecoshman.bblm.data.TeamOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1.0/races")
class RacesController internal constructor() {
    @Autowired
    private lateinit var config: StaticConfigData

    @RequestMapping("", method = arrayOf(GET))
    fun getRaces() = config.races

    @RequestMapping("/{raceName}", method = arrayOf(GET))
    fun getRace(@PathVariable(value="raceName") raceName: String): TeamOptions {
        if(config.teamOptions.containsKey(raceName)) return config.teamOptions[raceName]!!
        throw ItemNotFoundException()
    }
}