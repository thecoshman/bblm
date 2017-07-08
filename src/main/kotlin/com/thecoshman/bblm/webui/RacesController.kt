package com.thecoshman.bblm.webui

import com.thecoshman.bblm.data.StaticConfigData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import org.springframework.web.servlet.ViewResolver
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.TemplateEngine
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.templateresolver.ITemplateResolver
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class RacesWebController : ApplicationContextAware {
    private var appContext: ApplicationContext? = null

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        appContext = applicationContext
    }

    @Autowired
    private lateinit var config: StaticConfigData

    @ModelAttribute("allRaces")
    fun getRaces() = config.races

    @RequestMapping("/races")
    fun racesPage() = "races"

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