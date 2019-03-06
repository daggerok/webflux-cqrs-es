import Global.projectGradleVersion
import Global.projectGroup
import Global.projectJunitJupiterVersion
import Global.projectKotlinVersion
import Global.projectLombokVersion
import Global.projectVersion
import io.franzbecker.gradle.lombok.LombokPluginExtension

plugins {
  idea
  java
  base
  id("io.franzbecker.gradle-lombok") version "2.1" apply false
  id("org.springframework.boot") version "2.1.3.RELEASE" apply false
  id("io.spring.dependency-management") version "1.0.7.RELEASE" apply false
  id("org.jetbrains.kotlin.plugin.spring") version "1.3.21" apply false
  id("org.jetbrains.kotlin.jvm") version "1.3.21" apply false
}

extra["kotlin.version"] = projectKotlinVersion
extra["junit-jupiter.version"] = projectJunitJupiterVersion

allprojects {
  group = projectGroup
  version = projectVersion

  defaultTasks("build")

  apply(plugin = "io.franzbecker.gradle-lombok")

  configure<LombokPluginExtension> {
    version = projectLombokVersion
  }

  repositories {
    mavenCentral()
  }
}

tasks.withType<Wrapper> {
  gradleVersion = projectGradleVersion
  distributionType = Wrapper.DistributionType.BIN
}

/* // just use apply false in plugins block
tasks.withType<BootJar> {
  enabled = false
}
*/
