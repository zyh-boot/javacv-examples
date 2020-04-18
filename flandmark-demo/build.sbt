// Name of the project
name := "flandmark-demo"

// Project version
version := "1.5.3"

// Version of Scala used by the project
scalaVersion := "2.13.1"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-optimize", "-Xlint")

// Some dependencies like `javacpp` are packaged with maven-plugin packaging
classpathTypes += "maven-plugin"

// Platform classifier for native library dependencies
lazy val platform = org.bytedeco.javacpp.Loader.getPlatform

// @formatter:off
libraryDependencies ++= Seq(
  "org.bytedeco" % "javacpp"   % "1.5.3"       classifier platform,
  "org.bytedeco" % "javacv"    % "1.5.3",
  "org.bytedeco" % "flandmark" % "1.07-1.5.3"  classifier "",
  "org.bytedeco" % "flandmark" % "1.07-1.5.3"  classifier platform,
  "org.bytedeco" % "openblas"  % "0.3.9-1.5.3" classifier "",
  "org.bytedeco" % "openblas"  % "0.3.9-1.5.3" classifier platform,
  "org.bytedeco" % "opencv"    % "4.3.0-1.5.3" classifier "",
  "org.bytedeco" % "opencv"    % "4.3.0-1.5.3" classifier platform
)
// @formatter:on

// Used for testing local builds and snapshots of JavaCPP/JavaCV
//resolvers ++= Seq(
//  Resolver.sonatypeRepo("snapshots"),
//  // Use local maven repo for local javacv builds
//  "Local Maven Repository" at "file:///" + Path.userHome.absolutePath + "/.m2/repository"
//)

// set the main class for 'sbt run'
mainClass in(Compile, run) := Some("flandmark.Example1")

// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems
fork := true

// Set the prompt (for this build) to include the project id.
shellPrompt in ThisBuild := { state => "sbt:" + Project.extract(state).currentRef.project + "> " }
