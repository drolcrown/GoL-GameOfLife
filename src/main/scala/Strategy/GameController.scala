package Strategy

import Original_POO.{GameEngine, Statistics}
import View.GameView

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {
  
  def start {
    GameView.update
  }
  
  def halt() {
    Statistics.display
    //System.exit(0)
  }

  def makeCellAlive(i: Int, j: Int) {
    try {
			GameEngine.makeCellAlive(i, j)
			GameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    GameEngine.nextGeneration
    GameView.update
  }
  
}