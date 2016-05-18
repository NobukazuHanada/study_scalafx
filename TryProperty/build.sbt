lazy val root = (project in file(".")).
  settings(
    name := "try property",
    version := "1.0",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "1.0.1",
      "org.scalafx" %% "scalafx" % "8.0.92-R10",
      "org.scalafx" %% "scalafxml-core-sfx8" % "0.2.2"
    )
  )

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

fork in run := true 
