import java.net.URI
import java.net.URL
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version "1.3.72"
    `java-gradle-plugin`
    id("org.jetbrains.dokka") version "0.9.18"
}

gradlePlugin {
    plugins {
        create("subPlugin") {
            id = "myaa.subkt"
            implementationClass = "myaa.subkt.tasks.SubPlugin"
        }
    }
}

group = "myaa"
version = "0.1.12"

repositories {
    mavenCentral()
    jcenter()
}

sourceSets {
    main {
        java {
            srcDirs(listOf(
                    "ttorrent/bencoding/src/main/java",
                    "ttorrent/common/src/main/java"
            ))
        }
    }

    register("samples") {
        compileClasspath += sourceSets["main"].compileClasspath
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8", "1.3.72"))
    implementation(kotlin("reflect", "1.3.72"))
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.3.7")
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation("org.apache.velocity", "velocity-engine-core", "2.2")
    implementation("org.apache.pdfbox", "fontbox", "2.0.21")

    implementation("com.google.code.gson", "gson", "2.8.6")
    implementation("io.ktor", "ktor-client-cio", "1.3.2")
    implementation("io.ktor", "ktor-client-gson", "1.3.2")
    implementation("io.ktor", "ktor-client-auth-jvm", "1.3.2")
    implementation("commons-net", "commons-net", "3.6")
    implementation("commons-io", "commons-io", "2.7")
    implementation("com.jcraft", "jsch", "0.1.55")
    // ttorrent dependencies
    implementation("org.slf4j", "slf4j-log4j12", "1.6.4")
    implementation("org.jetbrains", "annotations", "16.0.2")
    implementation("commons-codec", "commons-codec", "1.14")

    "samplesImplementation"(sourceSets["main"].output)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    val dokka by getting(DokkaTask::class) {
        outputFormat = "gfm"
        outputDirectory = "docs"

        // configuration {
        noStdlibLink = false
        noJdkLink = false
        jdkVersion = 9
        //platform = "JVM"

        includes = listOf("src/docs/markdown/packages.md")
        samples = listOf("src/samples/kotlin/tasks.kt")

        externalDocumentationLink {
            url = URL("https://docs.gradle.org/current/javadoc/")
        }

        externalDocumentationLink {
            url = URL("https://velocity.apache.org/engine/2.2/apidocs/")
        }

        packageOptions/*perPackageOption*/ {
            prefix = "com.turn.ttorrent"
            suppress = true
        }

        linkMapping {
            // replace \ with / since only unix paths are allowed
            dir = project.file("src/main/kotlin").absolutePath.replace('\\', '/')
            url = "https://github.com/Myaamori/SubKt/blob/$version/src/main/kotlin"
            suffix = "#L"
        }
        // }
    }
}