package com.thecoshman.bblm.rest

import com.thecoshman.bblm.data.Coach
import com.thecoshman.bblm.data.CoachesData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coaches")
class CoachesController internal constructor() {
    @Autowired
    private lateinit var data: CoachesData

    @RequestMapping("", method = arrayOf(GET))
    fun getCoaches() = data.coaches

    @RequestMapping("", method = arrayOf(POST))
    private fun addCoach(@RequestBody newCoach: Coach) =
            if (data.addCoach(newCoach)) data.coaches
            else throw DuplicateItemException()

    @RequestMapping("", method = arrayOf(DELETE))
    private fun removeCoach(@RequestBody nameOfCoachToRemove: String): Set<Coach> {
        data.removeCoach(nameOfCoachToRemove)
        return data.coaches
    }
}