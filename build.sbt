name := "regression"

organization := "com.mocyuto"

version := "0.0.5"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

libraryDependencies  ++= {
  val scalaTest = "2.2.6"
  Seq(
    // other dependencies here
    "org.scalanlp" %% "breeze" % "0.11.2",
    // native libraries are not included by default. add this if you want them (as of 0.7)
    // native libraries greatly improve performance, but increase jar sizes.
    // It also packages various blas implementations, which have licenses that may or may not
    // be compatible with the Apache License. No GPL code, as best I know.
    "org.scalanlp" %% "breeze-natives" % "0.11.2",
    "org.scalactic" %% "scalactic" % scalaTest,
    "org.scalatest" %% "scalatest" % scalaTest
  )
}

resolvers ++= Seq(
  // other resolvers here
  // if you want to use snapshot builds (currently 0.12-SNAPSHOT), use this.
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/GitHub/regression/repository")))
