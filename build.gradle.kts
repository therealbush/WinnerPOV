import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("fabric-loom") version "0.12-SNAPSHOT"
}

val modId = "Winner-POV"
val modVersion = "10.1-Helsinki"

val minecraft = "1.19-rc2"
val kotlin = "1.6.21"

val fabricAPI = "0.55.1+1.19"
val yarn = "+build.1"
val fabricLoader = "0.14.6"

repositories {

    maven {
        url = uri("https://jitpack.io")
    }

    mavenCentral()
}

dependencies {

    minecraft("com.mojang:minecraft:$minecraft")
    mappings("net.fabricmc:yarn:$minecraft$yarn:v2")

    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricAPI")
    modImplementation("net.fabricmc:fabric-loader:$fabricLoader")

    implementation("com.github.therealbush:eventbus-kotlin:1.0.1")
    implementation("com.github.therealbush:translator:1.0.0")

    arrayOf("jse", "jme").forEach {
        implementation("org.luaj:luaj-$it:3.0.1")
    }

    implementation("com.google.code.gson:gson:2.9.0")

    implementation(kotlin("stdlib", kotlin))
    implementation(kotlin("stdlib-jdk8", kotlin))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
}

base {
    archivesBaseName = "$modId-$modVersion"
}

tasks {

    getByName<ProcessResources>("processResources") {
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
