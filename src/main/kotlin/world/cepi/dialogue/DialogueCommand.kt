package world.cepi.dialogue

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.minestom.server.command.builder.arguments.ArgumentType
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
            delay, message
        )
    )

    syntax(senderArgument, receiver, messages) {

        (!messages).fold(0L) { initial, element ->
            Manager.scheduler.buildTask {
                sender.sendMessage(Dialogue.create(context[senderArgument], context[receiver], element[message]))
            }.delay(Duration.ofMillis(initial + element[delay].toMillis())).schedule()

            initial + element[delay].toMillis()
        }
    }

}, "dialogue")