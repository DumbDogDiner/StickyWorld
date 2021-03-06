package com.dumbdogdiner.stickyworld.bukkit

import com.dumbdogdiner.stickyworld.api.StickyWorld
import org.bukkit.plugin.Plugin

object ApiProvider : WithPlugin, StickyWorld {
	override fun getProvider(): Plugin {
		return this.plugin
	}

}
