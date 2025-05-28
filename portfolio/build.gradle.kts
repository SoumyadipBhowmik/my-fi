plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("java")
    id("com.google.protobuf") version "0.9.4"
    application
}

repositories {
    mavenCentral()
}



val grpcSpringVersion = "3.1.0.RELEASE"
val grpcJavaVersion = "1.58.0"
val javaxAnnotationVersion = "1.3.2"
val guavaVersion = "32.1.3-jre"
val jakartaValidation = "3.0.2"
val lombokVersion = "1.18.30"
val mapstructVersion = "1.6.3"
val protobufVersion = "3.25.4"
val postgresVersion = "42.7.2"

configurations.all {
    exclude(group = "ch.qos.logback")
    exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
}

dependencies {
    // ========== Spring Framework ==========
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // ========== Database ==========
    runtimeOnly("org.postgresql:postgresql:$postgresVersion")

    // ========== gRPC Framework ==========
    implementation("net.devh:grpc-server-spring-boot-starter:$grpcSpringVersion")
    implementation("net.devh:grpc-client-spring-boot-starter:$grpcSpringVersion")
    implementation("io.grpc:grpc-services:$grpcJavaVersion")
    implementation("io.grpc:grpc-stub:$grpcJavaVersion")
    implementation("io.grpc:grpc-protobuf:$grpcJavaVersion")

    // ========== Protocol Buffers ==========
    implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")
    compileOnly("javax.annotation:javax.annotation-api:$javaxAnnotationVersion")

    // ========== Validation ==========
    implementation("jakarta.validation:jakarta.validation-api:$jakartaValidation")

    // ========== Utilities ==========
    implementation("com.google.guava:guava:$guavaVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations")

    // ========== Lombok ==========
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    // ========== MapStruct ==========
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    // ========== Testing ==========
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":common"))
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.23.4"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.58.0"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
            }
        }
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnit("4.13.2")
        }
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/java")
    }
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("com.myfi.portfolio.PortfolioServiceApplication")
}