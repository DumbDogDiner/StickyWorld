package com.dumbdogdiner.stickyworld.bukkit.world

import com.dumbdogdiner.stickyworld.api.world.WorldData
import com.dumbdogdiner.stickyworld.api.world.WorldProvider
import com.dumbdogdiner.stickyworld.bukkit.WithPlugin
import com.dumbdogdiner.stickyworld.bukkit.data.SpigotWorldData
import com.dumbdogdiner.stickyworld.bukkit.util.Constants
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.UUID

class SkWorldProvider : WithPlugin, WorldProvider {
	private val worldCache = mutableMapOf<String, SpigotWorldData>()
	private val worldConfig = YamlConfiguration()

	/**
	 * Initialize plugin world configuration.
	 */
	fun initializeWorldConfig(): Boolean {
		val configFile = File(this.plugin.dataFolder, "worlds.yml")
		if (!configFile.exists()) {
			configFile.createNewFile()
		}
		// attempt to load world configuration
		try {
			this.worldConfig.load(configFile)
		} catch(e: Exception) {
			this.logger.severe("Failed to load plugin world configuration! Is worlds.yml invalid?")
			return false
		}
		return true
	}

	/**
	 * Initialize the data cache and load worlds.
	 */
	fun initialize(): Boolean {
		// StickyWorld/     ../      ../
		//             plugins/ server/

		// read world data from both spigot and bukkit configuration
		this.logger.info("Merging Bukkit and Spigot world configurations...")

		// load plugin configuration
		if (!initializeWorldConfig()) {
			return false
		}

		// these must exist at runtime - no need to check if they exist
		val bukkitConfig = YamlConfiguration()
		val spigotConfig = YamlConfiguration()
		try {
			bukkitConfig.load(this.plugin.dataFolder.parentFile.resolveSibling("bukkit.yml"))
			spigotConfig.load(this.plugin.dataFolder.parentFile.resolveSibling("spigot.yml"))
		} catch(e: Exception) {
			this.logger.severe("Failed to load world configuration!")
			e.printStackTrace()
			return false
		}

		val bukkitWorlds = bukkitConfig.getConfigurationSection("worlds")?.getKeys(false)

		// find world keys - this is quite slow
		val spigotWorlds = spigotConfig.getConfigurationSection("world-settings")?.getKeys(false)?.filter { !Constants.SPIGOT_WORLD_CONFIG_KEYS.contains(it) }
			// no spigot defined worlds - we don't need to merge config
			?: return true

		spigotWorlds.forEach {
			worldCache[it] = SpigotWorldData.deserialize(spigotConfig.getConfigurationSection("world-settings")!!.getConfigurationSection(it)!!)
		}

		return true
	}

	override fun getWorldData(): List<WorldData> {
		TODO("Not yet implemented")
	}

	override fun getWorld(name: String?): WorldData? {
		TODO("Not yet implemented")
	}

	override fun getWorld(uuid: UUID?): WorldData? {
		TODO("Not yet implemented")
	}
}