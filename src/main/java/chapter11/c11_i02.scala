package chapter11

/**
 * 11.2 여러 기본 클래스를 어떻게 구현했는가?
 * 
 * 스칼라는 자바와 같은 방법, 즉 32비트 워드로 정수를 저장한다.
 * 
 * 스칼라는 정수가 객체여야 하는 경우 백업클래스인 java.lang.Integer를 사용한다.
 * 예를 들어 정수에 toString 호출하거나 Any 타입의 변수에 할당하는 경우이다.
 * 
 * 자바 5의 오토박싱과 비슷하게 들릴 것이다. 실제로 비슷한데 다른점은 관찰하기가 힘들다.
 * boolean isEqual(Integer x, Integer y) {return x ==y;}
 * 위 자바코드는 false를 리턴한다. 다른 객체가 되어버린 것이다. 이런 예는 어떤면에서
 * 자바는 객체지향적이지 않음을 보여준다.
 * 
 * 값타입에서 ==는 자연적인(값) 동일성.
 * 참조타입의 경우 ==는 Object의 equals 별명
 * 따라서 자바의 문자열 비교하기 함정에 빠질 일이 없다.
 * 
 * 하지만 참조 동일성이 필요한 경우도 있다. eq를 사용하면 된다.
 * eq는 자바의 ==와 동일하다.
 * 
 */
object c11_i02 extends App {
  /*
   * 자바의 오토박싱을 스칼라로 시도해보자.
   */
  def isEqual(x: Int, y: Int) = x ==y
  def isEqual2(x: Any, y: Any) = x ==y
  println(isEqual(1,1))
  println(isEqual2(1,1))
  
  val x = new String("abc")
  val y = new String("abc")
  println(x == y)
  println(x eq y)
  println(x ne y)
}