package chapter01.item01

import java.math.BigInteger;
/**
 * 1.1 점점 여러분의 마음에서 자라가는 언어
 */
object c01_i01 {
  def main(args: Array[String]): Unit = {
    /*
     * 표현은 높은 수준. 연관 맵을 언어 문법 수준에서 지원
     * 더 저수준 단계에서 제어도 가능하다 . library abstraction
     * 디폴트 Map에서 HashMap, TreeMap 구체적 구현 지정 가능.
     * SynchronizedMap trait mix in 해서 맵이 스레드 안전해야 한다고 지정 가능.
     */
    var capital = Map("US" -> "Washington", "France" -> "Praris")
    capital += ("Japan" -> "Tokyo")
    println(capital("France"))
    // println(capital("Praris")) //java.util.NoSuchElementException: key not found: Praris

    println(factorial(30))

  }

  // 새로운 타입을 키워가기
  
  /*
   * 무한정으로 커지는 정수 타입이 필요하다?
   * BigInt는 수와 비슷한 여러타입의 대표이며, 스칼라 기본 지원이 아닌 표준라이브러리의 클래스다. 
   * 십진수, 복소수, 유리수, 신뢰구간, 다항식...
   * 
   * 스칼라는 언어가 기본 지원하는 것처럼 느껴지는 쉽게 사용할 수 있는 라이브러리를 제공하여,
   * 사용자가 필요한 방향으로 확장하고 고칠 수 있게 허용한다.
   * 
   * 스칼라에서 새로운 타입(BigInt)을 만들고 이를 내장 타입만큼 편하게 사용할 수 있음을 보여줌
   */
  def factorial(x: BigInt): BigInt = if (x == 0) 1 else x * factorial(x - 1);

  /*
   * 언어에서 다양한 숫자 타입을 제공하는건 그리 확장성이 좋지 못하다.
   * 자바의 BigInteger
   */
  def factorial2(x: BigInteger): BigInteger =
    if (x == BigInteger.ZERO)
      BigInteger.ONE
    else
      x.multiply(factorial2(x.subtract(BigInteger.ONE)))

  // 새로운 제어 구조 키워가기
  /* 스칼라는 확장성 원칙을 타입뿐 아니라 제어 구조에도 마찬가지로 적용한다.
   * 액터기반 동시성 프로그래밍 (actor-based concurrent programming)
   * 
   * 자바의 스레드 모델은 shared memory, lock을 기반으로 하며, 코드를 보고 논리적으로 추론하기 힘듬
   * 코드에 숨어있는 경합조건(race condiiton), 교착상태(deadlock)가 없다고 확신하기 힘듬
   * 
   * 안전한 대안은 얼랑(Erlang) 언어에서 사용하는 액터 메시지 전달 아키텍쳐
   * 
   * 스칼라는 자바 API를 제공하지만, 얼랑 액터 모델을 추가로 제공한다.
   * 
   * actor, loop, receive, 메시지, 메시지 송신 등이 모두 스칼라 내장 기능이 아니라는 점이 중요한 것.
   * 이 액터구성요소는 스칼라 언어와는 완전히 별개.
   * 즉, 스칼라 언어를 동시성 프로그래밍과 같은 특별한 분야로까지 키울 수 있다.
   */
      
}