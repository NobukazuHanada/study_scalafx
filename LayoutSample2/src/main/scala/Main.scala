package sample.layouts

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Scene, Group}
import scalafx.scene.layout.
  {StackPane, AnchorPane, Region, HBox, VBox, GridPane, Priority}
import scalafx.geometry.{Pos, Insets}
import scalafx.scene.control.{Button}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.shape.Circle
import scalafx.scene.paint.Color
import scalafx.event.ActionEvent


object Main extends JFXApp {
  stage = new PrimaryStage{
    scene = new Scene{

      trait UnitRegion extends Region{
        prefWidth = 400
        prefHeight = 300
      }

      trait maxRegion extends Region{
        maxWidth = Double.MaxValue
        maxHeight = Double.MaxValue
      }


      val stackPane1 = new StackPane with UnitRegion{
        children =
          for(i <- 10 until 0 by -1) yield
            new Circle {
              radius = 50 + i * 10
              fill = Color.color(1.0 - i/10.0, 0, i/10.0)
            }
      }
      val stackPane2 = new StackPane with UnitRegion{
        alignment = Pos.TopLeft

        children =
          for(i <- 10 until 0 by -1) yield
            new Circle {
              radius = 50 + i * 10
              fill = Color.color(1.0 - i/10.0, 0, i/10.0)
            }
      }

      val imageViews = new AnchorPane with UnitRegion{
        val imageStackPane = new StackPane {
          prefWidth = 400
          prefHeight = 250

          

          children =
            for(i <- 1 to 5) yield 
              new ImageView(new Image(
                              getClass().getResource(s"/image${i}.jpg")
                                .toURI.toURL.toString
                            )){

                fitWidth = 400
                fitWidth = 250
                preserveRatio = true
              }
              
        }

        val button = new Button("next") {
          layoutX = 100
          layoutY = 250
          onAction = (e:ActionEvent) => {
            val images = imageStackPane.children
            val lastImage = images.remove(images.size() - 1)
            imageStackPane.children.add(0, lastImage)
          }
        }
        

        children = List(imageStackPane, button)
      }

      var anchorPane1 = new AnchorPane with UnitRegion{
        val btn0 = new Button("Button: Top & Left anchored"){
          AnchorPane.setLeftAnchor(this, 20.0)
          AnchorPane.setTopAnchor(this, 20.0)
        }

        val btn1 = new Button("Button: Bottom & Right anchored"){
          AnchorPane.setRightAnchor(this, 20.0)
          AnchorPane.setBottomAnchor(this, 20.0)
        }

        val btn2 = new Button("Button: Top, Left & Right anchred"){
          AnchorPane.setTopAnchor(this, 60.0)
          AnchorPane.setLeftAnchor(this, 60.0)
        }

        val btn3 = new Button("Button: Top, Bottom, Left & Right anchored"){
          AnchorPane.setTopAnchor(this, 120.0)
          AnchorPane.setRightAnchor(this, 60.0)
          AnchorPane.setLeftAnchor(this, 60.0)
          AnchorPane.setBottomAnchor(this,120.0)
        }


        children = List(btn0,btn1,btn2,btn3)
      }

      val gridPane = new GridPane with UnitRegion{
        hgap = 10
        vgap = 10

        children = 
          for(i <- 0 until 5;
              j <- 0 until 4) yield
            new Button(s"Button${j}-${i}") {
              GridPane.setConstraints(this,j,i)
            }
      }

      root = new AnchorPane {
        
        children = List(
          new GridPane{
            hgap = 10
            vgap = 10
            hgrow = Priority.Always

            this.add(stackPane1,0,0)
            this.add(stackPane2,1,0)
            this.add(imageViews,2,0)
            this.add(anchorPane1,0,1)
            this.add(gridPane,1,1)

            AnchorPane.setAnchors(this, 10, 10, 10, 10)
          }
        )
      }
    }
  }
}
