package studio.abos.mc.strangeadventures.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.blay09.mods.balm.commands.BalmCommands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModCommands {

    public static void initialize(final BalmCommands commands) {
        commands.register(ModCommands::register);
    }

    private static void register(final CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(StrangeAdventures.MOD_ID)
                .then(Commands.literal("green_avatar")
                        .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER))
                        .then(Commands.argument("players", EntityArgument.players())
                                .then(Commands.literal("activate")
                                        .executes(ModCommands::activateGreenAvatar))
                                .then(Commands.literal("deactivate")
                                        .executes(ModCommands::deactivateGreenAvatar))
                        )
                )
        );
    }

    private static int activateGreenAvatar(final CommandContext<CommandSourceStack> context) {
        try {
            for (final ServerPlayer player : EntityArgument.getPlayers(context, "players")) {
                StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA.getOrCreate(player).setActive(true);
            }
        } catch (final CommandSyntaxException ex) {
            return 0;
        }
        return 1;
    }

    private static int deactivateGreenAvatar(final CommandContext<CommandSourceStack> context) {
        try {
            for (final ServerPlayer player : EntityArgument.getPlayers(context, "players")) {
                StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA.getOrCreate(player).setActive(false);
            }
        } catch (final CommandSyntaxException ex) {
            return 0;
        }
        return 1;
    }
}
