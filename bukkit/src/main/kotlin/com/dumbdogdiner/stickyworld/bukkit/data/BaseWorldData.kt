package com.dumbdogdiner.stickyworld.bukkit.data

import java.util.UUID

data class BaseWorldData(
	val name: String,
	val uid: UUID,
	val seed: Long,
)
