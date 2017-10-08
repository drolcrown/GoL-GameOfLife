import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.canvas.{Canvas, GraphicsContext}
import javafx.scene.input.MouseEvent
import javafx.scene.{Group, Scene}
import javafx.scene.paint.{Color, Paint}
import javafx.stage.Stage

object MyCell extends App{

  Application.launch(classOf[Grid], args: _*)
}


class Grid extends Application {

  def start(stage: Stage) {
    val root = new Group
    val scene = new Scene(root)
    val stage = new Stage

    val board = new RectBoard(8, 8, 40, 40)

    board.setRectCellHandler(new RectCellHandler {
      def refresh(x: Int, y: Int, hg: RectGraphic) {
        if (x > 3) {
          hg.fill = if (y > 3) Color.ALICEBLUE else Color.CHOCOLATE
        } else {
          hg.fill = if (y > 3) Color.CORAL else Color.BISQUE
        }
      }
    })

    board.repaint

    board.setRectCellHandler(new RectCellHandler {
      def refresh(x: Int, y: Int, hg: RectGraphic) {
        hg.fill = Color.DARKGREEN
      }
    })

    root.getChildren.add(board)
    stage.setTitle("Hex Field")
    stage.centerOnScreen
    stage.setScene(scene)
    stage.show
  }

}


class RectBoard(val width: Int, val height: Int, radius: Int,  compr: Int) extends Canvas with EventHandler[MouseEvent] {

  private val rectGraphic = new RectGraphic(getGraphicsContext2D)
  private val metrics = (width + height) / 2

  this.setWidth(width)
  this.setHeight(height)

  private var rectCellHandler: RectCellHandler = _

  addEventHandler(MouseEvent.MOUSE_CLICKED, this)

  def getRectCellHandler = rectCellHandler

  def handle(event: MouseEvent) {
    val index = (event.getX, event.getY)
    if (isOnBoard(height, width))
      repaintCell(height, width)
  }

  def isOnBoard(x: Int, y: Int) = x > -1 && x < width && y > -1 && y < height

  def repaint = for (x <- 0 until width; y <- 0 until height) repaintCell(x, y)

  def repaintCell(x: Int, y: Int) {
    if (rectCellHandler != null) rectCellHandler.refresh(x, y, rectGraphic)
    (x, y, height, width)
    rectGraphic.draw(height, width)
  }

  def setRectCellHandler(handler: RectCellHandler) = rectCellHandler = handler

}


trait RectCellHandler {

  def refresh(x: Int, y: Int, hg: RectGraphic)

}

class RectGraphic(context: GraphicsContext) {

  var fill: Paint = Color.WHITE
  var border: Paint = Color.BLACK

  def draw[rect](cX: Int, cY: Int) {
    context.setFill(fill)
    context.fillRect(cX, cY, 6, 6)
    context.setFill(border)
    context.strokeRect(cX, cY, 6, 6)
  }

}
