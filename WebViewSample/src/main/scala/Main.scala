package webview.sample

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.web.WebView
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Button
import scalafx.geometry.Pos
import scalafx.event.ActionEvent
import netscape.javascript.JSObject

object Main extends JFXApp{
  val webView = new WebView{
    engine.loadContent("<html><script>function update(){ app.update(); }</script><body><h1>Hello <span id=name> World</span></body></html>")

  }
  val engine = webView.engine
  def update {
    engine.document.getElementById("name").setTextContent("ScalaFX")
  }


  stage = new JFXApp.PrimaryStage(){
    scene = new Scene{
      root = new BorderPane {
        prefWidth = 600
        prefHeight = 400
        
        center = webView
        top = new Button("update") { 
          alignment = Pos.Center
          onAction = (e: ActionEvent) => {
            val window : JSObject = engine.executeScript("window").asInstanceOf[JSObject]
            window.setMember("app", Main)

            engine.executeScript("update()")
          }
        }
      }
    }
  }
}
