package simple.control

import scalafx.Includes._
import scalafx.application.JFXApp
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


object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    scene = new Scene {
      root = new AnchorPane { anchorPane : AnchorPane =>
        prefWidth = 500
        prefHeight = 500
        children = List(
          new Button {
            mnemonicParsing = true
            layoutX = 100
            layoutY = 10
            text.value = "_push button"
            tooltip = new Tooltip("button")
            prefWidth = 100.0
            prefHeight = 40.0
            onAction = (e : ActionEvent) => {
              text = "_Push"
            }
          },
          new Label {
            text = "label"
            layoutX = 100
            layoutY = 100
            font = Font.font(null, 24)
            graphic = new Rectangle {
              x = 0
              y = 0
              width = 10
              height = 10 
            }
          },
          new ToggleButton{ toggle : ToggleButton => 
            text = "label"
            prefWidth = 100
            prefHeight = 300 
            onAction = (e:ActionEvent) => {
              println("sample " + toggle.isSelected())
            }
          },
          new HBox{
            val tog = new ToggleGroup 
            layoutX = 200
            layoutY = 400
            spacing = 10
            children = List(
              new ToggleButton {
                text = "blue"
                toggleGroup = tog
              },
              new ToggleButton {
                text = "red"
                toggleGroup = tog
              },
              new ToggleButton{
                text = "green"
                toggleGroup = tog
              }
            )
          },
          new CheckBox { checkbox : CheckBox =>
            indeterminate = true
            selected = false
            layoutX = 300
            layoutY = 100
            allowIndeterminate = true
            onAction = (e: ActionEvent) => {
              println(" mikakutei " + checkbox.isIndeterminate())
              println(" sentaku" + checkbox.isSelected())
            }
          },
          new Hyperlink {
            layoutX = 300
            layoutY = 40
            text = "hyperlink"
            underline = true
          },
          new TextField {
            layoutX = 400
            layoutY = 400
           onAction = (e: ActionEvent) => {
             println( this.text.value )
           }
          }
        )
      }
    }
  }
}
