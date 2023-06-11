plugins {
    id("java")
    checkstyle
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "org.example"
    version = "1.0-SNAPSHOT"
}


