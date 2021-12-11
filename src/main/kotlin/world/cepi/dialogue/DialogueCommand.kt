package world.cepi.dialogue

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kstom.Manager
import world.cepi.kstom.adventure.asMini
import world.cepi.kstom.command.kommand.Kommand
import java.time.Duration

object DialogueCommand : Kommand({

    val senderArgument = ArgumentType.String("sender")

    val receiver = ArgumentType.String("receiver")

    val delay = ArgumentType.Time("delay")
    val message = ArgumentType.String("message")
        .map { it.asMini() }

    val messages = ArgumentType.Loop("messages",
        ArgumentType.Group(
            "messageGroup",
            senderArgument, receiver, delay, message
        )
    )

    applyHelp {
        """
            The dialogue command allows you to test and create dialogue scripts.
            
            Dialogue uses looped arguments, which repeats a certain argument.
            
            The dialogue argument is as follows: <yellow>(sender) (receiver) (delay) (message)
            <blue>sender, receiver, <gray>and <blue>message<gray> need to be enclosed in quotes.
            
            You can repeat this syntax as many times as you want.
            
            Syntax: /dialogue (...messages)
        """.trimIndent()
    }

    syntax(messages) {

        (!messages).fold(0L) { initial, element ->
            Manager.scheduler.buildTask {
                sender.sendMessage(Dialogue.create(element[senderArgument], element[receiver], element[message]))
            }.delay(Duration.ofMillis(initial + element[delay].toMillis())).schedule()

            initial + element[delay].toMillis()
        }
    }

}, "dialogue")