package chapter09

/**
 * 9.3 커링
 * 
 * 본래 언어에서 지원하는 듯한 제어 추상화 구문을 만드는 방법을 이해하려면,
 * 먼저 함수 언어에서 사용되는 기법 중 커링(currying)을 이해해야 한다.
 * 
 * 커링은 미국 수학자 하스켈 브룩스 커리의 이름에서 온 것으로 이름에서 보다시피
 * 3개의 언어 하스켈, 브룩스, 커리는 그의 이름에서 유래된 것.
 */
object c09_i03 extends App {
  /*
   * 커링되지 않은 일반적인 함수
   * (x: Int, y: Int)Int
   */
  def plainOldSum(x: Int, y: Int) = x + y
  println(plainOldSum(1, 2))
  
  /*
   * 커링한 함수
   * 실제로는 2개의 전통적인 함수를 연달아 호출한 것.
   * (x: Int)(y: Int)Int
   */
  def curriedSum(x: Int)(y: Int) = x + y
  println(curriedSum(1)(2))
  
  /*
   * 위 커링 함수를 풀어 보자면 다음과 같다.
   * first 메소드는 함수값((y: Int) => x + y))을 반환하며,
   * 그 함수값은 또 다시 y: Int 인자를 받아들인다.
   * (x: Int)Int => Int
   */
  def first(x: Int) = (y: Int) => x + y
  val second = first(1)
  println(second)
  println(second(2))
  
  // 위치 표시자를 이용하면, val second 함수에 대한 참조를 얻을 수 있다.
  val onePlus = curriedSum(1)_
  println(onePlus)
  println(onePlus(2))
}