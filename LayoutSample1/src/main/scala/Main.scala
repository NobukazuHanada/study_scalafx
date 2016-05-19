package sample.layout

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.stage.Stage
import scalafx.scene.{Scene, Group}
import scalafx.scene.control.{Label, Button}
import scalafx.scene.layout.{FlowPane, HBox, VBox, Priority,BorderPane}
import scalafx.geometry.GeometryIncludes._
import scalafx.geometry.{Insets, Pos}

object Main extends JFXApp {
  stage = new PrimaryStage {
    scene = new Scene{
      val group = new Group{
        children =
          for(i <- 0 until 10)
          yield new Button(s"Button$i"){
            layoutX = i * 30.0
            layoutY = i * 15.0
          }
      }

      val flowPane = new FlowPane(8,4){
        prefWidth = 400
        prefHeight = 300
        padding = Insets(12,24,12,24)
        alignment = Pos.Center

        children =
          for(i <- 0 until 10) yield
            if( i == 0 ){
              new Button("Button0"){
                margin = Insets(20,20,20,20)
              }
            }else{
              new Button(s"Button$i")
            }

        
      }

      val hbox = new HBox(8){
        prefWidth = 400
        prefHeight = 300
        padding = Insets(12)
        

        children =
          for(i <- 0 until 4) yield
            if( i == 0)
              new Button("Button0"){
                maxWidth = Double.MaxValue
                maxHeight = Double.MaxValue
                HBox.setHgrow(this, Priority.Always)
              }
            else 
              new Button(s"Button$i"){
                maxWidth = Double.MaxValue
                maxHeight = Double.MaxValue
              }
      }

      val borderPane = new BorderPane {
        prefWidth = 400
        prefHeight = 300

        trait maxBtn extends Button{
          maxWidth = Double.MaxValue
          maxHeight = Double.MaxValue
        }
        top = new Button("TOP") with maxBtn
        bottom = new Button("BOTTOM") with maxBtn
        right = new Button("RIGHT") with maxBtn
        left = new Button("LEFT") with maxBtn
        center = new Button("CENTER") with maxBtn
      }
      

      root = borderPane
    }
  }
}
