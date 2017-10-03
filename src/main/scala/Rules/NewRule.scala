package Rules

import TemplateMethod.GameEngine

class NewRule extends GameEngine{

  /**
    * A ideia dessa classe é que o usuário possa utilizar sua própria regra no jogo
    * Sem precisar alterar regras ja existentes.
    */

  /** verifica se uma celula deve ser mantida viva */
  override def shouldKeepAlive(line: Int, column: Int) = ???

  /** verifica se uma celula deve (re)nascer */
  override  def shouldRevive(line: Int, column: Int) = ???
}
