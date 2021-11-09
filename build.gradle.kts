import org.gradle.jvm.tasks.Jar
import java.io.ByteArrayOutputStream
import java.util.Properties

val localPropertiesFile = project.rootProject.file("local.properties")
val localProperties = Properties()
if (localPropertiesFile.canRead())
    localProperties.load(localPropertiesFile.inputStream())

plugins {
    kotlin("jvm") version "1.5.31"
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

dependencies {
    api(kotlin("stdlib-jdk8"))

    // Plugin platform
    implementation("org.pf4j:pf4j:3.6.0")

    // Eidu
    implementation("com.eidu:domain:1.0.255")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlin.RequiresOptIn"
            )
        }
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
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
            artifact(sourcesJar)
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
