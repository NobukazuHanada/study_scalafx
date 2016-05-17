package simple3.control

import scalafx.event.ActionEvent
import scalafx.application.Platform
import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.layout.BorderPane
import scalafx.Includes._
import scalafxml.core.macros.sfxml
import scalafx.stage.FileChooser
import FileChooser.ExtensionFilter


@sfxml
class ImageViewController (
  private var borderPane : BorderPane,
  private var imageView : ImageView
) {

  val fileChooser = new FileChooser {
    title = "Image Select"
    extensionFilters ++= List(
      new ExtensionFilter("Image File",List(
                            "*.png", "*.jpg", "*.gif"))
    )
  }


  def openFile(event : ActionEvent) {
    val file = fileChooser.showOpenDialog(Main.stage)
    if( file != null ){
      val image = new Image(file.toURI.toURL.toString)
      imageView.image = image
    }
  }

  def handleExit(event: ActionEvent)  {
    Platform.exit()
  }
} 
