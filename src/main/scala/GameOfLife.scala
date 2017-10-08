import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.seqAsJavaList
import scala.util.Random

import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import javafx.util.Duration

/**
 * John Conway's Game of Life - with JavaFX and Scala
 */
object GameOfLife {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[GameOfLife], args: _*)
  }

}

trait JfxUtils {
  def mkEventHandler[E <: Event](f: E => Unit) = new EventHandler[E] { def handle(e: E) = f(e) }
}

class GameOfLife extends javafx.application.Application with JfxUtils {

  val canvasWidth = 800
  val canvasHeight = 600
  val cellCount = 40
  val lifeSpeed = 2

  // --------------------------------------------------------------------------
  val (width, height) = ((canvasWidth / cellCount) - 2, (canvasHeight / cellCount) - 2)

  case class Cell(x: Int, y: Int, alive: Boolean = false) extends Rectangle {
    setUserData(alive)
    setX(x * (width + 2))
    setY(y * (height + 2))
    setWidth(width)
    setHeight(height)
    setArcWidth(2)
    setArcHeight(2)

    paint

    setOnMousePressed(mkEventHandler((e: MouseEvent) => { killOrResurrect }))

    def paint = if (isAlive) setFill(Color.RED) else setFill(Color.WHITESMOKE)

    def isAlive: Boolean = getUserData().asInstanceOf[Boolean]

    def killOrResurrect = {
      setUserData(!isAlive)
      paint
    }
  }

  override def start(stage: Stage): Unit = {
    stage.setTitle("Conway's Game of Life")

    val drawingArea = new Group()
    val background = {
      val b = new Rectangle(0, 0, canvasWidth, canvasHeight)
      val stops = List(new Stop(0, Color.BLACK), new Stop(1, Color.WHITESMOKE))
      val g = new LinearGradient(0.0, 1.0, 0.0, 0.0, true, CycleMethod.NO_CYCLE, stops)
      b.setFill(g)
      b
    }
    val cells = new Group()
    cells.getChildren().addAll(for {
      x <- 0 to cellCount
      y <- 0 to cellCount
    } yield Cell(x, y, Random.nextBoolean))

    drawingArea.getChildren.addAll(background, cells)

    val growTimeline = new Timeline
    growTimeline.setRate(lifeSpeed)
    growTimeline.setCycleCount(Animation.INDEFINITE)
    growTimeline.getKeyFrames().add(
      new KeyFrame(Duration.seconds(1),
        new EventHandler[ActionEvent]() {
          def handle(event: ActionEvent) {
            val nextGen = nextGeneration((convert2CellList(cells.getChildren())))
            cells.getChildren().clear()
            cells.getChildren.addAll(nextGen)
          }
        }))
    growTimeline.play()

    stage.setScene(new Scene(drawingArea, canvasWidth, canvasHeight))
    stage.show()
  }

  def nextGeneration(allCells: List[Cell]): List[Cell] = {
    for (c <- allCells) yield {
      if (c.isAlive) {
        getAliveNeighbors(c, allCells).size match {
          case 1 => c.copy(alive = false)
          case 2 => c
          case 3 => c
          case _ => c.copy(alive = false)
        }
      } else {
        if (getAliveNeighbors(c, allCells).size == 3)
          c.copy(alive = true)
        else c
      }
    }
  }

  def getAliveNeighbors(c: Cell, cells: List[Cell]) = getNeighbors(c, cells).filter(_.isAlive)

  def convert2CellList(nodes: ObservableList[Node]): List[Cell] = {
    (for (
      n <- nodes if (n match {
        case c: Cell => true
        case _ => false
      })
    ) yield n.asInstanceOf[Cell]).toList
  }

  def getNeighbors(cell: Cell, cells: List[Cell]) =
    cells.filter(c => c != cell && (scala.math.abs(cell.x - c.x) <= 1 && scala.math.abs(cell.y - c.y) <= 1))
}