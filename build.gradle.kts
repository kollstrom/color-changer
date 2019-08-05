import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.41"
}

group = "no.kollstrom.lemus"
version = "0.0.1"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.1")
  testImplementation("org.assertj:assertj-core:3.10.0")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

tasks {
  test {
    useJUnitPlatform {
      includeEngines("junit-jupiter")
    }
  }
}
