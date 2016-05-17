package org.nobkz.study.scalafx.sample.helloworld

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.AnchorPane


object HelloWorld extends JFXApp {
  stage = new PrimaryStage {
    title = "Hello World"
    scene = new Scene(100,40) {
      root = new AnchorPane {
        children = Seq(new Label("Hello, World"))
      }
    }
  }
}
