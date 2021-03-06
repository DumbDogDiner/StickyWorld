package com.dumbdogdiner.stickyworld.bukkit.data

import com.dumbdogdiner.stickyworld.api.world.WorldData
import java.util.UUID

data class SkWorldData(
	val baseWorldData: BaseWorldData,
	val spigotWorldData: SpigotWorldData,
	val bukkitWorldData: BukkitWorldData
) : WorldData {
	override fun getName(): String {
		return this.baseWorldData.name
	}

	override fun getUniqueId(): UUID {
		return this.baseWorldData.uid
	}

	override fun getSeed(): Long {
		return this.baseWorldData.seed
	}
}
