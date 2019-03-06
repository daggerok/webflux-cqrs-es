import org.gradle.api.JavaVersion

object Global {
  const val projectVersion = "1.0.0-SNAPSHOT"
  const val projectGroup = "com.github.daggerok"
  const val projectJunitJupiterVersion = "5.4.0"
  val projectJavaVersion = JavaVersion.VERSION_1_8
  val projectKotlinParams = mutableListOf("-Xjsr305=strict")
  const val projectKotlinVersion = "1.3.21"
  const val projectLombokVersion = "1.18.6"
  const val projectGradleVersion = "5.2.1"
  const val projectJunit4Version = "4.12"
  const val projectAssertjVersion = "3.11.1"
}
