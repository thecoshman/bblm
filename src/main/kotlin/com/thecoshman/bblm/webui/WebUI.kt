package com.thecoshman.bblm.webui

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class WebUI :  ApplicationContextAware {

    private var appContext: ApplicationContext? = null

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appContext = applicationContext
    }
}
