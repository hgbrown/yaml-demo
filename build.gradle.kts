import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.yaml.snakeyaml.Yaml
import java.util.*

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.yaml:snakeyaml:1.29")
    }
}

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

val yml: Map<String, Any> = Yaml().load(
    File("${project.projectDir}/src/main/resources/application.yml").inputStream()
)

val props: Properties = Properties().apply {
    load(
        File("${project.projectDir}/src/main/resources/version.properties").inputStream()
    )
}

group = "dev.hbrown"
version = props.getProperty("info.app.version")
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks["bootJar"].doLast {
    val appMap = yml["app"] as Map<String, Any>
    val usersMap = appMap["users"] as Map<String, String>
    val path = usersMap["path"]

    println("==========================")
    println("=== Version: ${props.getProperty("info.app.version")}")
    println("=== Path: $path")
    println("==========================")
}

