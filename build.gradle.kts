plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.diffplug.spotless") version "6.25.0" apply false
	id("org.sonarqube") version "4.4.1.3373"
}

group = "com.myfi"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

subprojects {
	repositories {
		mavenCentral()
	}
	apply(plugin = "com.diffplug.spotless")

	configure<com.diffplug.gradle.spotless.SpotlessExtension> {
		java {
			target("src/*/java/**/*.java")
			googleJavaFormat()
			removeUnusedImports()
			importOrder(
				"java",
				"javax",
				"org.springframework",
				"org",
				"com",
				""
			)
			endWithNewline()
			trimTrailingWhitespace()
		}
	}

	tasks.withType<com.diffplug.gradle.spotless.SpotlessCheck>().configureEach {
		doFirst {
			println("\nChecking ${project.name}...")
			println("Import Order Checks...")
			println("Format Style Checks...")
			println("Line Ending Checks...")
		}

		doLast {
			if (state.failure == null) {
				println("${project.name}: No formatting issues found")
			}
		}
	}

	tasks.withType<com.diffplug.gradle.spotless.SpotlessApply>().configureEach {
		doFirst {
			println("\nFormatting ${project.name}...")
			println("Fixing imports...")
			println("Applying format styles...")
			println("Fixing line endings...")
		}

		doLast {
			println("${project.name}: Formatting applied")
		}
	}
}

repositories {
	mavenCentral()
}