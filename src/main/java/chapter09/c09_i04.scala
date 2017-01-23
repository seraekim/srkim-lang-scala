package chapter09

/**
 * 9.4 새로운 제어 구조 작성
 * 
 * 함수가 1급 계층인 언어에서는 언어의 문법이 고정되어 있더라도
 * 새로운 제어 구조를 작성할 수 있다. 함수를 인자로 받는 메소드만 있으면 된다.
 */
object c09_i04 extends App {
  /*
   * twice라는 제어 구조가 있다 가정하자.
   */
  def twice(op: Double => Double, x: Double) = op(op(x))
  /*
   * op 인자로 함수값(_ + 1)을 받고, x 인자로 5를 받아서
   * 실제로 ((5 + 1) + 1) 연산이 이뤄진다.
   */
  println(twice(_ + 1, 5))
}