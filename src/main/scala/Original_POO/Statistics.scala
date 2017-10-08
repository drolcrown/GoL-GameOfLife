package Original_POO

import javafx.geometry.Insets

import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout.GridPane
import scalafx.scene.text.{Font, FontPosture, Text}

/**
 * Classe usada para computar as estatisticas 
 * do GoL.
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br)
 */
object Statistics {
  
  private var revivedCells = 0
	private var killedCells = 0
  
	
	def getRevivedCells = revivedCells

	def recordRevive = revivedCells += 1

  def getKilledCells = killedCells
	
  def recordKill = killedCells += 1 

	def display = {
		val gridM = new GridPane()
		val sceneM = new Scene(gridM, 1200, 1200)

		gridM.setAlignment(Pos.CENTER)
		gridM.setHgap(10)
		gridM.setVgap(10)
		gridM.setPadding(new Insets(25, 25, 25, 25))

		val scenetitle = new Text("GAME OF LIFE")
		scenetitle.setFont(Font.font("Tahoma", FontPosture.Italic, 80))
		gridM.add(scenetitle, 0, 0, 1, 1)

		var labelR = new Label("================================= ")
		labelR.setStyle("-fx-font-size: 30")
		gridM.add(labelR, 0, 1)

		val scenetitle1 = new Text("Statistics")
		scenetitle1.setFont(Font.font("Tahoma", FontPosture.Italic, 50))
		gridM.add(scenetitle1, 0, 2, 1, 1)

		var labelC = new Label("================================= ")
		labelC.setStyle("-fx-font-size: 30;")
		gridM.add(labelC, 0, 3)

		var labelM = new Label("Revived cells: " + revivedCells)
		labelM.setStyle("-fx-font-size: 30;")
		gridM.add(labelM, 0, 4)

		var labelN = new Label("Killed cells: " + killedCells)
		labelN.setStyle("-fx-font-size: 30;")
		gridM.add(labelN, 0, 5)

		var labelT = new Label("================================= ")
		labelT.setStyle("-fx-font-size: 30;")
		gridM.add(labelT, 0, 6)

		gridM.setAlignment(Pos.Center)
		JFXApp.Stage.setScene(sceneM)
		JFXApp.Stage.titleProperty().setValue("GAME OF LIFE")
		JFXApp.Stage.show()
	}
  
}