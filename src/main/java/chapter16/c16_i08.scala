package chapter16

/**
 * 16.8 List 객체의 메소드
 *
 * 지금까지 살펴본 모든 연산은 List '클래스'의 메소드다.
 * 지금부터는 scala.List 동반'객체'의 메소드를 살펴보겠다.
 */
object c16_i08 extends App {
  /*
   * 1. 원소로부터 리스트 만들기: List.apply
   */
  List(1, 2, 3) // 리스트 리터럴
  List.apply(1, 2, 3)

  /*
   * 2. 수의 범위를 리스트로 만들기: List.range
   * 
   * List.range(from_inclu, until_exclu), 기본 간격은 1
   * List.range(from_inclu, until_exclu, interval)
   */
  List.range(1, 5)
  List.range(1, 9, 2)

  /*
   * 3. 균일한 리스트 생성: List.fill
   * 
   * 같은 원소의 복사본을 0번 이상 반복한 리스트를 만듬.
   * 생성할 리스트의 길이, 반복할 원소를 받음(커링)
   */
  List.fill(5)('a')
  // 다차원 리스트
  List.fill(2, 3)('b')

  /*
   * 4. 함수 도표화: List.tabulate
   * 
   * 기본 골격은 fill과 같으나, 어떤 정해진 원소 대신 함수를 가지고 원소를 계산하는 점이 다르다.
   */
  println(List.tabulate(5)(n => n * n))
  println(List.tabulate(2, 3)(_ * _))
  println(List.tabulate(2, 3)((x, y) => x * y))

  /*
   * 5. 여러 리스트 연결하기: List.concat
   */
  println(List.concat(List('a', 'b'), List('c')))
  println(List.concat(List(), List('c')))
  println(List.concat(List()))
}