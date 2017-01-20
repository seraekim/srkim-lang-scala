package chapter07

/**
 * 7.8 명령형 스타일 코드 리팩토링
 */
object c07_i08 extends App {
  /*
   * 리스트 7.19 (함수형)
   */
  // 하나의 열을 시퀀스로 반환
  def makeRowSeq(row: Int) =
    for (col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      //println(padding + prod)
      padding + prod
    }
  // 하나의 열을 문자열로 변환
  def makeRow(row: Int) = {val printRow = makeRowSeq(row).mkString}

  // 표를 한 줄에 한 열의 내용을 담고 있는 문자열로 반환
  // print를 하지 않는다. 부수효과 없다.
  def multiTable() = {
    val tableSeq = // 한 열에 해당하는 문자열의 시퀀스
      for (row <- 1 to 10)
        yield makeRow(row)
    tableSeq.mkString("\n")
  }
}