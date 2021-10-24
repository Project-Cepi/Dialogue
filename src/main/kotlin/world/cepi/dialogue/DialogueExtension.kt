package world.cepi.dialogue

import net.minestom.server.extensions.Extension;

class DialogueExtension : Extension() {

    override fun initialize() {
        DialogueCommand.register()

        logger.info("[Dialogue] has been enabled!")
    }

    override fun terminate() {
        DialogueCommand.unregister()

        logger.info("[Dialogue] has been disabled!")
    }

}
