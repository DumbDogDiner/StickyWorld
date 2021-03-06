package com.dumbdogdiner.stickyworld.bukkit

/**
 * Utility interface for quickly accessing plugin fields.
 */
interface WithPlugin {
	// A reference to the singleton plugin instance
	val plugin
		get() = SkWorldPlugin.plugin

	// A reference to the plugin's logger
	val logger
		get() = this.plugin.logger

	// A reference to the plugin's configuration
	val config
		get() = this.plugin.config
}
