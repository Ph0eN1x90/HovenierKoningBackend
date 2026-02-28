import com.github.spotbugs.snom.Confidence
import com.github.spotbugs.snom.Effort
import com.github.spotbugs.snom.SpotBugsTask

plugins {
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("checkstyle")
	id("com.github.spotbugs") version "6.0.26"
}

group = "com.hovenierkoning"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework:spring-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	
	// MapStruct
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

checkstyle {
	toolVersion = "10.12.3"
	configFile = file("config/checkstyle/checkstyle.xml")
}

spotbugs {
	toolVersion.set("4.9.3")
	ignoreFailures.set(true)
	effort.set(Effort.DEFAULT)
	reportLevel.set(Confidence.MEDIUM)
}

tasks.checkstyleMain {
	ignoreFailures = false
}

tasks.withType<SpotBugsTask>().configureEach {
	reports.create("html") {
		required.set(true)
	}
	reports.create("xml") {
		required.set(false)
	}
}

tasks.build {
	dependsOn("checkstyleMain", "spotbugsMain")
}