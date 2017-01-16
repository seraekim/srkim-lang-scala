package chapter05

/**
 * 5.4 산술 연산
 */
object c05_i04 {
  def main(args: Array[String]): Unit = {
    /*
     * 양쪽의 피연산자가 모두 수 타입(Int, Long, Byte, Short, Char) 라면 /는 정수만을 몫으로
     * %는 IEEE 754 표준이 아닌데, 만약 필요하다면 다음과 같이 쓸 수 있다.
     */
    println(math IEEEremainder(11.0, 4.0)) // -1.0
    println(11.0 % 4.0) // 3.0
   
    println(1 + -3)
  }
}