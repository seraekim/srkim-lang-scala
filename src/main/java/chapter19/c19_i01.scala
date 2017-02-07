package chapter19

/**
 * 19장 타입 파라미터화
 * 
 * 타입 파라미터화와 13장의 정보 은닉을 함께 보여줄 텐데, 이는 정보 은닉을 통해
 * 더 일반적인 타입 파라미터화 변성(variance)표기를 소개할 수 있기 때문이다.
 * 
 * 타입 파라미터화를 사용하면 제네릭 클래스와 트레이트를 쓸 수 있다. 예를 들어 집합은 제네릭이며
 * 타입 파라미터를 받기 때문에 타입이 Set[T]이다. 자바(raw type)와 달리 스칼라는 
 * 타입파라미터를 반드시 표기해야만 한다.
 * 
 * 타입 변성은 파라미터 타입 간의 상속 관계를 지정한다.
 * 예를 들면, Set[String]이 Set[AnyRef]의 하위 집합인자와 같은 것이 변성에 의해 결정된다.
 * 
 * 19.1 함수형 큐
 * 
 * 함수형 큐는 다음 세 연산을 제공하는 데이터 구조다.
 * 
 *  - head: 큐의 첫 원소를 반환한다.
 *  - tail: 큐의 첫 원소를 제외한 나머지를 반환한다.
 *  - enqueue: 인자로 주어진 원소를 큐의 맨 뒤에 추가한 새로운 큐를 반환한다.
 *  
 * 변경 가능한 큐와 달리, 함수형 큐는 원소를 추가해도 내용을 바꾸지 않는다. 대신, 새로운 원소를 추가한
 * 새 큐를 반환한다.
 * 
 * 변경불가능한 List와 비슷한데, List는 :: 연산을 통해 앞쪽으로 늘어나는 반면, 큐는 enqueue를 통해
 * 뒤쪽으로 늘어난다.
 * 
 * 어떻게 효율적으로 구현할까? 함수형 큐가, 명령형 큐보다 기본적인 부가비용이 더 커서는 안 된다.
 * 즉, head, tail, enqueue가 모두 상수 시간이 걸려야 한다.
 */
class SlowAppendQueue[T](elems: List[T]) {
  def head = elems.head
  def tail = new SlowAppendQueue(elems.tail)
  /*
   * 원소 개수에 비례한 시간이 걸림, 상수 시간이 걸려야 하는데 이를 위해 뒤집어 버린다면?
   */
  def enqueue(x: T) = new SlowAppendQueue(elems ::: List(x))
}
class SlowHeadQueue[T](smele: List[T]) {
  /*
   * 뒤집게 되면 head와 tail이 상수시간이 아니게 된다.
   */
  def head = smele.last
  def tail = new SlowHeadQueue(smele.init)
  def enqueue(x: T) = new SlowHeadQueue(x :: smele)
}
/*
 * 위 두 예제를 보면, 세 연산을 모두 상수 시간에 달성하는 구현을 만들기가 쉽지 않아 보인다.
 * 그런데 두 방식을 합하면 상당히 근접해진다.
 * 
   * mirror 연산은 큐의 원소 개수에 비례한 시간이 걸리지만,
   * 그 다음 n번의 tail 연산은 상수 시간에 실행 가능하므로,
   * mirror 의 복잡도를 n으로 나누게 되면 시간 복잡도는 상수이며, 명령형 큐와 같다.
   * 
   * 한번 mirror가 불리면, 그 다음 불려질 일이 없으므로, n번의 tail 연산을 저금한 셈이다.
   * 
 *  - 점근적인 동작에 대한 것이며, 복잡도는 상당히 달라질 수 있다.
 *  - head, tail, enqueue를 거의 비슷한 빈도로 호출한다는 가정에 있기에 가능한건데,
 *  head를 많이 호출한다면, 매번 head 호출에 비싼 비용 들여 mirror를 호출하는 문제가 있다.
 *  이 문제는 뒤에가서 해결 한다.
 */
/*
class Queue[T] (
  private val leading: List[T],
  private val trailing: List[T]
) {
  private def mirror = {
    if (leading.isEmpty)
      new Queue(trailing.reverse, Nil)
    else
      this
  }
  def head = mirror.leading.head
  def tail = {
    val q = mirror
    new Queue(q.leading.tail, q.trailing)
  }
  def enqueue(x: T) = new Queue(leading, x :: trailing)
}
*/


object c19_i01 {
  
}