# PermissionGate

**PermissionGate** is a server-side Minecraft Fabric mod that provides granular permission control over gameplay actions using the Fabric Permissions API and LuckPerms.

### Current Permissions

| Permission    | Action                                    |
| ------------- | ----------------------------------------- |
| `pg.break`    | Break blocks                              |
| `pg.interact` | Interact with blocks and special entities |
| `pg.use`      | Use items                                 |
| `pg.attack`   | Attack entities                           |

> There is a bug with `pg.interact` that when user throw an egg for example, the client won't see the egg in their inventory but actually it's still there according to the server. The player can see the egg again when they click on that egg's slot in the inventory.

## Planned Permissions

More granular permissions are planned, including:

```text
pg.drop
pg.pickup
pg.container
pg.inventory
pg.craft
pg.enchant
pg.anvil
pg.trade
pg.smith
pg.brew
pg.mount
pg.vehicle
pg.fishing
pg.sleep
pg.respawn
pg.teleport
pg.dimension
pg.chat
```

> These permissions can be merged into one or won't be implemented. Check GitHub for updates.

Command and administrative permissions will also be added:

```text
pg.command.*
pg.admin.*
pg.admin.bypass
pg.admin.reload
pg.admin.debug
```

## Requirements

* Fabric API **0.158.0+26.2**
* LuckPerms **5.5.57** for Fabric / Minecraft 26.2

## How It Works

```text
Player Action
     ↓
PermissionGate
     ↓
Fabric Permissions API
     ↓
LuckPerms
     ↓
Allow / Deny
```

PermissionGate does not replace LuckPerms. It uses the Fabric Permissions API as the permission interface and lets LuckPerms handle the actual permission storage and management.

## Development Status

> These permissions can be merged into one or won't be implemented.

### Phase 1 — Complete

* [x] `pg.break`
* [x] `pg.interact`
* [x] `pg.use`
* [x] `pg.attack`

### Phase 2 — Planned

* [ ] `pg.drop`
* [ ] `pg.pickup`
* [ ] `pg.container`
* [ ] `pg.inventory`
* [ ] `pg.craft`
* [ ] `pg.enchant`
* [ ] `pg.anvil`
* [ ] `pg.trade`
* [ ] `pg.smith`
* [ ] `pg.brew`
* [ ] `pg.mount`
* [ ] `pg.vehicle`
* [ ] `pg.fishing`
* [ ] `pg.sleep`
* [ ] `pg.respawn`
* [ ] `pg.teleport`
* [ ] `pg.dimension`
* [ ] `pg.chat`
* [ ] `pg.command.*`
* [ ] `pg.admin.*`

