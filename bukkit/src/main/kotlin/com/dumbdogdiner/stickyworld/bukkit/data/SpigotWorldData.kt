package com.dumbdogdiner.stickyworld.bukkit.data

import org.bukkit.configuration.ConfigurationSection

data class SpigotWorldData(
	var verbose: Boolean,
	var viewDistance: Int,
	var mergeRadius: MutableMap<MergeType, Double>,
	var chunksPerTick: Int,
	var itemDespawnRate: Int,
	var mobSpawnRange: Int,
	var growth: MutableMap<Crop, Int>,
	var entityActivationRange: MutableMap<ActivatableEntity, Int>,
	var tickInactiveVillagers: Boolean,
	var entityTrackingRange: MutableMap<TrackableEntity, Int>,
	var saveStructureInfo: Boolean,
	var randomLightUpdates: Boolean,
	var nerfSpawnerMobs: Boolean,
	var zombieAggressiveTowardsVillager: Boolean,
	var enableZombiePigmenPortalSpawns: Boolean,
	var maxEntityCollisions: Int,
	var dragonDeathSoundRadius: Int,
	var witherSpawnSoundRadius: Int,
	var maxBulkChunks: Int,
	var maxTickTime: MutableMap<Tickable, Int>,
	var clearTickList: Boolean,
	var hopperAltTicking: Boolean,
	var hopperAmount: Int,
	var seedVillage: Long,
	var seedFeature: Long,
	var seedMonument: Long,
	var seedSlime: Long,
	var hunger: MutableMap<HungerProperty, Double>,
	var hangingTickFrequency: Int,
	var ticksPer: MutableMap<HopperTick, Int>
) {
	enum class MergeType {
		EXP,
		ITEM
	}

	/**
	 * Represents types of crop stored by the growth key.
	 */
	enum class Crop {
		CACTUS,
		MELON,
		PUMPKIN,
		SAPLING,
		CANE,
		MUSHROOM,
		WHEAT;
	}

	/**
	 * Represents types of activatable entities stored by the entityActivationRange section.
	 */
	enum class ActivatableEntity {
		ANIMALS,
		MONSTERS,
		MISC
	}

	/**
	 * Represents types of trackable entities stored by the entityTrackingRange section.
	 */
	enum class TrackableEntity {
		PLAYERS,
		ANIMALS,
		MONSTERS,
		MISC,
		OTHER
	}

	/**
	 * Represents types of tickable objects stored by the maximumTickTime section.
	 */
	enum class Tickable {
		TILE,
		ENTITY
	}

	/**
	 * Represents types of hunger multipliers stored by the hunger section.
	 */
	enum class HungerProperty {
		JUMP_WALK_EXHAUSTION,
		JUMP_SPRINT_EXHAUSTION,
		COMBAT_EXHAUSTION,
		REGEN_EXHAUSTION,
		SWIM_MULTIPLIER,
		SPRINT_MULTIPLIER,
		OTHER_MULTIPLIER,
	}

	/**
	 * Represents types of hopper ticking stored by the ticksPer section.
	 */
	enum class HopperTick {
		HOPPER_TRANSFER,
		HOPPER_CHECK
	}

	companion object {
		/**
		 * Get the default spigot world data settings.
		 */
		fun getDefault(): SpigotWorldData {
			return SpigotWorldData(
				verbose = true,
				viewDistance = 10,
				mergeRadius = mutableMapOf(
					MergeType.EXP to 3.0,
					MergeType.ITEM to 2.5
				),
				chunksPerTick = 650,
				itemDespawnRate = 6000,
				mobSpawnRange = 6,
				growth = mutableMapOf(
					Crop.CACTUS to 100,
					Crop.MELON to 100,
					Crop.PUMPKIN to 100,
					Crop.SAPLING to 100,
					Crop.CANE to 100,
					Crop.MUSHROOM to 100,
					Crop.WHEAT to 100
				),
				entityActivationRange = mutableMapOf(
					ActivatableEntity.ANIMALS to 32,
					ActivatableEntity.MONSTERS to 32,
					ActivatableEntity.MISC to 32
				),
				tickInactiveVillagers = true,
				entityTrackingRange = mutableMapOf(
					TrackableEntity.PLAYERS to 48,
					TrackableEntity.ANIMALS to 48,
					TrackableEntity.MONSTERS to 48,
					TrackableEntity.MISC to 32,
					TrackableEntity.OTHER to 64
				),
				saveStructureInfo = true,
				randomLightUpdates = false,
				nerfSpawnerMobs = false,
				zombieAggressiveTowardsVillager = true,
				enableZombiePigmenPortalSpawns = true,
				maxEntityCollisions = 8,
				dragonDeathSoundRadius = 0,
				witherSpawnSoundRadius = 0,
				maxBulkChunks = 10,
				maxTickTime = mutableMapOf(
					Tickable.TILE to 50,
					Tickable.ENTITY to 50,
				),
				clearTickList = false,
				hopperAltTicking = false,
				hopperAmount = 1,
				seedVillage = 10387312,
				seedFeature = 14357617,
				seedMonument = 10387313,
				seedSlime = 987234911,
				hunger = mutableMapOf(
					HungerProperty.JUMP_WALK_EXHAUSTION to 0.05,
					HungerProperty.JUMP_SPRINT_EXHAUSTION to 0.2,
					HungerProperty.COMBAT_EXHAUSTION to 0.1,
					HungerProperty.REGEN_EXHAUSTION to 6.0,
					HungerProperty.SWIM_MULTIPLIER to 0.01,
					HungerProperty.SPRINT_MULTIPLIER to 0.1,
					HungerProperty.OTHER_MULTIPLIER to 0.0
				),
				hangingTickFrequency = 100,
				ticksPer = mutableMapOf(
					HopperTick.HOPPER_TRANSFER to 8,
					HopperTick.HOPPER_CHECK to 1
				)
			)
		}

		/**
		 * Deserialize a configuration section into world data.
		 */
		fun deserialize(section: ConfigurationSection): SpigotWorldData {
			val data = getDefault()

			// hacky let setting because ConfigurationSection never returns null
			// i would love to loop over Constants.SPIGOT_WORLD_CONFIG_KEYS but i don't think i can.
			// enum maps can probably be iterated.
			(section.get("verbose") as Boolean?)?.let{ data.verbose = it }
			(section.get("viewDistance") as Int?)?.let { data.viewDistance = it }

			(section.get("merge-radius.exp") as Double?)?.let { data.mergeRadius[MergeType.EXP] = it }
			(section.get("merge-radius.item") as Double?)?.let { data.mergeRadius[MergeType.ITEM] = it }

			(section.get("chunks-per-tick") as Int?)?.let { data.chunksPerTick = it }
			(section.get("item-despawn-rate") as Int?)?.let { data.itemDespawnRate = it }
			(section.get("mob-spawn-range") as Int?)?.let { data.mobSpawnRange = it }

			(section.get("growth.cactus") as Int?)?.let { data.growth[Crop.CACTUS] = it }
			(section.get("growth.melon") as Int?)?.let { data.growth[Crop.MELON] = it }
			(section.get("growth.pumpkin") as Int?)?.let { data.growth[Crop.PUMPKIN] = it }
			(section.get("growth.sapling") as Int?)?.let { data.growth[Crop.SAPLING] = it }
			(section.get("growth.cane") as Int?)?.let { data.growth[Crop.CANE] = it }
			(section.get("growth.mushroom") as Int?)?.let { data.growth[Crop.MUSHROOM] = it }
			(section.get("growth.wheat") as Int?)?.let { data.growth[Crop.WHEAT] = it }

			(section.get("entity-activation-range.animals") as Int?)?.let { data.entityActivationRange[ActivatableEntity.ANIMALS] = it }
			(section.get("entity-activation-range.monsters") as Int?)?.let { data.entityActivationRange[ActivatableEntity.MONSTERS] = it }
			(section.get("entity-activation-range.misc") as Int?)?.let { data.entityActivationRange[ActivatableEntity.MISC] = it }

			(section.get("tick-inactive-villagers") as Boolean?)?.let { data.tickInactiveVillagers = it }

			(section.get("entity-tracking-range.players") as Int?)?.let { data.entityTrackingRange[TrackableEntity.PLAYERS] = it }
			(section.get("entity-tracking-range.animals") as Int?)?.let { data.entityTrackingRange[TrackableEntity.ANIMALS] = it }
			(section.get("entity-tracking-range.monsters") as Int?)?.let { data.entityTrackingRange[TrackableEntity.MONSTERS] = it }
			(section.get("entity-tracking-range.misc") as Int?)?.let { data.entityTrackingRange[TrackableEntity.MISC] = it }

			(section.get("save-structure-info") as Boolean?)?.let { data.saveStructureInfo = it }
			(section.get("random-light-updates") as Boolean?)?.let { data.randomLightUpdates = it }
			(section.get("nerf-spawner-mobs") as Boolean?)?.let { data.nerfSpawnerMobs = it }
			(section.get("zombie-aggressive-towards-villager") as Boolean?)?.let { data.zombieAggressiveTowardsVillager = it }
			(section.get("enable-zombie-pigmen-portal-spawns") as Boolean?)?.let { data.enableZombiePigmenPortalSpawns = it }

			(section.get("max-entity-collisions") as Int?)?.let { data.maxEntityCollisions = it }
			(section.get("dragon-death-sound-radius") as Int?)?.let { data.dragonDeathSoundRadius = it }
			(section.get("wither-spawn-sound-radius") as Int?)?.let { data.witherSpawnSoundRadius = it }
			(section.get("max-bulk-chunks") as Int?)?.let { data.maxBulkChunks = it }

			(section.get("max-tick-time.tile") as Int?)?.let { data.maxTickTime[Tickable.TILE] = it }
			(section.get("max-tick-time.entity") as Int?)?.let { data.maxTickTime[Tickable.ENTITY] = it }

			(section.get("clear-tick-list") as Boolean?)?.let { data.clearTickList = it }
			(section.get("hopper-alt-ticking") as Boolean?)?.let { data.hopperAltTicking = it }

			(section.get("hopper-amount") as Int?)?.let { data.hopperAmount = it }

			(section.get("seed-village") as Long?)?.let { data.seedVillage = it }
			(section.get("seed-feature") as Long?)?.let { data.seedFeature = it }
			(section.get("seed-monument") as Long?)?.let { data.seedMonument = it }
			(section.get("seed-slime") as Long?)?.let { data.seedSlime = it }

			(section.get("hunger.jump-walk-exhaustion") as Double?)?.let { data.hunger[HungerProperty.JUMP_WALK_EXHAUSTION] = it }
			(section.get("hunger.jump-sprint-exhaustion") as Double?)?.let { data.hunger[HungerProperty.JUMP_SPRINT_EXHAUSTION] = it }
			(section.get("hunger.combat-exhaustion") as Double?)?.let { data.hunger[HungerProperty.COMBAT_EXHAUSTION] = it }
			(section.get("hunger.regen-exhaustion") as Double?)?.let { data.hunger[HungerProperty.REGEN_EXHAUSTION] = it }
			(section.get("hunger.swim-multiplier") as Double?)?.let { data.hunger[HungerProperty.SWIM_MULTIPLIER] = it }
			(section.get("hunger.sprint-multiplier") as Double?)?.let { data.hunger[HungerProperty.SPRINT_MULTIPLIER] = it }
			(section.get("hunger.other-multiplier") as Double?)?.let { data.hunger[HungerProperty.OTHER_MULTIPLIER] = it }

			(section.get("hanging-tick-frequency") as Int?)?.let { data.hangingTickFrequency = it }

			(section.get("ticks-per.hopper-transfer") as Int?)?.let { data.ticksPer[HopperTick.HOPPER_TRANSFER] = it }
			(section.get("ticks-per.hopper-check") as Int?)?.let { data.ticksPer[HopperTick.HOPPER_CHECK] = it }

			return data
		}

		/**
		 * Static reference to the default to save memory. DO NOT MODIFY.
		 */
		val DEFAULT = getDefault()
	}
}
