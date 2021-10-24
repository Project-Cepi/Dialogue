package world.cepi.dialogue

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

object Dialogue {

    fun create(
        sender: String,
        receiver: String,
        message: Component
    ) = Component.text(sender, NamedTextColor.GOLD)
        .append(Component.space())
        .append(Component.text("->", NamedTextColor.WHITE))
        .append(Component.space())
        .append(Component.text(receiver, NamedTextColor.YELLOW))
        .append(Component.text(" // ", NamedTextColor.DARK_GRAY))
        .append(message.color(NamedTextColor.GRAY))

}