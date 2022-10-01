ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val lwjglVersion = "3.3.1"



libraryDependencies ++= Seq(
    "org.lwjgl" % "lwjgl" % lwjglVersion,
    "org.lwjgl" % "lwjgl-glfw" % lwjglVersion,
    "org.lwjgl" % "lwjgl-opengl" % lwjglVersion,
)


lazy val root = (project in file("."))
  .settings(
    name := "ScalaRenderer"
  )
