import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("fabric-loom") version "0.11-SNAPSHOT"
}

val minecraftVersion = "1.18.2"
val kotlinVersion = "1.6.20"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")

    mappings("net.fabricmc:yarn:$minecraftVersion+build.1:v2")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.48.0+$minecraftVersion")
    modImplementation("net.fabricmc:fabric-loader:0.13.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    implementation("io.github.classgraph:classgraph:4.8.146")
    implementation("org.reflections:reflections:0.10.2")

    implementation(kotlin("stdlib", kotlinVersion))
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
}

tasks {
    getByName<ProcessResources>("processResources") {
        inputs.property("version", version)

        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to version))
        }
    }

    withType(JavaCompile::class) {
        options.encoding = "UTF-8"
    }

    withType(KotlinCompile::class) {
        kotlinOptions.jvmTarget = "17"
    }
}

