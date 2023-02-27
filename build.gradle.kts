import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "hn.single.learn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    // Because RxAndroid releases are few and far between, it is recommended you also
    // explicitly depend on RxJava's latest version for bug fixes and new features.
    // see https://github.com/ReactiveX/RxJava/releases for latest 3.x.x version
    implementation ("io.reactivex.rxjava3:rxjava:3.0.2")
    implementation ("io.reactivex.rxjava3:rxkotlin:3.0.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}