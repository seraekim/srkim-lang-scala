package chapter10

/**
 * 10.9 다형성과 동적 바인딩
 * 
 * Element 타입의 변수가 서브클래스인 ArrayElement 타입의 객체를 참조할 수 있음을 알 수 있다.
 * 이를 다형성이라 한다.
 * 
 * 다음 클래스는 주어진 높이와 너비만큼을 지정한 문자로 채우는 새로운 형태의 Element 이다.
 * 여기서 다루는 다형성은 서브타입 다형성(subtyping polymorphism)이라 하며 다른 형태의 다형성은
 * 19장에서 다룬다.
 * 
 * 10.10 final 멤버 선언
 * 
 * 서브 클래스가 특정 멤버를 오버라이드하지 못하게 막고 싶다면 final 쓴다.
 * ex) final override def demo() {...}
 * 
 * 클래스 자체를 상속 막고 싶다면?
 * final class A ...
 * 
 * 10.11 상속과 구성 사용
 *  - LineElement.scala
 */
class UniformElement (
  ch: Char,
  override val width: Int,
  override val height: Int
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}