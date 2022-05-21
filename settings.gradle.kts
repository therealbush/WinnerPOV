pluginManagement {
    repositories {
        gradlePluginPortal()
        maven(url = "https://maven.fabricmc.net") {
            name = "Fabric"
        }
    }
}

if (JavaVersion.current().ordinal + 1 < 17)
    throw IllegalStateException("You must have Java 17+!")
