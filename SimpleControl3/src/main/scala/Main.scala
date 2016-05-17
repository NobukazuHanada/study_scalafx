package simple3.control

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
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
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.MenuBar
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuItem
import scalafx.application.Platform
import scalafx.scene.input.KeyCombination
import java.io.IOException
import scalafxml.core.{NoDependencyResolver, FXMLView}
import scalafx.scene.control.ListView
import scalafx.scene.layout.VBox
import scalafx.scene.layout.Priority
import scalafx.geometry.Orientation
import scalafx.scene.control.SelectionMode
import scalafx.scene.control.cell.TextFieldListCell
import scalafx.scene.control.ListCell
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.ComboBox
import scalafx.scene.text.Font
import scalafx.scene.text.FontPosture
import scalafx.scene.text.FontWeight
import scalafx.scene.control.TableView
import scalafx.scene.control.TableRow
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.TableCell
import scalafx.scene.control.cell.TextFieldTableCell
import scalafx.beans.property.StringProperty
import scalafx.beans.property.ObjectProperty
import scalafx.util.StringConverter




object Main extends JFXApp {
  class ColorListCell extends javafx.scene.control.ListCell[String]{
    override def updateItem(item: String, empty: Boolean) {
      super.updateItem(item, empty)

      if(!empty) {
        val rect = new Rectangle {
          width = 20
          height = 20
          fill = Color.web(item)
        }
        setGraphic(rect)
        setText(item)
      }
    }
  }

  stage = new PrimaryStage() {
    title = "simple controller 2"
    scene = new Scene(500, 500) {
      root = new AnchorPane {
        val vbox = new VBox {
          layoutX = 50
          layoutY = 50
          prefWidth = 200
          prefHeight = 150
          vgrow = Priority.SOMETIMES
          val listView =
            new ListView[String](
              List(
                "Red", "Green", "Blue", "Yellow", "Magenta", "Cyan", "Black", "White"
              )
            ){
              editable = true
              cellFactory =
                (listView: ListView[String]) => new ColorListCell()
            }
          
          val selectionModel = listView.selectionModel()
          selectionModel.selectionMode = SelectionMode.MULTIPLE
          val selectedItems = selectionModel.selectedItems
          selectedItems.onChange(
            (buffer, changes) => {
              println(s"changes : $changes")
              println(s"buffer : ${buffer.toList}")
            }
          )
          listView.onEditCommit = (e : ListView.EditEvent[String]) => {
            listView.items().set(e.getIndex, e.getNewValue)
          }
          children = List(listView)
        }


        val families = ObservableBuffer("Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput")
        val familyCombo = new ComboBox[String](families) {
          layoutX = 300
          layoutY = 50
          editable = true
        }

        val styles = ObservableBuffer("Plain", "Bold", "Italic", "BoldItalic")
        val styleCombo = new ComboBox(styles){
          layoutX = 300
          layoutY = 100
          selectionModel().selectFirst()
        }

        val sizes = ObservableBuffer(8, 12, 16, 20, 24)
        val sizeCombo = new ComboBox(sizes) {
          layoutX = 300
          layoutY = 150
          selectionModel().selectFirst()
        }

        val text = new Label {
          layoutX = 200
          layoutY = 250
          text.value = "asdfghjkl12345678"
        }

        val handler = (event: ActionEvent) => {
          val family = familyCombo.value()
          val size = sizeCombo.value()

          val (posture, weight) = styleCombo.value() match {
            case "Bold" =>
              (FontPosture.REGULAR, FontWeight.BOLD)
            case "Italic" =>
              (FontPosture.ITALIC, FontWeight.MEDIUM)
            case "BoldItalic" =>
              (FontPosture.ITALIC, FontWeight.BOLD)
            case _ =>
              (FontPosture.REGULAR, FontWeight.MEDIUM)
          }

          text.font = Font.font(
            if( family == null || family.isEmpty() )
              "Serif" else family,
            weight,
            posture,
            if( size == null ) 12 else size
          )
        }

        familyCombo.onAction =  handler
        styleCombo.onAction = handler
        sizeCombo.onAction = handler

        class Album(
          val title_ : String,
          val artist_ : String,
          val release_ : Int
        ){
          val title = new StringProperty(this, "Title", title_)
          val artist = new StringProperty(this, "Artist", artist_)
          val release = new ObjectProperty(this, "Release", release_)
        }

        val tableView = new TableView[Album](ObservableBuffer( 
          new Album("Nobukazu Hanada", "Nobkz", 1989),
          new Album("Abby Load", "Beatiles", 1950),
          new Album("New generation", "AKB48", 2009)
                                             )) {
          editable = true
          layoutX = 50
          layoutY = 300
          columns ++= List(
          new TableColumn[Album, String] {
            text = "Title"
            cellValueFactory = { _.value.title }
            cellFactory = { _ =>
              new TextFieldTableCell[Album, String] (
                new StringConverter[String] {
                  def fromString(s:String) = s
                  def toString(s:String) = s
                }
              ) {
                onEditCommit = (e: CellEditEvent[Album, String]) => {
                  val album  = e.tableView.items().get(e.tablePosition.row)
                  album.title.value = e.getNewValue()
                }
              }
            }
            minWidth = 100
          },
          new TableColumn[Album, String] {
            text = "Artist"
            cellValueFactory = { _.value.artist }
            cellFactory = { _ =>
              new TextFieldTableCell[Album, String](
                new StringConverter[String] {
                  def fromString(s:String) = s
                  def toString(s:String) = s
                }
              ){
                onEditCommit = (e: CellEditEvent[Album, String]) => {
                  val album = e.tableView.items().get(e.tablePosition.row)
                  album.artist.value = e.newValue
                }
              }
            }
            minWidth = 150
          },
          new TableColumn[Album, Int]{
            text = "Release"
            cellValueFactory = { _.value.release }
            cellFactory = {_ =>
              new TextFieldTableCell[Album, Int](
                new StringConverter[Int] {
                  def fromString(s:String) = s.toInt
                  def toString(i:Int) = i.toString
                }
              ) {
                (e: CellEditEvent[Album, Int]) => {
                  val album = e.tableView.items().get(e.tablePosition.row)
                  album.release.value = e.newValue
                }
              }
            }
            minWidth = 70
          })
        }


        children = List(vbox, familyCombo, styleCombo, sizeCombo, text, tableView)
      }
    }
  }
}
