package chapter19

/**
 * 19.7 객체의 비공개 데이터
 * 
 * Queue에서 head를 여러번 호출하면, mirror 연산이 trailing 리스트를
 * leading 리스트로 반복해서 복사하는 문제가 있는데, 복사로 인한 낭비는
 * 몇 가지 부수효과를 적절히 활용해 방지할 수 있다.
 * 
 * leading, trailing 을 var로 바꾸고, mirror 에 while을 추가하여
 * 부수효과가 있는 함수로 만들어 버렸다.
 */
trait Queue[+T] {
  def head: T
  def tail: Queue[T]
  def enqueue[U >: T](x: U): Queue[U]
}
object Queue {
  def apply[T](xs: T*): Queue[T] = new QueueImpl[T](xs.toList, Nil)
  
  private class QueueImpl[T] (
    private var leading: List[T],
    private var trailing: List[T]
  ) extends Queue[T] {
    def mirror() = {
      if (leading.isEmpty) {
        while (!trailing.isEmpty) {
          leading = trailing.head :: leading
          trailing = trailing.tail
        }
      }
    }
    def head: T = {
      mirror()
      leading.head
    }
    def tail: QueueImpl[T] = {
      mirror()
      new QueueImpl(leading.tail, trailing)
    }
    def enqueue[U >: T](x: U) = new QueueImpl[U](leading, x :: trailing)
  }
}

/*
 * 트레이트를 안쓴 버전
 * 
 * 아래 큐2에는 공변적 타입 파라미터 T와 같은 타입의 재할당 가능한 필드가 둘이나 있기 때문에
 * 스칼라 타입 검사를 통과할 수 있을까?
 * 
 * 객체 전용(object private, private[this])으로 선언된 경우만 가능하다.
 * 만약에 private 으로만 var를 선언해버리면, 컴파일러 에러가 발생한다.
 */
class Queue2[+T] private (
  private[this] var leading: List[T],
  private[this] var trailing: List[T]
) {
  def this() = this(Nil, Nil)
  def this(elems: T*) = this(elems.toList, Nil) 
  private def mirror() = {
    if (leading.isEmpty) {
      while (!trailing.isEmpty) {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }
    }
  }
  def head: T = {
    mirror()
    leading.head
  }
  def tail: Queue2[T] = {
    mirror()
    new Queue2(leading.tail, trailing)
  }
  def enqueue[U >: T](x: U) = new Queue2[U](leading, x :: trailing)
}
object c19_i07 extends App {
  val x: Queue[Any] = Queue(1,2,3)
  val x2 = x.enqueue("Abc")
  println(x2.tail.tail.tail.head)
  
  
  val x3: Queue2[Any] = new Queue2(1,2,3)
  val x4 = x3.enqueue("Abc")
  println(x4.tail.tail.tail.head)
}