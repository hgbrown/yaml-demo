package dev.hbrown.yamldemo

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Value("\${info.app.version}")
    lateinit var appVersion: String

    @Value("\${app.users.path}")
    lateinit var userPath: String

    @GetMapping(
        path = ["/"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun home(): String = """
        {
            "version": "$appVersion"
            "path": "$userPath"
        }
    """.trimIndent()
}
