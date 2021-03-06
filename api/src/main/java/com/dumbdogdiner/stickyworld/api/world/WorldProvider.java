package com.dumbdogdiner.stickyworld.api.world;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * Provides data on stored worlds.
 */
public interface WorldProvider {
	/**
	 * @return A list of {@link WorldData} objects for currently cached worlds.
	 */
	@NotNull List<WorldData> getWorldData();

	/**
	 * Fetch data for the world with the target name.
	 * @param name The name of the world
	 * @return A {@link WorldData} object for the world, or `null` if none is found.
	 */
	@Nullable WorldData getWorld(@NotNull String name);

	/**
	 * Fetch data for the world with the target unique id.
	 * @param uuid The UUID of the world
	 * @return A {@link WorldData} object for the world, or `null` if none is found.
	 */
	@Nullable WorldData getWorld(@NotNull UUID uuid);
}
