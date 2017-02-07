package chapter19

/**
 * 19.2 정보 은닉
 * 
 * 효율성 면에서는 좋다. 하지만 불필요하게 내부 구현을 자세히 노출하면서 달성했다는 점에서 구현이
 * 멋지지 않다.
 * 
 * Queue의 생성자는 누구나 접근이 가능하고, 생성자 파라미터인 두 리스트 중 하나는 순서를
 * 뒤집어야 한다는 점에서 큐를 표현하는 상식적인 방식과는 거리가 멀다.
 * 
 * 클라이언트 코드로부터 이 생성자를 감출 방법을 알아보자.
 */

/*
 * 1. 비공개 생성자와 팩토리 메소드
 * 
 * 자바에서는 생성자를 비공개로 만들어 숨길 수 잇으나, 스칼라에서는 명시적으로 주 생성자를 정의하지 않는다.
 * 클래스 파라미터 앞에 private 붙이면 된다. 이 경우 오직 클래스 자신과, 동반 객체에서만 접근이 가능하다.
 * 
 * 그렇다면 클라이언트가 생성자를 호출하려면, 보조 생성자를 추가하면 된다.
 * 
 * def this() = this(Nil, Nil)
 * def this(elems: T*) = this(elems.toList, Nil) // T*는 반복의 의미. 8.8절 참고
 * 
 * 동반객체 apply를 활용할 수도 있다. 동반객체 때문에 공개적인 팩토리 메소드 처럼 보인다. 사실
 * 스칼라에는 전역적으로 볼 수 있는 메소드는 없다. 각 메소드는 반드시 객체나 클래스에 속해야하며, apply
 * 메소드를 만드는 방식을 통해, 전역 메소드를 호출하는 것과 같은 사용 패턴을 지원할 수 있다.
 */

/*
 * 2. 대안: 비공개 클래스
 * 
 * 비공개 생성자와 비공개 멤버는 클래스 초기화와 내부 표현을 감추는 한 가지 방법이다. 좀 더 급진적인 방법으로는
 * 클래스 자체를 감추고, 클래스에 대한 공개 인터페이스만을 제공하는 트레이트를 외부로 노출하는 방법이 있다.
 * 
 * 개별 생성자와, 메소드를 감추는 대신, 이 방식은 구현 클래스 전체를 감춘다.
 */
trait Queue[T] {
  def head: T
  def tail: Queue[T]
  def enqueue(x: T): Queue[T]
}
object Queue {
  def apply[T](xs: T*): Queue[T] = new QueueImpl[T](xs.toList, Nil)
  
  private class QueueImpl[T] (
    private val leading: List[T],
    private val trailing: List[T]
  ) extends Queue[T] {
    def mirror = {
      if (leading.isEmpty)
        new QueueImpl(trailing.reverse, Nil)
      else
        this
    }
    def head: T = mirror.leading.head
    def tail: QueueImpl[T] = {
      val q = mirror
      new QueueImpl(q.leading.tail, q.trailing)
    }
    def enqueue(x: T) = new QueueImpl(leading, x :: trailing)
  }
}