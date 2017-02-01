package chapter16

/**
 * 16.2 리스트 타입
 * 
 *  - 배열과 마찬가지로 리스트도 균일(homogeneous)하다. 즉 어떤 리스트에 속한 모든 원소의 타입은 같다.
 *  - 스칼라 리스트 타입은 공변적(covariant)이다. S 타입이 T 타입의 서브타입이면,
 *    List[S] 도 List[T]의 서브타입이다. List[String] 은 List[Object]의 서브타입이다.
 *    Nothing은 모든 스칼라타입의 서브타입이다. 따라서 List[Nothing] 은 모든 List[T]타입의
 *    객체이다.
 */
object c16_i02 extends App {
  // 앞에서 본 4가지 리스트에 타입을 명시해보자.
  val fruit: List[String] = List("apples", "oranges", "pears")
  val nums: List[Int] = List(1, 2, 3, 4)
  val diag3: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
  val empty: List[Nothing] = List()
  // Nothing은 모든 타입의 서브타입이므로.. 다음처럼 표현도 가능하다.
  val empty2: List[String] = List()
}