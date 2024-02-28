import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent


plugins {
    java
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.1.4"
    id("io.freefair.lombok") version "8.6"
}

group = "com.inngest"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":inngest-spring-boot-adapter"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.7.18") {
            bomProperty("kotlin.version", "1.9.10")
        }
    }
}


tasks.create("stage") {
    dependsOn("build")
}
