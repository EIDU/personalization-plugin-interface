import java.io.ByteArrayOutputStream

plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
    id("com.github.jk1.dependency-license-report") version "2.8"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7

    withSourcesJar()
    withJavadocJar()
}

licenseReport {
    allowedLicensesFile = File("$projectDir/allowed-licenses.json")
}

tasks.named("checkLicense") {
    // The checkLicense task does not declare this input itself, so we do it here. This ensures
    // that a modification of the file causes the checkLicense task to be re-evaluated.
    inputs.file("$projectDir/allowed-licenses.json")
}

tasks.named("check") {
    dependsOn("checkLicense")
}

val mavenArtifactId: String = "personalization-plugin-interface"

signing {
    useInMemoryPgpKeys(
        System.getenv("SIGNING_KEY_ID"),
        System.getenv("SIGNING_KEY"),
        System.getenv("SIGNING_PASSWORD")
    )
    sign(publishing.publications)
}

publishing {
    repositories {
        maven {
            name = "MavenCentral"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("MAVEN_CENTRAL_USERNAME")
                password = System.getenv("MAVEN_CENTRAL_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.eidu"
            artifactId = mavenArtifactId
            version = version()
            from(components["java"])

            pom {
                name.value(mavenArtifactId)
                description.value("EIDU Personalization Plugin Interface")
                url.value("https://github.com/EIDU/personalization-plugin-interface")
                licenses {
                    license {
                        name.value("MIT License")
                        url.value("https://raw.githubusercontent.com/EIDU/personalization-plugin-interface/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.value("berlix")
                        name.value("Felix Engelhardt")
                        url.value("https://github.com/berlix/")
                    }
                }
                scm {
                    url.value("https://github.com/EIDU/personalization-plugin-interface")
                    connection.value("scm:git:git://github.com/EIDU/personalization-plugin-interface.git")
                    developerConnection.value("scm:git:ssh://git@github.com/EIDU/personalization-plugin-interface.git")
                }
            }
        }
    }
}

fun version(): String = run("git tag -l --sort -version:refname v-*.*.* | head -n 1").substring(2)

fun run(command: String): String {
    ByteArrayOutputStream().use { output ->
        exec {
            commandLine("sh", "-c", command)
            standardOutput = output
        }
        return output.toString().trim()
    }
}
