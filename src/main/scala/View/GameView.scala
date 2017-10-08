package View

import Strategy.GameController

/**
 * Representa o componente View do GoL
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameView extends ViewSFX{
	//update()
	GameController.halt()

	def update() {
		createMatrix()
		options()
	}

  private def makeCellAlive {

	  var i = 0
	  var j = 0
		makeCell()
    GameController.makeCellAlive(i, j)
	}

  private def nextGeneration = GameController.nextGeneration

  private def halt = GameController.halt

}