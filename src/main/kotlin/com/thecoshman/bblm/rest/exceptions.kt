package com.thecoshman.bblm.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate item.")
class DuplicateItemException : RuntimeException()

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find the item.")
class ItemNotFoundException : RuntimeException()