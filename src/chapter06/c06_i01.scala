package chapter06

/**
 * 6장 함수형 객체
 * 
 * 6.1 분수 클래스 명세
 * 
 * raitonal number = numerator / denominator,
 * 1/2 + 2/3 = 7/6,
 * 2/10 = 1/5
 * 
 * 잘 보면 수학의 분수는 변경 가능한 상태가 없다.
 * 
 * 내장객체처럼 동작하도록 Rational 클래스를 작성해보자.
 * 
 * 6.14 결론
 * 
 * Rational 클래스는 30장에서 equals, hashcode 메소드를 오버라이드하며
 * 21장에서는 Rational 클래스의 동반 객체에 암시적 타입 변환 메소드를 넣어서
 * 편리하게 원하는 스코프로 불러올 수 있게 하는 방법을 설명하겠다.
 */
object c06_i01 {
  def main(args: Array[String]): Unit = {
    val oneHalf = new Rational(1,2)
    val twoThirds = new Rational(2,3)
    println(oneHalf add twoThirds)
    
    println(new Rational(66,42))
    println(new Rational(42,66))
    
    println(oneHalf * twoThirds)
    
    println(twoThirds * 2)
    /**
     * 6.12 암시적 타입 변환
     * 2 * Rational 원래는 안되지만,
     * 해당 스코프내에서 implicit 를 통해 해결 가능하다.
     * 
     * 너무 강력하기 때문에 단점이 되기도 한다. 2.10 이후 제어하려는 움직임이 있다.
     * 21장에서 다른 암시기법을 제시한다.
     * 
     * 6.13 주의사항
     * 
     * 아무리 단순하게 한들 지나치면 독이 될 수 있다. 연산자 오버로딩 등 클라이언트 프로그래머 입장에선
     * 난해할 수 있다.
     */
    implicit def intToRational(x: Int) = new Rational(x)
    println(2 * twoThirds)
  }
}