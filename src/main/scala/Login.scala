
import javafx.geometry.Insets

import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, PasswordField, TextField}
import scalafx.scene.layout.{GridPane, Pane, Priority}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, FontWeight, Text}


object Login extends JFXApp{

  val grid = new GridPane()
  grid.setAlignment(Pos.CENTER)
  grid.setHgap(10)
  grid.setVgap(10)
  grid.setPadding(new Insets(25, 25, 25, 25))

  val scene = new Scene(grid, 300, 275)

  JFXApp.Stage.setScene(scene)

  val scenetitle = new Text("Welcome")
  scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20))
  grid.add(scenetitle, 0, 0, 2, 1)

  val userName = new Label("User Name:")
  grid.add(userName, 0, 1)

  val userTextField = new TextField()
  grid.add(userTextField, 1, 1)

  val pw = new Label("Password:")
  grid.add(pw, 0, 2)

  val pwBox = new PasswordField()


}
