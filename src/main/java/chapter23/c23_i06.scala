package chapter23

/**
 * 23.6 for 일반화
 * 
 * for 표현식의 변환은 오직 map, flatMap, withFilter 메소드에만 의존한다.
 * List, Array 외에도, range, iterator, stream 등 모든 집합 구현이 지원한다.
 * 
 *  - map만 정의했다면 제너레이터가 1개만 있는 for 표현식을 사용할 수 있다.
 *  - map과 flatMap을 함께 정의했다면, 제너레이터가 여럿 있는 for 표현식도 사용 가능하다.
 *  - foreach를 정의했다면, for 루프(제너레이터 개수는 상관없음)사용 가능.
 *  - withFilter를 정의하면, for 표현식 안에서 if로 시작하는 for 필터 표현식을 사용
 * 
 */
/*
 * 어떤 종류의 컬렉션을 표시하는 파라미터화된 클래스 C가 있다고 하자.
 * map, flatMap, withFilter, foreach에 대해 다음과 같은 타입 시그니처를 선택하는 것은
 * 아주 자연스러운 일이다.
 * 
 * 함수 프로그래밍에는 모나드(monad)라는 일반적인 개념이 있다. 계산과 관련된 다양한 타입을 컬렉션을 포함해
 * 설명할 수 있다.계산에 들어가는 것에는 상태 I/O, 백트래킹, 트랜잭션 등이 있다. map, flatMap, withFilter를
 * 모나드 위에서 표현할 수 있다.
 * 
 * 객체지향 언어에서는 유니트 생성 함수가 바로 인스턴스 생성자나 팩토리 메소드다. 따라서 map, flatMap, withFilter는
 * 바로 함수 언어의 모나드 개념을 객체지향에 적용한 것이라 볼 수 있다. for 표현식을 모나드를 위한 문법으로 볼 수 있다.
 * 
 * 스칼라에서 이 세 메소드가 존재하는 라이브러리가 있다면 for 표현식을 사용해 해당 타입의 원소를 조작할 때 더 간결한
 * 표현이 가능할 것이다.
 * 
 * 23.7 결론
 * 
 * for 표현식과 루프를 몇 가지 정해진 표준 고차 메소드들을 호출하는 것으로 변환할 수 있음을 배웠다. 그런 변환이 가능하기에
 * for 표현식의 개념이 단순히 컬렉션에 대해 이터레이션하는 것보다 더 일반적이라는 사실과, 여러분 자신의 클래스에서
 * for식을 지원하도록 구현 가능하다는 사실을 보았다.
 * 
 */
abstract class C[A] {
  def map[B](f: A => B): C[B]
  def flatMap[B](f: A => C[B]): C[B]
  def withFilter(p: A => Boolean): C[A]
  def foreach(b: A => Unit): Unit
}
object c23_i06 {
  
}