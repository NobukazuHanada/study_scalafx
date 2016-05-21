package nobkz.sample.chart

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.chart.{PieChart, LineChart, NumberAxis, XYChart}
import scalafx.scene.layout.{GridPane}

object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {

    val pieChart = new PieChart{
      title = "PieChart Sample"
      data = List(
        PieChart.Data("A", 30),
        PieChart.Data("B", 10),
        PieChart.Data("C", 20),
        PieChart.Data("D", 50)
      )
    }

    val xAxis = new NumberAxis("X", 0, 100, 10)
    val yAxis = new NumberAxis("Y", 0, 100, 10)

    val lineChart = new LineChart(xAxis, yAxis) {
      title = "math graphs"
      val series1 = new XYChart.Series[Number, Number]{
        name = "route * 4"
        data() ++= (1 to 100 by 4).map (x =>
          XYChart.Data[Number, Number](
            x,
            Math.sqrt(x) * 8
          ))
      }

      val series2 = new XYChart.Series[Number, Number]{
        name = "route(100 - x) * 4"
        data() ++= (1 to 100 by 4).map (x =>
          XYChart.Data[Number, Number](
            x,
            Math.sqrt(100 - x) * 4
          ))
      }

      data() += series1
      data() += series2
    }

    scene = new Scene{
      root = new GridPane() {
        hgap = 10.0
        vgap = 10.0
        this.add(pieChart, 0, 0)
        this.add(lineChart,0, 1)
      }
    }

  }
}
