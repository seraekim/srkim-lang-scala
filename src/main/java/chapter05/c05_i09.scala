package chapter05

/**
 * 5.9 풍부한 래퍼
 * 암시적 변환
 * 
 * 5.10 결론
 * 스칼라에선 연산자가 메소드 호출이며, 기본 타입에 더 유용한 메소드를 제공해주는 풍부한 래퍼 클래스로 변환하는
 * 암시적 변환이 존재한다.
 */
object c05_i09 {
  def main(args: Array[String]): Unit = {
    /*
     * 풍부한 래퍼가 제공하는 연산
     */
    println(0 max 5)
    println(0 min 5)
    println(-2.7 abs)
    println(-2.7 round)
    println(1.5 isInfinity)
    println(((1.0)/0) isInfinity)
    println(4 to 6)
    println("bob" capitalize)
    println("robert" drop 2)
    println()
    
    /*
     * 풍부한 래퍼 클래스
     * Byte, Short, Int, Char, Float, Double, Boolean -> Rich*
     * String -> StringOps
     */
    
  }
}