package chapter19

/**
 * 19.5 하위 바운드
 * 
 * Queue[T] 를 Queue[+T]로 만들 수 없는데, 그 이유는 T는 enqueue 메소드의 파라미터 타입인데,
 * 그 위치는 부정적인 위치이기 때문이다.
 * 
 * enqueue를 다형성으로 더 일반화하고, 타입 파라미터에 하위바운드(lower bound)를 사용하는 것이다.
 * 
 * U >: T
 * T를 U의 하위바운드로 지정. 따라서 U는 T의 슈퍼타입이어야만 한다.
 * 즉 U가 Any 일 때, T는 String, Int 등 다 된다는 것이다.
 * 
 * 이는 타입 위주 설계(type-driven design)의 좋은 예다.
 * 
 * 지금껏 설명한 것은 자바의 와일드카드에서 볼 수 있는 사용 위치 변성(use-site variance) 보다
 * 선언 위치(declaration site) 변성을 선호하는 주된 이유다. 변성처리는 어려운 일이며, 사용자들은
 * 실수하기 쉽기 때문에, 와일드 카드나 제네릭은 너무 복잡하다는 인상만 받고, 포기해버리곤 한다.
 * 
 * 스칼라의 컴파일러는, 사용자가 제공하고자 하는 메소드가 실제로 사용가능한지를 다시 한번 확인해준다.
 */
/*
trait Queue[+T] {
  def head: T
  def tail: Queue[T]
  def enqueue[U >: T](x: U): Queue[U]
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
    def enqueue[U >: T](x: U) = new QueueImpl[U](leading, x :: trailing)
  }
}
*/
object c19_i05 extends App {
  /*
  val x: Queue[Any] = Queue(1,2,3)
  val x2 = x.enqueue("Abc")
  println(x2.tail.tail.tail.head)
  */
}