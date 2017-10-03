import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.shape.Rectangle

object HelloUI extends JFXApp{
  stage = new JFXApp.PrimaryStage {
    title.value = "Hello Stage"
    width = 600
    height = 450
    scene = new Scene {
      content = new Rectangle {
        x = 25
        y = 40
        width = 100
        height = 100
      }
    }
  }
}