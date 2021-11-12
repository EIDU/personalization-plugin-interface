import java.io.ByteArrayOutputStream
import java.util.Properties

val localPropertiesFile = project.rootProject.file("local.properties")
val localProperties = Properties()
if (localPropertiesFile.canRead())
    localProperties.load(localPropertiesFile.inputStream())

plugins {
    kotlin("jvm") version "1.5.21"
    id("java-library")
    id("maven-publish")
}

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/EIDU/personalization-plugin-interface")
        credentials {
            username = System.getenv("READPACKAGES_GITHUB_USER")
                ?: System.getenv("GITHUB_READPACKAGES_USER")
                ?: localProperties.getProperty("githubReadPackagesUser")
            password = System.getenv("READPACKAGES_GITHUB_TOKEN")
                ?: System.getenv("GITHUB_READPACKAGES_TOKEN")
                ?: localProperties.getProperty("githubReadPackagesToken")
        }
    }
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

ProcessBuilder("git config --local core.hooksPath git-hooks".split(" ")).start()

fun run(command: String): String {
    ByteArrayOutputStream().use { output ->
        exec {
            commandLine("sh", "-c", command)
            standardOutput = output
        }
        return output.toString().trim()
    }
}

fun version(): String = System.getenv("GITHUB_RUN_NUMBER")?.let {
    "1.0.$it" + (run("git rev-parse --abbrev-ref HEAD").takeIf { it != "main" }?.let { "-$it" } ?: "")
} ?: "snapshot"
