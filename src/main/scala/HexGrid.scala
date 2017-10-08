import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.stage.Stage

// Based on: http://blog.ruslans.com/2011/02/hexagonal-grid-math.html

object HexGrid extends App {

  Application.launch(classOf[HexGrid], args: _*)

}

class HexGrid extends Application {

  def start(stage: Stage) {
    val root = new Group
    val scene = new Scene(root)
    val stage = new Stage

    val board = new HexBoard(8, 8, 40)

    board.setHexCellHandler(new HexCellHandler {
      def refresh(x: Int, y: Int, hg: HexGraphic) {
        if (x > 3) {
          hg.fill = if (y > 3) Color.ALICEBLUE else Color.CHOCOLATE
        } else {
          hg.fill = if (y > 3) Color.CORAL else Color.BISQUE
        }
      }
    })

    board.repaint

    board.setHexCellHandler(new HexCellHandler {
      def refresh(x: Int, y: Int, hg: HexGraphic) {
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

class HexBoard(val width: Int, val height: Int, radius: Int) extends Canvas with EventHandler[MouseEvent] {

  private val hexGraphic = new HexGraphic(getGraphicsContext2D)
  private val metrics = new HexMetrics(radius)
  private val cornersX = new Array[Double](6)
  private val cornersY = new Array[Double](6)

  this.setWidth(((width * radius * 3) / 2) + (radius / 2))
  this.setHeight(metrics.height * height + (if (height > 1) metrics.height / 2 else 0))

  private var hexCellHandler: HexCellHandler = _

  addEventHandler(MouseEvent.MOUSE_CLICKED, this)

  def getHexCellHandler = hexCellHandler

  def handle(event: MouseEvent) {
    val index = metrics.indexByPoint(event.getX, event.getY)
    if (isOnBoard(index._1, index._2)) repaintCell(index._1, index._2)
  }

  def isOnBoard(x: Int, y: Int) = x > -1 && x < width && y > -1 && y < height

  def repaint = for (x <- 0 until width; y <- 0 until height) repaintCell(x, y)

  def repaintCell(x: Int, y: Int) {
    if (hexCellHandler != null) hexCellHandler.refresh(x, y, hexGraphic)
    metrics.computeCorners(x, y, cornersX, cornersY)
    hexGraphic.draw(cornersX, cornersY)
  }

  def setHexCellHandler(handler: HexCellHandler) = hexCellHandler = handler

}

trait HexCellHandler {

  def refresh(x: Int, y: Int, hg: HexGraphic)

}

class HexGraphic(context: GraphicsContext) {

  var fill: Paint = Color.WHITE
  var border: Paint = Color.BLACK

     def draw[hex](cX: Array[Double], cY: Array[Double]) {
    context.setFill(fill)
    context.fillPolygon(cX, cY, 6)
    context.setFill(border)
    context.strokePolygon(cX, cY, 6)
  }

}

class HexMetrics(val radius: Double) {

  val width = radius * 2
  val height = radius * Math.sqrt(3)
  val side = radius * 3 / 2

  private val cornersX = Array(radius / 2, side, width, side, radius / 2, 0)
  private val cornersY = Array(0, 0, height / 2, height, height, height / 2)

  def computeCorners(x: Int, y: Int, cX: Array[Double], cY: Array[Double]) {
    var mX = x * side
    var mY = height * (2 * y + (x % 2)) / 2
    for (i <- 0 until 6) {
      cX(i) = mX + cornersX(i)
      cY(i) = mY + cornersY(i)
    }
  }

  def indexByPoint(x: Double, y: Double) = {
    val ci = Math.floor(x / side)
    val cx = x - side * ci

    val ty = y - (ci % 2) * height / 2
    val cj = Math.floor(ty / height)
    val cy = ty - height * cj

    if (cx > Math.abs(radius / 2 - radius * cy / height)) (ci.toInt, cj.toInt)
    else ((ci - 1).toInt, (cj + (ci % 2) - (if (cy < height / 2) 1 else 0)).toInt)
  }

}