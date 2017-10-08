package View

import Strategy.GameController

/**
 * Programa principal do GoL.
 *
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object Main {
  
  val height = 20
  val width = 32
  
  def main(args: Array[String]){
    GameController.start
  }
}