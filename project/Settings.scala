import Release._
import sbt.Keys._
import sbt.ScriptedPlugin.autoImport.{scriptedBufferLog, scriptedLaunchOpts}
import sbt._
import dotty.tools.sbtplugin.DottyPlugin.autoImport.DottyCompatModuleID
object Settings {
  lazy val commonSettings: Seq[Setting[_]] = Seq(
    crossScalaVersions := Seq(
      Dependencies.versions.dotty,
      Dependencies.versions.scala212,
      Dependencies.versions.scala213
    ),
    scalaVersion := crossScalaVersions.value.head,
    Test / parallelExecution := false // For logging tests
  )

  lazy val coreSettings: Seq[Setting[_]] = Seq(
    libraryDependencies ++= Seq(
      (Dependencies.test.scalatest).withDottyCompat(scalaVersion.value),
      (Dependencies.test.mockitoScala).withDottyCompat(scalaVersion.value),
      (Dependencies.pureconfig).withDottyCompat(scalaVersion.value),
      (Dependencies.scalameta).withDottyCompat(scalaVersion.value),
      (Dependencies.betterFiles).withDottyCompat(scalaVersion.value),
      (Dependencies.grizzledSlf4j).withDottyCompat(scalaVersion.value),
      (Dependencies.circeCore).withDottyCompat(scalaVersion.value),
      (Dependencies.sttp).withDottyCompat(scalaVersion.value),
      (Dependencies.mutationTestingMetrics).withDottyCompat(scalaVersion.value),
      Dependencies.mutationTestingElements,
      Dependencies.log4jApi,
      Dependencies.log4jCore,
      Dependencies.log4jslf4jImpl % Test // Logging tests need a slf4j implementation
    )
  )

  lazy val commandRunnerSettings: Seq[Setting[_]] = Seq(
    libraryDependencies ++= Seq(
      Dependencies.log4jslf4jImpl,
      (Dependencies.test.scalatest).withDottyCompat(scalaVersion.value)
    )
  )

  lazy val sbtPluginSettings: Seq[Setting[_]] = Seq(
    // sbt plugin has to be 2.12
    crossScalaVersions := Seq(Dependencies.versions.scala212),
    scalaVersion := Dependencies.versions.scala212,
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++
        Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    },
    scriptedBufferLog := false
  )

  lazy val buildLevelSettings: Seq[Setting[_]] = inThisBuild(
    releaseCommands ++
      buildInfo
  )

  lazy val buildInfo: Seq[Def.Setting[_]] = Seq(
    description := "Stryker4s, the mutation testing framework for Scala.",
    organization := "io.stryker-mutator",
    organizationHomepage := Some(url("https://stryker-mutator.io/")),
    homepage := Some(url("https://stryker-mutator.io/")),
    licenses := Seq("Apache-2.0" -> url("https://github.com/stryker-mutator/stryker4s/blob/master/LICENSE")),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/stryker-mutator/stryker4s"),
        "scm:git:https://github.com/stryker-mutator/stryker4s.git",
        "scm:git:git@github.com:stryker-mutator/stryker4s.git"
      )
    ),
    developers := List(
      Developer("legopiraat", "Legopiraat", "", url("https://github.com/legopiraat")),
      Developer("hugo-vrijswijk", "Hugo", "", url("https://github.com/hugo-vrijswijk"))
    )
  )
}
