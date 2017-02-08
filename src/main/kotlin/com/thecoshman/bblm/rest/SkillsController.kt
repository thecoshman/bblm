package com.thecoshman.bblm.rest

import com.thecoshman.bblm.data.StaticConfigData
import com.thecoshman.bblm.data.TeamOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/skills")
class SkillsController internal constructor() {
    @Autowired
    private lateinit var config: StaticConfigData

    @RequestMapping("", method = arrayOf(GET))
    fun getSkills() = config.skills
}