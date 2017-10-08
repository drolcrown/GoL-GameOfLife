package View

import javafx.geometry.Insets

import Original_POO.GameEngine

import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.text.{Font, FontPosture, FontWeight, Text}

class ViewSFX extends JFXApp {

  val grid = new GridPane()
  val scene = new Scene(grid, 1200, 1200)

  def quebraLinha(grid: GridPane, i: Int): Unit = {

    for (j <- 0 until i) {
      var label2 = new Label("")
      grid.add(label2, 3, 20 + j, 10, 10)
      label2.setStyle("-fx-font-size: 30;")
    }
  }

  def createMatrix(): Unit = {
    for (column <- 0 until 31) {
      for (line <- 0 until 21) {
        var label = new Label("   ")
        label.setStyle("-fx-border-color: white; -fx-font-size: 30; -fx-background-color: red;")
        grid.add(label, column, line)
      }
    }
  }


  def makeCell() : Unit={
    val gridM = new GridPane()
    val sceneM = new Scene(gridM, 1200, 1200)

    gridM.setAlignment(Pos.CENTER)
    gridM.setHgap(10)
    gridM.setVgap(10)
    gridM.setPadding(new Insets(25, 25, 25, 25))

    JFXApp.Stage.setScene(sceneM)

    val scenetitle = new Text("GAME OF LIFE")
    scenetitle.setFont(Font.font("Tahoma", FontPosture.Italic, 80))
    gridM.add(scenetitle, 0, 0, 2, 1)

    var labelR = new Label("Inform the row number (0 - " + (GameEngine.height - 1) + "): ")
    labelR.setStyle("-fx-font-size: 30")
    gridM.add(labelR, 0, 1)

    val userTextField = new TextField()
    gridM.add(userTextField, 1, 1)

    var labelC = new Label("Inform the column number (0 - " + (GameEngine.width - 1) + "): ")
    labelC.setStyle("-fx-font-size: 30;")
    gridM.add(labelC, 0, 2)

    val userTextField2 = new TextField()
    gridM.add(userTextField2, 1, 2)

    gridM.setAlignment(Pos.Center)
    JFXApp.Stage.titleProperty().setValue("GAME OF LIFE")
    JFXApp.Stage.show()

    //if()
  }

  def options() : Unit ={
    createMatrix()
    quebraLinha(grid, 10)

    val scenetitle = new Text("Options:")
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20))
    grid.add(scenetitle, 0, 23, 10, 10)
    grid.add(new Button("Make a cell alive"), 4, 23, 10, 10)
    grid.add(new Button("Next generation"), 17, 23, 10, 10)
    grid.add(new Button("Halt"), 30, 23, 10, 10)

    grid.setAlignment(Pos.Center)

    JFXApp.Stage.setScene(scene)
    JFXApp.Stage.titleProperty().setValue("GAME OF LIFE")
    JFXApp.Stage.show()
  }

}
