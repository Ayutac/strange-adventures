package studio.abos.mc.strangeadventures.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.blay09.mods.balm.commands.BalmCommands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.Entity;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.entity.GreenAvatarCloneEntity;

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
                                .then(Commands.literal("mass")
                                        .then(Commands.literal("set")
                                                .then(Commands.argument("amount", FloatArgumentType.floatArg())
                                                        .executes(ModCommands::setGreenAvatarMass)
                                                )
                                        )
                                        .then(Commands.literal("add")
                                                .then(Commands.argument("amount", FloatArgumentType.floatArg())
                                                        .executes(ModCommands::addGreenAvatarMass)
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("dominate")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .then(Commands.argument("clones", EntityArgument.entities())
                                                .executes(ModCommands::dominateGreenAvatarClones)
                                        )
                                )
                        )
                )
        );
    }

    private static int activateGreenAvatar(final CommandContext<CommandSourceStack> context) {
        try {
            for (final ServerPlayer player : EntityArgument.getPlayers(context, "players")) {
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActivate(player);
            }
        } catch (final CommandSyntaxException ex) {
            return 0;
        }
        return 1;
    }

    private static int deactivateGreenAvatar(final CommandContext<CommandSourceStack> context) {
        try {
            for (final ServerPlayer player : EntityArgument.getPlayers(context, "players")) {
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarDeactivate(player);
            }
        } catch (final CommandSyntaxException ex) {
            return 0;
        }
        return 1;
    }

    private static int setGreenAvatarMass(final CommandContext<CommandSourceStack> context) {
        final float amount = FloatArgumentType.getFloat(context, "amount");
        try {
            for (final ServerPlayer player : EntityArgument.getPlayers(context, "players")) {
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarSetMass(player, amount);
            }
        } catch (final CommandSyntaxException ex) {
            return 0;
        }
        return 1;
    }

    private static int addGreenAvatarMass(final CommandContext<CommandSourceStack> context) {
        final float amount = FloatArgumentType.getFloat(context, "amount");
        try {
            for (final ServerPlayer player : EntityArgument.getPlayers(context, "players")) {
                StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarAddMass(player, amount);
            }
        } catch (final CommandSyntaxException ex) {
            return 0;
        }
        return 1;
    }

    private static int dominateGreenAvatarClones(final CommandContext<CommandSourceStack> context) {
        try {
            final ServerPlayer player = EntityArgument.getPlayer(context, "player");
            if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
                for (final Entity entity : EntityArgument.getEntities(context, "clones")) {
                    if (entity instanceof final GreenAvatarCloneEntity clone) {
                        clone.setOwner(player);
                    }
                }
            }
        } catch (CommandSyntaxException ex) {
            return 0;
        }
        return 1;
    }

}
