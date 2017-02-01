package chapter16

/**
 * 16장 리스트
 *
 * 어딜가나 언어는 데이터 구조 중 리스트를 가장 많이 사용하는 것 같다.
 * 리스트 디자인 원칙을 알아보자.
 *
 * 16.1 리스트 리터럴
 * 
 * 리스트는 배열과 꽤 비슷하지만 중요한 차이점이 두 가지가 있다.
 *  - 리스트는 변경이 불가능. 즉 할당문으로 변경 불가
 *  - 리스트구조는 재귀적(linkedList)이지만, 배열은 평면적이다.
 */
object c16_i01 extends App {
  val fruit = List("apples", "oranges", "pears")
  val nums = List(1, 2, 3, 4)
  val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
  val empty = List()
}