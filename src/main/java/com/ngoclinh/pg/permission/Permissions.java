package com.ngoclinh.pg.permission;

import net.fabricmc.fabric.api.permission.v1.PermissionNode;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

public final class Permissions {

    private static final PermissionNode<Boolean> TEST =
            PermissionNode.of(Identifier.fromNamespaceAndPath(
                    "permissiongate",
                    "test"
            ));

    private static final PermissionNode<Boolean> BREAK =
            PermissionNode.of(Identifier.fromNamespaceAndPath(
                    "pg",
                    "break"
            ));

    private static final PermissionNode<Boolean> INTERACT =
            PermissionNode.of(Identifier.fromNamespaceAndPath(
                    "pg",
                    "interact"
            ));

    private static final PermissionNode<Boolean> USE =
            PermissionNode.of(Identifier.fromNamespaceAndPath(
                    "pg",
                    "use"
            ));

    private static final PermissionNode<Boolean> ATTACK =
            PermissionNode.of(Identifier.fromNamespaceAndPath(
                    "pg",
                    "attack"
            ));

    private Permissions() {
    }

    public static boolean canTest(ServerPlayer player) {
        return player.checkPermission(TEST, false);
    }

    public static boolean canBreak(ServerPlayer player) {
        return player.checkPermission(BREAK, false);
    }

    public static boolean canInteract(ServerPlayer player) {
        return player.checkPermission(INTERACT, false);
    }

    public static boolean canUse(ServerPlayer player) {
        return player.checkPermission(USE, false);
    }

    public static boolean canAttack(ServerPlayer player) {
        return player.checkPermission(ATTACK, false);
    }
}