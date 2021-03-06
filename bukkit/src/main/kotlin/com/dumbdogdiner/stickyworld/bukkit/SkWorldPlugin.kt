/*
 * Copyright (c) 2020 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information.
 */
package com.dumbdogdiner.stickyworld.bukkit

import kr.entree.spigradle.annotations.PluginMain
import org.bukkit.plugin.java.JavaPlugin

@PluginMain
class SkWorldPlugin : JavaPlugin() {
	companion object {
		// keep a static reference to the plugin - singleton instance
		lateinit var plugin: SkWorldPlugin
	}

	override fun onLoad() {
		// set the singleton instance
		plugin = this
	}
}
