
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Menu, MenuBar}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

object HelloUI extends JFXApp{
  stage = new JFXApp.PrimaryStage {
    title.value = "GAME OF LIFE - GoL"
    scene = new Scene(800, 800) {
      val button = new Button("Click me")
      //button.layoutX = 100
     // button.layoutY = 100
      val rect = Rectangle (400, 200, 20, 20)
      val rect2 = Rectangle (600, 200, 20, 20)
    rect.fill = Color.Black
    content = List(button, rect, rect2)
    }
  }
}