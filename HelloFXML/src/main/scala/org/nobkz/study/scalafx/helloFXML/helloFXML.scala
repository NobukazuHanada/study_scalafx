package org.nobkz.study.scalafx.helloFXML

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}

object HelloFXML extends JFXApp {
  var resource = getClass.getResource("Hello.fxml")
  val root: jfxs.Parent = jfxf.FXMLLoader.load(resource)
  stage = new PrimaryStage {
    scene = new Scene(root)
  }
}
