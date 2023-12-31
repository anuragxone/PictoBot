plugins {
    id("java")
}

group = "project.anurag.PictoBot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.github.auties00:whatsappweb4j:3.5.0")
}

tasks.test {
    useJUnitPlatform()
}