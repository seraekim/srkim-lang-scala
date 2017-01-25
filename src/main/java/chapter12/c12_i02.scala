package chapter12

/**
 * 12.2 간결한 인터페이스와 풍부한 인터페이스
 * 
 * 어떤 클래스에 그 클래스가 이미 갖고 있는 메소드를 기반으로 하는 새로운 메소드를 추가
 * 이는 간결한 인터페이스를 풍부한인터페이스로 만드는 것을 의미하며 트레이트를 사용한다.
 * 
 * 자바의 인터페이스는 풍성하기보다는 간결한 경향을 띤다.
 */
object c12_i02 {
  
}
/*
 * 자바의 CharSequence를 스칼라의 트레이트로 정의하자면...
 * 자바의 CharSequence는 4개의 메소드만 가지고 있는데, String의 모든 메소드를
 * 포함했다면, CharSequence 구현은 부담이 큰 작업이었을 것이고, 모든 자바프로그래머는
 * 모두 수십개의 메소드를 더 정의해야 했을 것이다. 스칼라는 이를 편하게 할 수 있다.
 */
trait CharSequence {
  def charAt(index: Int): Char
  def length: Int
  def subSequence(start: Int, end: Int): CharSequence
  def toString(): String
}