package dev.shuuyu.commands

import com.kotlindiscord.kord.extensions.checks.anyGuild
import com.kotlindiscord.kord.extensions.extensions.Extension
import io.github.qbosst.kordex.commands.hybrid.publicHybridCommand

class InfoCommand : Extension() {
    override val name: String = "Info"

    override suspend fun setup() {
        publicHybridCommand {
            name = "info"
            description = "Shows info about the bot."

            check {
                anyGuild()
            }

            action {
                val javaVersion = System.getProperty("java.home")
                val osRevision = System.getProperty("os.version")
                val osName = System.getProperty("os.name")

                respond {
                }
            }
        }
    }
}