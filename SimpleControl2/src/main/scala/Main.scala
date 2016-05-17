package simple2.control

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.AnchorPane
import scalafx.scene.control.Button
import scalafx.scene.control.Tooltip
import scalafx.scene.control.Label
import scalafx.scene.control.ToggleButton
import scalafx.scene.control.ToggleGroup
import scalafx.scene.control.CheckBox
import scalafx.scene.control.Hyperlink
import scalafx.scene.control.TextField
import scalafx.scene.text.Font
import scalafx.scene.shape.Rectangle
import scalafx.event.ActionEvent
import scalafx.scene.layout.HBox
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.MenuBar
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuItem
import scalafx.application.Platform
import scalafx.scene.input.KeyCombination
import java.io.IOException
import scalafxml.core.{NoDependencyResolver, FXMLView}



object Main extends JFXApp {

  val resource = getClass.getResource("/main.fxml")
  if( resource == null ) {
    throw new IOException("Cannot load resource: main.fxml")
  }

  val root = FXMLView(resource, NoDependencyResolver)

  stage = new PrimaryStage() {
    title = "simple controller 2"
    scene = new Scene(root)
  }
}
