package chapter08

/**
 * 8.4 간단한 형태의 함수 리터럴
 */
object c08_i04 {
  val someNumbers = List(-11, -10, 0, 5, 10)
  someNumbers.filter((x: Int) => x > 0)
  /*
   * 함수 리터럴을 좀 더 간단하게 만드는 방법은 인자의 타입을 제거하는 것
   * someNumbers 는 List[Int] 이므로
   * 스칼라 컴파일러는 인자로 넘겨진 x가 Int 타입인 것을 추론할 수 있다.
   * 
   * 이를 타깃 타이핑이라 함
   */
  someNumbers.filter(x => x > 0)
}