import java.io.ByteArrayOutputStream

plugins {
    kotlin("jvm") version "1.5.21"
    id("java-library")
    id("maven-publish")
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    withSourcesJar()
    withJavadocJar()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/EIDU/personalization-plugin-interface")
            credentials {
                username = System.getenv("GITHUB_USER")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.eidu"
            artifactId = "personalization-plugin-interface"
            version = version()

            from(components["java"])
        }
    }
}

fun version(): String = System.getenv("GITHUB_RUN_NUMBER")?.let { runNumber ->
    "1.0.$runNumber" + (run("git rev-parse --abbrev-ref HEAD").takeIf { it != "main" }?.let { "-$it" } ?: "")
} ?: "snapshot"

fun run(command: String): String {
    ByteArrayOutputStream().use { output ->
        exec {
            commandLine("sh", "-c", command)
            standardOutput = output
        }
        return output.toString().trim()
    }
}
