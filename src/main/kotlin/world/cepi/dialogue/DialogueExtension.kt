package world.cepi.dialogue

import net.minestom.server.extensions.Extension;

class DialogueExtension : Extension() {

    override fun initialize() {
        logger.info("[Dialogue] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Dialogue] has been disabled!")
    }

}
