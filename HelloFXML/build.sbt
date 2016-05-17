lazy val root = (project in file(".")).
  settings(
    name := "helloFXML",
    version := "1.0",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "1.0.1",
      "org.scalafx" %% "scalafx" % "8.0.92-R10"
    )
  )
