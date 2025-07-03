plugins {
    java
    kotlin("jvm") version "1.9.10"
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "ch.bbcag.crambs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin standard library
    implementation(kotlin("stdlib"))

    // JavaFX dependencies (if needed for Kotlin)
    implementation("org.openjfx:javafx-controls:19")
    implementation("org.openjfx:javafx-media:19")
    implementation("org.openjfx:javafx-graphics:19")

    // Test dependencies
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test-junit5"))
}

// Configure Java compatibility
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Configure Kotlin compatibility
kotlin {
    jvmToolchain(17)
}

// Main class configuration
application {
    mainClass.set("ch.bbcag.crambs.CrambsApp")
    // Alternative for Kotlin main class (if you convert to Kotlin):
    // mainClass.set("ch.bbcag.crambs.CrambsAppKt")
}

javafx {
    modules("javafx.controls", "javafx.media", "javafx.graphics")
}

tasks.test {
    useJUnitPlatform()
}

// Configure source sets for mixed Java/Kotlin projects
sourceSets {
    main {
        kotlin {
            setSrcDirs(listOf("src/main/kotlin", "src/main/java"))
        }
        java {
            setSrcDirs(emptyList<String>())
        }
    }
    test {
        kotlin {
            setSrcDirs(listOf("src/test/kotlin", "src/test/java"))
        }
        java {
            setSrcDirs(emptyList<String>())
        }
    }
}