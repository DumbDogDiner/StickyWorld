package com.dumbdogdiner.stickyworld.api.world;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides the typedef for the world manager.
 */
public interface WorldManager {
	/**
	 * Attempt to load the target world.
	 * @param world The data object for the target world
	 * @return True if the action was successful.
	 */
	@NotNull Boolean loadWorld(@NotNull WorldData world);

	/**
	 * Attempt to unload the target world.
	 * @param world The world to unload
	 * @return True if the action was successful.
	 */
	@NotNull Boolean unloadWorld(@NotNull World world);

	/**
	 * Attempt to unload the target world.
	 * @param data The target world
	 * @return True if the action was successful.
	 */
	default @NotNull Boolean unloadWorld(@NotNull WorldData data) {
		World world = this.resolve(data);
		// world does not exist or is already unloaded.
		if (world == null) {
			return true;
		}
		return this.unloadWorld(world);
	}

	/**
	 * Copy the target world into a new world with the target name.
	 * @param world The target world
	 * @param name The name of the new world
	 * @return A new {@link WorldData} object containing data of the new world.
	 */
	@Nullable WorldData copyWorld(@NotNull WorldData world, @NotNull String name);

	/**
	 * Attempt to resolve the target world data into a loaded world.
	 * @param data The world data of the target world
	 * @return A {@link World} object, if the world is loaded.
	 */
	@Nullable World resolve(@NotNull WorldData data);
}
