package css.sample

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Scene}
import scalafx.scene.layout.{VBox}
import scalafx.geometry.Insets
import scalafx.scene.control.
  {Label, TextField, Button}

object Main extends JFXApp{
  stage = new PrimaryStage {
    scene = new Scene{
      stylesheets = List("/style.css")
      root = new VBox {
        padding = Insets(10)
        margin  = Insets(10)
        children = List(
          new Button("push button"){
            id = "btn"
          },
          new TextField(){
            id = "textfield"
          },
          new Label("css sample"){
            id = "label"
          }
        )
      }
    }
  }
}
