package tried.property

import scalafx.beans.property.IntegerProperty
import scalafx.beans.property.DoubleProperty
import scalafx.beans.binding.Bindings
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.HBox
import scalafx.scene.layout.VBox
import scalafx.scene.control.Slider
import scalafx.scene.control.Label

object Main extends JFXApp {
  // check simple property
  val i = new IntegerProperty(this, "nobkz", 10) {
    onChange { (source, oldValue, newValue) =>
      println(
        s"Value of property '$name' is changing from $oldValue to $newValue with $source"
      )
    }
  }

  i() = 60
  i() = 74
  i() = 24


  stage = new PrimaryStage {
    title() = "Try Property"
    scene = new Scene {
      content = new VBox {
        val slider = new Slider(0,100,0) {
          showTickLabels.value = true
        }

        val label = new Label("0")
        slider.value.onChange { (_, oldValue, newValue) =>
          label.text.value = newValue.toString
        }


        children = List(slider, label)
      }
    }
  }
}
