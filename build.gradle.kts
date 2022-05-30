import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("fabric-loom") version "0.12-SNAPSHOT"
}

val minecraftVersion = "1.18.2"
val kotlinVersion = "1.6.21"

val fabricAPI = "0.53.0+1.18.2"
val yarn = "1.18.2+build.3:v2"
val loaderVersion = "0.14.6"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarn")

    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricAPI")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
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

