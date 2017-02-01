package com.thecoshman.bblm.rest

import com.thecoshman.bblm.data.DivisionsData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/divisions")
class DivisionsController internal constructor() {
    @Autowired
    private lateinit var data: DivisionsData

    @RequestMapping("", method = arrayOf(GET))
    fun getDivisions() = data.divisions

    @RequestMapping("", method = arrayOf(POST))
    private fun addDivision(@RequestBody newDivision: String) =
            if (data.addDivision(newDivision)) data.divisions
            else throw DuplicateItemException()

    @RequestMapping("", method = arrayOf(DELETE))
    private fun removeDivision(@RequestBody divisionToRemove: String): Set<String> {
        data.removeDivision(divisionToRemove)
        return data.divisions
    }
}