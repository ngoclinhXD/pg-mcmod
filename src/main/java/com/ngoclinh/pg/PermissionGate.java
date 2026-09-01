package com.ngoclinh.pg;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.BlockEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;

import com.ngoclinh.pg.permission.Permissions;

public class PermissionGate implements ModInitializer {

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess, environment) ->
						registerCommands(dispatcher)
		);

		PlayerBlockBreakEvents.BEFORE.register(
				(world, player, pos, state, blockEntity) -> {
					if (!(player instanceof net.minecraft.server.level.ServerPlayer serverPlayer)) {
						return true;
					}

					return Permissions.canBreak(serverPlayer);
				}
		);

		BlockEvents.USE_ITEM_ON.register(
				(itemStack, blockState, world, pos, player, hand, hitResult) -> {
					if (!(player instanceof net.minecraft.server.level.ServerPlayer serverPlayer)) {
						return null;
					}

					if (!Permissions.canInteract(serverPlayer)) {
						return InteractionResult.FAIL;
					}

					return null;
				}
		);

		BlockEvents.USE_WITHOUT_ITEM.register(
				(blockState, world, pos, player, hitResult) -> {
					if (!(player instanceof net.minecraft.server.level.ServerPlayer serverPlayer)) {
						return null;
					}

					if (!Permissions.canInteract(serverPlayer)) {
						return InteractionResult.FAIL;
					}

					return null;
				}
		);

		UseEntityCallback.EVENT.register(
				(player, world, hand, entity, hitResult) -> {
					if (!(player instanceof net.minecraft.server.level.ServerPlayer serverPlayer)) {
						return InteractionResult.PASS;
					}

					if (!Permissions.canInteract(serverPlayer)) {
						return InteractionResult.FAIL;
					}

					return InteractionResult.PASS;
				}
		);

		UseItemCallback.EVENT.register(
				(player, world, hand) -> {
					if (!(player instanceof net.minecraft.server.level.ServerPlayer serverPlayer)) {
						return InteractionResult.PASS;
					}

					return Permissions.canUse(serverPlayer)
							? InteractionResult.PASS
							: InteractionResult.FAIL;
				}
		);

		AttackEntityCallback.EVENT.register(
				(player, world, hand, entity, hitResult) -> {
					if (!(player instanceof net.minecraft.server.level.ServerPlayer serverPlayer)) {
						return InteractionResult.PASS;
					}

					if (entity instanceof ItemFrame || entity instanceof ArmorStand) {
						return Permissions.canInteract(serverPlayer)
								? InteractionResult.PASS
								: InteractionResult.FAIL;
					}

					return Permissions.canAttack(serverPlayer)
							? InteractionResult.PASS
							: InteractionResult.FAIL;
				}
		);

		System.out.println("PermissionGate initialized.");
	}

	private static void registerCommands(
			CommandDispatcher<CommandSourceStack> dispatcher
	) {
		dispatcher.register(
				Commands.literal("pgtest")
						.executes(context -> {
							CommandSourceStack source = context.getSource();

							if (!(source.getEntity() instanceof net.minecraft.server.level.ServerPlayer player)) {
								source.sendFailure(
										Component.literal("Players only.")
								);
								return 0;
							}

							if (!Permissions.canTest(player)) {
								source.sendFailure(
										Component.literal("§cYou don't have permissiongate.test")
								);
								return 0;
							}

							source.sendSuccess(
									() -> Component.literal("§aPermission granted!"),
									false
							);

							return 1;
						})
		);
	}
}
