package world.cepi.dialogue

import net.minestom.server.extensions.Extension;
import world.cepi.scriptable.script.Script

class DialogueExtension : Extension() {

    override fun initialize() {
        DialogueCommand.register()

        Script.objects["Dialogue"] = Dialogue

        logger.info("[Dialogue] has been enabled!")
    }

    override fun terminate() {
        DialogueCommand.unregister()

        logger.info("[Dialogue] has been disabled!")
    }

}
