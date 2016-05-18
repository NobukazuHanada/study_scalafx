package tried.property

import scalafx.Includes._
import scalafx.beans.binding.BindingIncludes._
import scalafx.beans.property.{
  IntegerProperty,
  DoubleProperty,
  ObjectProperty
}
import scalafx.beans.binding.Bindings
import scalafx.beans.binding.ObjectBinding
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color

import scalafx.scene.control.{
  Slider,
  Label,
  TextArea,
  ProgressBar
}
import scalafx.scene.shape.Rectangle
import scalafx.collections.ObservableBuffer

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

  val numbers = new ObservableBuffer[Int]() {
    onChange { (source, change) =>
      println(
        s"Value of source is $change in $source"
      )
    }
  }

  numbers.addAll(1,2,3)
  numbers.addAll(4,5)
  numbers(1) = 10

  val x = IntegerProperty(0)
  val y = IntegerProperty(0)
  y.onInvalidate { (o) =>
    println("Invalidate: $o") 
  }
  y.onChange { (_, oldValue, newValue) =>
    println("Onchange! : $newValue") 
  }

  y <== x
  println(s"first invalidate : ${y()}")

  x() = 10
  x() = 20
  println(s"check bind : ${y()}")



  stage = new PrimaryStage {
    title() = "Try Property"
    scene = new Scene {
      content = new VBox {
        prefWidth = 300
        val slider = new Slider(0,100,0) {
          showTickLabels.value = true
        }

        val label = new Label("0")
        slider.value.onChange { (_, oldValue, newValue) =>
          label.text.value = newValue.toString
        }

        val slider2 = new Slider(0,100,0) {
          showTickLabels = true
          showTickMarks = true
          majorTickUnit = 20
          minorTickCount = 1
        }

        val slider3 = new Slider(0,100,10) {
          showTickLabels = true
          showTickMarks = true
          majorTickUnit = 20
          minorTickCount = 1
        }

        var bar = new ProgressBar() 

        val value = DoubleProperty(0.0)

        value <== (slider2.value + slider3.value) / 200
        bar.progress <== value

        val textArea1 = new TextArea
        val textArea2 = new TextArea

        textArea2.text <==> textArea1.text

        val rect = new Rectangle {
          width = 200
          height = 100
        }

        val slider4 = new Slider(0,1.0,0.5)
        val slider5 = new Slider(0,1.0,0.5)
        val slider6 = new Slider(0,1.0,0.5)

        val colorBindings = Bindings.createObjectBinding[javafx.scene.paint.Color](
          () => {
            javafx.scene.paint.Color.color(slider4.value(),
                                           slider5.value(),
                                           slider6.value())
          },
          slider4.value,
          slider5.value,
          slider6.value
        )


        rect.fillProperty() <== colorBindings

        children = List(slider, label, slider2, slider3, bar
        ,textArea1, textArea2, slider4, slider5, slider6, rect)
      }
    }
  }
}
