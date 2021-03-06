package com.dumbdogdiner.stickyworld.bukkit.world

import com.dumbdogdiner.stickyworld.api.world.WorldData
import com.dumbdogdiner.stickyworld.api.world.WorldManager
import org.bukkit.World

class SkWorldManager : WorldManager {
	override fun loadWorld(world: WorldData): Boolean {
		TODO("Not yet implemented")
	}

	override fun unloadWorld(world: World): Boolean {

	}

	override fun copyWorld(world: WorldData, name: String): WorldData? {
		TODO("Not yet implemented")
	}

	override fun resolve(data: WorldData): World? {
		TODO("Not yet implemented")
	}

}