import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("fabric-loom") version "0.12-SNAPSHOT"
}

val minecraftVersion = "1.19-pre3"
val kotlinVersion = "1.6.21"

val fabricAPI = "0.53.4+1.19"
val yarn = "1.19-pre3+build.1"
val loaderVersion = "0.14.6"

repositories {
    maven {
        url = uri("https://jitpack.io")
    }

    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarn")

    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricAPI")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    implementation("com.github.therealbush:eventbus-kotlin:1.0.1")
    //implementation("com.github.therealbush:translator:1.0.0")

    implementation("com.google.code.gson:gson:2.9.0")

    for (ind in arrayOf("jse", "jme"))
        implementation("org.luaj:luaj-$ind:3.0.1")

    implementation(kotlin("stdlib", kotlinVersion))
    implementation(kotlin("stdlib-jdk8", kotlinVersion))

    implementation("org.reflections:reflections:0.10.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
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
