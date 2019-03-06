import Global.projectAssertjVersion
import Global.projectJavaVersion
import Global.projectJunit4Version
import Global.projectJunitJupiterVersion
import Global.projectKotlinParams
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  java
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.plugin.spring")
  id("io.spring.dependency-management")
  id("org.springframework.boot")
}

java {
  sourceCompatibility = projectJavaVersion
  targetCompatibility = projectJavaVersion
}

the<SourceSetContainer>()["main"].java.srcDir("src/main/kotlin")
the<SourceSetContainer>()["test"].java.srcDir("src/test/kotlin")

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    freeCompilerArgs = projectKotlinParams
    jvmTarget = "$projectJavaVersion"
  }
}

dependencies {
  implementation("io.vavr:vavr:0.9.2")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.8")

  implementation("org.springframework.boot:spring-boot-starter-json")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  runtimeOnly("org.springframework.boot:spring-boot-devtools")
  runtimeOnly("com.h2database:h2")
  testImplementation("com.h2database:h2")

  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.projectreactor:reactor-test")
}

dependencies {
  testImplementation("org.assertj:assertj-core:$projectAssertjVersion")
  testImplementation("junit:junit:$projectJunit4Version")
  testAnnotationProcessor("org.projectlombok:lombok")
  testImplementation(platform("org.junit:junit-bom:$projectJunitJupiterVersion"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
  testRuntime("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    showExceptions = true
    showStandardStreams = true
    events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
  }
}

// ./gradlew h2
// ./gradlew h2 -P pg
// ./gradlew h2 -P trace
// ./gradlew h2 -P browser
// ./gradlew h2 -P browser -P trace -P pg
tasks.register<JavaExec>("h2") {
  val h2Jar = sourceSets.main.get().runtimeClasspath
      .files.filter { it.name.contains("h2") }
      .firstOrNull()
  println("running $h2Jar")
  if (h2Jar == null) {
    println("h2 not found in runtimeClasspath...")
    return@register
  }

  val addons = mutableListOf<String>()
  // ./gradlew -P browser
  if (project.findProperty("browser") != null) {
    addons.add("-browser")
  }
  // ./gradlew -P pg
  if (project.findProperty("pg") != null) {
    addons.addAll(listOf(
        "-pg", /*"-pgDaemon",*/ "-pgAllowOthers", "pgPort", "5432"
    ))
  }
  // ./gradlew -P trace
  if (project.findProperty("trace") != null) {
    addons.add("-trace")
  }

  // run java -jar h2.jar ...
  main = "-jar"
  args = listOf(
      h2Jar.absolutePath,
      "-tcp",
      "-tcpAllowOthers",
      "-web",
      /*"-webDaemon",*/ // not worked for some reasons as daemon...
                        // we just cannot use JavaExec gradle task for it
      "-webAllowOthers"
  ).plus(addons)
}
