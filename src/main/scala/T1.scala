
import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.text.{Font, FontWeight, Text}

object T1 extends JFXApp{

  def quebraLinha(i : Int) : Unit ={

    for(j <- 0 until i) {
      var label2 = new Label("")
      grid.add(label2, 3, 20 + j, 10, 10)
      label2.setStyle("-fx-font-size: 30;")
    }
  }

  val grid = new GridPane()
  val scene = new Scene(grid, 1200, 1200)

  for (column <- 0 until 32) {
    for (line <- 4 until 20) {
      var label = new Label("   ")
      label.setStyle("-fx-border-color: white; -fx-font-size: 30; -fx-background-color: red;")
      grid.add(label, column, line)
    }
  }

  quebraLinha(10)
  val scenetitle = new Text("Options:")
  scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20))
  grid.add(scenetitle, 0, 23, 10, 10)
  grid.add(new Button("Make a cell alive"), 4, 23, 10 , 10)
  grid.add(new Button("Next generation"), 17, 23, 10 , 10)
  grid.add(new Button("Halt"), 30, 23, 10 , 10)

  grid.setAlignment(Pos.Center)


  JFXApp.Stage.setScene(scene)
  JFXApp.Stage.titleProperty().setValue("GAME OF LIFE")
  JFXApp.Stage.show()
}
