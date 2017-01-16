package chapter03

/**
 * 11단계: 함수형 스타일을 인식하는 법을 배우자
 * 
 * 코드에 var만 있다면 명령형 스타일,
 * 코드에 val만 있다면 함수형 스타일
 */
object c03_i05 {
  /**
   * var가 있으므로 명령형
   */
  def printArgs(args: Array[String]): Unit = {
    var i =0
    while (i < args.length) {
      println(args(i))
      i += 1
    }
  }
  
  /**
   * 명령형에 비해 간결하고 오류 가능성이 낮다.
   * 그러나 완벽한 함수형은 아닌데,
   * Unit이 그 지표다. 부수효과 println이 있기 때문이다.
   */
  def printArgs2(args: Array[String]): Unit = {
    for ( arg <- args)
      println(arg)
    //args.foreach { println }
  }
  
  /**
   * 정말 함수적인 코드
   * 부수효과가 없음.
   * 이점으로 테스트하기가 더 편해짐.
   */
  def formatArgs(args: Array[String]) = args.mkString("\n")
  
  def main(args: Array[String]): Unit = {
    val res = formatArgs(Array("z", "o", "t"))
    // Boolean 이 거짓이라면 AssertionError
    assert(res == "z\no\nt")
  }
}