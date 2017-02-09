package chapter20

/**
 * 20장 추상 멤버
 * 
 * 어떤 클래스나 트레이트의 멤버가 그 클래스나 트레이트 안에서 완전한 정의를 갖고 있지 않으면
 * 추상 멤버라 한다. 추상 멤버는 그 정의가 잇는 클래스를 상속한 서브클래스에서 구현하도록 
 * 되어 있다.
 * 
 * 자바와 달리 스칼라는 추상화를 가장 일반적인 수준까지 구현하여, 메소드뿐 아니라, 추상 필드도
 * 정의할 수 있으며, 클래스나 트레이트의 멤버로 추상 타입을 정의할 수도 있다.
 * 
 * 이 장에서는, 4 종류의 추상 멤버, val, var, 메소드, 타입을 설명할 것이다.
 * 
 *  - pre-initialized field
 *  - 지연(lazy) val
 *  - 경로 의존적인 타입(path dependent type)
 *  - 열거형(enumeration)
 *  
 * 20.1 추상 멤버 간략하게 돌아보기
 * 
 */
trait Abstract {
  // 4개의 추상멤버
  type T // 타입 멤버
  def transform(x: T): T // 추상 val 변수
  val initial: T
  var current: T
}
class Concrete extends Abstract {
  /*
   * 20.2 타입 멤버
   * 
   * 추상 타입은 정의 없이 선언(키워드만 사용)만 한 타입이다.
   * 스칼라에서는 클래스나, 트레이트를 추상 타입이라고 부르지 않는다.
   * 추상 타입은 항상 어떤 클래스나 트레이트의 멤버인 것이다.
   * 
   * Concrete의 정의 안에 T라는 타입이 있는 경우 String을 의미
   * 타입 멤버를 쓰는 이유는, 실제 이름이 너무 길거나 의미가 불명확할 때, 간단하고 의도를
   * 
   * 더 잘 전달할 수 있는 별명을 선언하는 것. 타입 멤버의 다른 사용법으로는, 서브 클래스에서
   * 꼭 정의해야 하는 추상 타입을 선언하는 것이다.
   */
  type T = String
  def transform(x: String) = x + x
  val initial = "hi"
  var current = initial
}

/*
 * 20.3 추상 val 변수
 * 
 * val 이름: 타입
 * 어떤 클래스 안에서 어떤 변수에 대해 정확한 값을 알 수 없지만, 그 변수가 클래스의 인스턴스에서
 * 변하지 않으리란 사실은 알고 있을 때 추상 val을 사용한다.
 * 
 * 추상 val은 파라미터 없는 추상 메소드 선언과 비슷해보인다.
 * def initial: String
 * 만약 val이 아니라 def라면, 사용할 때마다, 같은 값을 얻을 수 있음을 확신 못한다.
 */
abstract class Fruit {
  val v: String
  def m: String
}
abstract class Apple extends Fruit {
  val v: String
  val m: String //def를 val로 오버라이드 할 수 있다.
}
abstract class BadApple extends Fruit {
  // def v: String // 오류: val은 def로 오버라이드 할 수 없다.
  def m: String
}
object c20_i01 extends App {
  
}