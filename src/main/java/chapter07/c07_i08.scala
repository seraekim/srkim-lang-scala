package chapter07

/**
 * 7.8 명령형 스타일 코드 리팩토링
 * 
 * 명령형 스타일 : var, while
 * 함수형 스타일 : val, for 표현식, 도우미 함수
 * 함수형 스타일 장점 : 부수효과 없다 -> 단위테스트 쉽다
 * 
 */
object c07_i08 extends App {
  /*------------------------------
   * 리스트 7.19 (함수형)
   */
  // 하나의 열을 시퀀스로 반환 yield => IndexedSeq[String]
  def makeRowSeq(row: Int) =
    for (col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      //println(padding + prod)
      padding + prod
    }
  // 시퀀스에 담긴 문자열을 이어붙여 하나의 문자열로 연결
  def makeRow(row: Int) = {
    val printRow = makeRowSeq(row).mkString
    //println(printRow)
    printRow
  }

  // print를 하지 않는다. 부수효과 없다.
  def multiTable() = {
    val tableSeq = // 한 열에 해당하는 문자열의 시퀀스
      for (row <- 1 to 10)
        yield makeRow(row)
    tableSeq.mkString("\n")
  }
  println(multiTable)
  //------------------------------
}