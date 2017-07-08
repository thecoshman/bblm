package com.thecoshman.bblm.webui

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.web.servlet.ViewResolver
import org.thymeleaf.TemplateEngine
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import org.thymeleaf.templateresolver.ITemplateResolver

open class WebUI :  ApplicationContextAware {

    private var appContext: ApplicationContext? = null

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appContext = applicationContext
    }

    fun viewResolver(): ViewResolver {
        with(ThymeleafViewResolver()) {
            templateEngine = templateEngine() as SpringTemplateEngine?
            characterEncoding = "UTF-8"
            contentType = "text/html; charset=UTF-8"
            return this
        }
    }

    fun templateEngine(): TemplateEngine {
        with (SpringTemplateEngine()) {
            setTemplateResolver(templateResolver())
            return this
        }
    }

    fun templateResolver(): ITemplateResolver {
        with(SpringResourceTemplateResolver()) {
            setApplicationContext(appContext)
            prefix = "/main/WEB-INF/templates/"
            suffix = ".html"
            // templateMode = TemplateMode.HTML // This is default vOv
            characterEncoding = "UTF-8"
            return this
        }
    }
}
