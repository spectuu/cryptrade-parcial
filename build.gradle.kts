plugins {
    id("java")
}

group = "parcial"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.13.1")
    implementation("org.apache.logging.log4j:log4j-core:2.25.1")
}

tasks.test {
    useJUnitPlatform()
}