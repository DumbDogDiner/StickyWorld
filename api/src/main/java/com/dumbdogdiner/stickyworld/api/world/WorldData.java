package com.dumbdogdiner.stickyworld.api.world;

import java.util.UUID;

/**
 * Represents stored world data.
 */
public interface WorldData {
	/**
	 * @return The name of this world.
	 */
	String getName();

	/**
	 * @return The unique ID of this world.
	 */
	UUID getUniqueId();

	/**
	 * @return The seed of this world.
	 */
	Long getSeed();
}
