import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    java
}

group = "dev.shuuyu"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.kotlindiscord.com/repository/maven-public/")
}

val shadowMe: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    implementation("dev.kord:kord-core:0.8.x-SNAPSHOT")
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.5.3-SNAPSHOT")
    implementation("org.litote.kmongo:kmongo:4.5.1")
    shadowMe("ch.qos.logback:logback-classic:1.2.11")
    shadowMe("org.codehaus.groovy:groovy:3.0.10")
    shadowMe(platform("io.ktor:ktor-bom:2.0.1"))
    shadowMe("io.ktor:ktor-serialization-kotlinx-json-jvm")
    shadowMe("io.ktor:ktor-client-core-jvm")
    shadowMe("io.ktor:ktor-client-cio-jvm")
    shadowMe("io.ktor:ktor-client-content-negotiation-jvm")
    implementation("io.github.qbosst:kordex-hybrid-commands:1.0.3-SNAPSHOT")
}

tasks {
    "shadowJar"(ShadowJar::class) {
        configurations = listOf(shadowMe)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    "compileKotlin"(KotlinCompile::class) {
        kotlinOptions {
            jvmTarget = "17"
            kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
            kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.contracts.ExperimentalContracts"
            kotlinOptions.freeCompilerArgs += "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
            kotlinOptions.freeCompilerArgs += "-opt-in=kotlinx.serialization.InternalSerializationApi"
        }
    }
    "compileJava"(JavaCompile::class) {
        options.encoding = "UTF-8"
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
    }
}