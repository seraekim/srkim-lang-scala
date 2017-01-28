package chapter12

import chapter06.Rational

/**
 * 12.4 Ordered 트레이트
 * 
 * 풍부한 인터페이스를 이용하면 편리해지는 또 다른 영역으로는 비교가 있다.
 * 
 * 6장의 Rational 클래스에 비교 연산자 메소드를 추가한다고 생각해보자..
 * 
 * def < (that: Rational) = this.number * that.denom > that.number * this.denom
 * def > (that: Rational) = that < this ...
 * 
 * > 는 <의 반대일 뿐이고, >,<,>=,<= 4개의 연산자는 모두 >를 기반으로 만들어진다는 것을 볼 때, 반복할 것이 뻔한
 * 코드가 꽤나 많은 셈이다.
 * 
 * 실제로, 이런 문제는 너무 흔한데, 스칼라에서는 이를 해결할 Ordered라는 트레이트를 제공한다. 하나의 비교 연산자 구현으로
 * 모든 비교 연산자 구현을 대신할 수 있다.
 */
object c12_i04 extends App {
  val half = new Rational2(1,2)
  val third = new Rational2(1,3)
  println((half <= third))
  println(half > third)
}

/*
 * Ordered 트레이트를 이용해 Rational의 비교 연산자를 정의한 모습은 다음과 같다.
 * 지금까지 트레이트와 달리 Ordered는 타입 파라미터를 명시해야 한다. 비교자하는 클래스 타입을 넣는 것이다.
 * 
 * 호출대상객체가 인자와 같으면 0, 더 작으면 음수, 더 크면 양수를 리턴
 * 
 * 트레이트가 equals를 대신 정의하지 않음에 유의. 왜냐면 불가능하니까. 비교 관점에서 equals를 구현하려면
 * 전달받을 객체의 타입을 알아야 한다. 그러나 타입 소거(type erasure, 컴파일 시 타입 파라미터 정보를 제거, 자바도 그러함)
 * 때문에 Ordered 트레이트는 이러한 검사를 수행할 수 없다. 이를 우회할 방법은 30장에서 익힌다.
 */
class Rational2(n: Int, d: Int) extends Rational(n, d) with Ordered[Rational2] {
  def compare(that: Rational2) = (this.numer * that.denom) - (that.numer * this.denom)
}