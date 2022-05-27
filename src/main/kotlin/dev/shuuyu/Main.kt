package dev.shuuyu

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import dev.shuuyu.commands.*

object Main {
    @OptIn(PrivilegedIntent::class)
    suspend fun main() {
        val bot = ExtensibleBot(env("TOKEN")) {

            applicationCommands {
                enabled = true
            }

            chatCommands {
                defaultPrefix = System.getenv("CHAT_COMMAND_PREFIX")
                enabled = true
            }

            extensions {
                add(::InfoCommand)
                add(::ShutdownCommand)
            }

            presence {

            }

            intents {
                +Intent.GuildMembers
            }
        }
        bot.start()
    }
}