import kr.entree.spigradle.kotlin.paper
import kr.entree.spigradle.kotlin.papermc

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("kr.entree.spigradle")
}

repositories {
    papermc()
    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}

dependencies {
    // jvm and kotlin dependencies
    implementation(kotlin("stdlib"))
    implementation(project(":api"))

    // server dependencies
    compileOnly(paper())
}

spotless {
    kotlin {
        prettier(mapOf(
            "prettier" to "2.2.1",
            "prettier-plugin-kotlin" to "2.0.0"
        )).config(mapOf(
            "parser" to "kotlin",
            "useTabs" to true
        ))
        licenseHeaderFile(rootProject.file("LICENSE_HEADER"))
    }
}

tasks {
    build {
        dependsOn("shadowJar")
    }

    shadowJar {
        archiveClassifier.set("")
    }

    spigot {
        name = "StickyWorld"
        authors = mutableListOf("SkyezerFox")
        apiVersion = "1.16"
        softDepends = mutableListOf()
    }
}
