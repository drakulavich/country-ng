import org.gradle.kotlin.dsl.annotationProcessor

plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.protobuf") version "0.9.4"
}

group = "guru.qa"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.grpc:spring-grpc-dependencies:0.9.0-SNAPSHOT")
    }
}

dependencies {
    implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")
    implementation("io.grpc:grpc-services")
    implementation("com.google.protobuf:protobuf-java-util:${dependencyManagement.importedProperties["protobuf-java.version"]}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("com.tailrocks.graphql:graphql-datetime-spring-boot-starter:6.0.0")
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    runtimeOnly("org.flywaydb:flyway-core:10.17.1")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.17.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    annotationProcessor("org.projectlombok:lombok:1.18.34")
    compileOnly("org.projectlombok:lombok:1.18.34")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${dependencyManagement.importedProperties["protobuf-java.version"]}"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${dependencyManagement.importedProperties["grpc.version"]}"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("grpc") {
                    option("jakarta_omit")
                    option("@generated=omit")
                }
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
