package nobkz

import scalafx.Includes._
import scalafx.application.JFXApp
import JFXApp.PrimaryStage
import scalafx.scene.control.Button
import scalafx.scene.control.Label
import scalafx.scene.Scene
import scalafx.scene.layout.AnchorPane
import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color
import scalafx.scene.control.Tooltip
import scalafx.scene.shape.Rectangle
import scalafx.scene.input.MouseEvent

object Main extends JFXApp{
  stage = new PrimaryStage {
    title ="button pushing"
    scene = new Scene {
      root = new AnchorPane{
        prefWidth = 500
        prefHeight = 500
        children = Seq(
          new Button {
            layoutX = 100
            layoutY = 100
            text = "push!"
            style = "-fx-font-size: 24pt"
          },

          new Button {
            layoutX = 100
            layoutY = 200
            prefWidth = 100
            prefHeight = 100
            text = "push!"
            effect = new DropShadow(4,2,2,Color.GRAY)
            tooltip = new Tooltip("button")

            onMouseClicked = (e: MouseEvent) => println("clicked!")
            
          },

          new Label{
            layoutX = 100
            layoutY = 300
            text = "label!!"
            graphic = new Rectangle{
              x = 0
              y = 0
              width = 10
              height = 10
            }
          }
       )
      }
    }
  }
}
