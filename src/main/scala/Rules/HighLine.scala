package Rules

import TemplateMethod.GameEngine

class HighLine extends GameEngine{
  /**
    * Uma celula morta com exatamente seis celulas vizinhas vivas
    * se torna uma celula viva (nascimento).
    * Uma celula viva com duas ou tres celulas vizinhas vivas
    * permanece viva (sobrevive).
    * Em todos os outros casos, a celula morre ou continua morta
    * (superpopulacao ou solidao).
    */

  /** verifica se uma celula deve ser mantida viva */
  override def shouldKeepAlive(line: Int, column: Int) {
    if (isCellAlive(line, column) &&
      (numberOfNeighborhoodAliveCells(line, column) == 3 || numberOfNeighborhoodAliveCells(line, column) == 2))
      true
    else
      false
  }


  /** verifica se uma celula deve (re)nascer */
  override def shouldRevive(line: Int, column: Int) {
    if (!isCellAlive(line, column) && numberOfNeighborhoodAliveCells(line, column) == 6)
      true
    else
      false
  }
}
