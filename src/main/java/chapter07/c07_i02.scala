package chapter07

/**
 * 7.2 while 루프
 *
 * 스칼라에는 do while도 있다. 본문을 수행하고나서 조건을 검사한다.
 * while, do-while이 이루는 구조는 수행 결과가 특정 값이 아니므로
 * 표현식이라 하지 않고 루프라고 한다. 루프의 결과는 Unit다
 *
 * Unit는 ()로 표시한다.
 * ()가 있다는 점에서 자바의 void와는 다르다.
 * 결과 값이 없으니 프로시저라하며, 값이 있다면 함수라 한다.
 * 
 * 결론 : 최대한 var, while 사용을 자제하라.
 */
object c07_i02 extends App {
  // 최대공약수를 계산 
  // 명령형 스타일(var, while)
  def gcdLoop(x: Long, y: Long): Long = {
    var a = x
    var b = y
    while (a != 0) {
      val temp = a
      a = b % a
      b = temp
    }
    b
  }
  // 함수형 스타일(val, 재귀)
  // 명령형에 비해 프로그래머가 직관적으로 이해하기 힘들 수 있다.
  def gcd(x: Long, y: Long): Long = if (y == 0) x else gcd(y, x % y)
  println(gcdLoop(3, 6))

  def greet() { println("hi") }
  println(greet() == ())

  // do-while로 표준 입력 읽기
  /*var line = ""
  do {
    line = readLine()
    println("Read: " + line)
  } while (line != "")*/

  // 자바스타일
  var line = ""
  // var 에 재할당을 하는 경우 Unit 값이 결과가 됨.
  // var line_=(x$1: String): Unit
  while ((line = readLine()) != "") // 작동하지 않음! 무한반복!
    println("Read: " + line)

}