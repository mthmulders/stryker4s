addSbtPlugin("com.geirsson" % "sbt-ci-release" % "1.5.2")
resolvers += Resolver.sonatypeRepo("snapshots")
addSbtPlugin("io.stryker-mutator" % "sbt-stryker4s" % "0.7.2+33-50230f75-SNAPSHOT")
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.11")
