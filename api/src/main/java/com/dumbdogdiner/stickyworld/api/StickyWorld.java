/*
 * Copyright (c) 2020 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information.
 */
package com.dumbdogdiner.stickyworld.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a generic implementation of your plugin.
 */
public interface StickyWorld {
	/**
	* Register the API service.
	*
	* @param plugin  The plugin registering the service
	* @param service The plugin's implementation of the service
	*/
	static void registerService(JavaPlugin plugin, StickyWorld service) {
	Bukkit
		.getServicesManager()
		.register(StickyWorld.class, service, plugin, ServicePriority.Lowest);
	}

	/**
	* Fetch the instantiated API service object.
	*
	* @return {@link StickyWorld}
	*/
	@NotNull
	static StickyWorld getService() {
		var provider = Bukkit
			.getServicesManager()
			.getRegistration(StickyWorld.class);
		if (provider == null) {
			throw new RuntimeException(
				"Cannot access API service - has not been registered!"
			);
		}
		return provider.getProvider();
	}

	/**
	* Return a reference to the plugin providing the StickyWorld implementation.
	*
	* @return {@link Plugin}
	*/
	Plugin getProvider();
}
