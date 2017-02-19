package chapter23

/**
 * 23.2 n 여왕 문제
 * 
 * for 표현식이 특히나 잘 어울리는 분야는 조합 퍼즐(combinatorial puzzle) 분야다.
 * 그러한 퍼즐의 예로 표준 체스판에서 8개의 여왕 말을 서로 잡지 못하게 놓는 문제인 8 여왕 문제를 들 수 있다.
 * 두 여왕 말이 같은 열, 행, 또는 대각선상에 있으면 서로를 잡을 수 있다.
 * 
 * 명령형 해법으로 풀어보자면, 정말로 모든 가능성을 시도해보는 체계를 만드는 것으로 어려워 보인다.
 * 더 함수적인 접근 방법은 해를 직접 값으로 표현하는 것이다. 해는 각 여왕의 체스판상의 위치를 표현하는
 * 좌표들의 리스트다. 하지만 이 전체 해를 단 한번에 찾을 수는 없다. 해를 한 번에 하나씩 여왕을
 * 인접한 행에 추가해가면서 점진적으로 만들어 나가야 한다.
 * 
 * 이는 재귀적인 알고리즘을 의미한다 N * N 크기의 체스판에 k개의 여왕을 놓는 모든 해를 (k < N)
 * 만들어냈다면, 그런해는 (row, column) 좌표가 들어있는 길이 k인 리스트로 표현할 수 있다.
 * 이런 부분해 리스트는 스택으로 다루는 것이 편리하다. 즉 k번째 행의 여왕 좌표를 리스트 맨앞에 두고
 * 그 다음은 k-1을 둔다.
 */
object c23_i02 extends App {
 
  def queens(n: Int): List[List[(Int, Int)]] = {
    def placeQueens(k: Int): List[List[(Int, Int)]] = {
      if (k == 0) List(List())
      else {
        for {
          queens <- placeQueens(k - 1)
          column <- 1 to n
          queen = (k, column)
          if isSafe(queen, queens)
        } yield queen :: queens // 새 여왕이 다른 여왕을 잡을 수 없는 위치라면 새로운 부분해 생성
      }
    }
    placeQueens(n)
  }
  
  // 다른 여왕들을 잡을 수 있는 위치가 아니라면 안전하다는 점을 표현
  def isSafe(queen: (Int, Int), queens: List[(Int, Int)]) = {
    queens forall (q => !inCheck(queen, q))
  }
  
  // 여왕 q1과 q2가 서로 잡을 수 있는 지를 표현
  def inCheck(q1: (Int, Int), q2: (Int, Int)) =
    q1._1 == q2._1 || // 같은 행, 사실 이 경우는 불가하다. placeQueens가 이미 각 여왕을 다른 행에 넣기 때문
    q1._2 == q2._2 || // 같은 열
    (q1._1 - q2._1).abs == (q1._2 - q2._2).abs // 대각선, 즉 행좌표와 열좌표의 간격이 같은 경우
  
  // 찾아낸 모든 해는 좌표로 되어 있어 보기 힘들다. 다음 함수를 사용하면 보기 좋게 출력가능 하다. 이 또한 for 표현식의 좋은 예다.
  def printSolutions(tbls: List[List[(Int, Int)]]) = {
    def printSolution(tbl: List[(Int, Int)]) = {
      val len = tbl.head._1
      println(" " + "_" * (len * 2 - 1))
      for {
        dim <- tbl.reverse
        col <- 1 to len // 모든 셀에 무언가를 출력
        pipe = if (col == 1) "|" else ""
        cell = if (col == dim._2) "Q|" else "_|"
        nl = if (col == len) "\n" else ""
      } print(pipe + cell + nl)
    }
    for (t <- tbls) printSolution(t)
  }
  
  val queen8 = queens(8);
  printSolutions(queen8)
  
/* 
 _______________
|Q|_|_|_|_|_|_|_|
|_|_|_|_|Q|_|_|_|
|_|_|_|_|_|_|_|Q|
|_|_|_|_|_|Q|_|_|
|_|_|Q|_|_|_|_|_|
|_|_|_|_|_|_|Q|_|
|_|Q|_|_|_|_|_|_|
|_|_|_|Q|_|_|_|_|
 _______________
|Q|_|_|_|_|_|_|_|
|Q|_|_|_|_|_|_|_|
|Q|_|_|_|_|_|_|_|
|Q|_|_|_|_|_|_|_|
|Q|_|_|_|_|_|_|_|
|Q|_|_|_|_|_|_|_|
|Q|_|_|_|_|_|_|_|
|Q|_|_|_|_|_|_|_|
 _______________
|Q|_|_|_|_|_|_|_|
|_|Q|_|_|_|_|_|_|
|_|_|Q|_|_|_|_|_|
|_|_|_|Q|_|_|_|_|
|_|_|_|_|Q|_|_|_|
|_|_|_|_|_|Q|_|_|
|_|_|_|_|_|_|Q|_|
|_|_|_|_|_|_|_|Q|

*/
}