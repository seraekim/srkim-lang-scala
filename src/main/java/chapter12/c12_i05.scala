package chapter12

import scala.collection.mutable.ArrayBuffer

/**
 * 12.5 트레이트를 이용해 변경 쌓아올리기
 * 
 * 지금까지 간결한 인터페이스를 푸웁한 인터페이스로 바꾸는 것을 살폈고, 이제 두 번째 용도로
 * 클래스에 쌓을 수 있는 변경을 적용하는 방법을 살펴본다.
 * 
 * 정수로 된 큐(queue)에 변경을 쌓아나가자. 선입선출(FIFO) 구조다.
 *  - Doubling: 큐의 모든 정수를 2배로
 *  - Incrementing: 모든 정수에 1을 더함
 *  - Filtering: 큐에 있는 음수를 걸러낸다.
 * 
 * 위 세 트레이트는 각각 변경을 나타낸다. 왜냐하면 전체 큐 클래스를 정의하기보다는 어떤 기존 큐 클래스의
 * 동작을 정의하기 때문이다.
 */
object c12_i05 extends App {
  val queue = new MyQueue
  
  val queue2 = new BasicIntQueue with Doubling
  queue2.put(10)
  println(queue2.get())
  
  val queue3 = (new BasicIntQueue with Incrementing with Filtering)
  queue3.put(-1);queue3.put(0);queue3.put(1)
  println(queue3.get())
  println(queue3.get())
  
  /*
   * 가장 오른쪽에 있는 트레이트부터 적용되는 것을 볼 수 있다.
   */
  val queue4 = (new BasicIntQueue with Filtering with Incrementing )
  queue4.put(-1);queue4.put(0);queue4.put(1)
  println(queue4.get())
  println(queue4.get())
  println(queue4.get())
  
}

/*
 * IntQueue 추상 클래스
 */
abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}
/*
 * ArrayBuffer로 IntQueue을 구현한 클래스
 */
class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) {buf += x}
  //def put(x: Int) { super.put(2 * x) }
  /*abstract override' modifier only allowed for members of traits
  method put in class IntQueue is accessed from super. 
  It may not be abstract unless it is overridden by 
  a member declared abstract' and override'*/
}

/*
 * 슈퍼클래스로 IntQueue를 선언하는데, 이것은 IntQueue를 상속한 클래스에만 믹스인
 * 할 수 있다는 뜻이다.
 * 트레이트의 추상메소드가 super를 호출한다. 이것이 서브클래스와 달리 성공하는 이유는 동적바인딩.
 */
trait Doubling extends IntQueue {
  // 컴파일러에게 의도적으로 super의 메소드를 호출했다는 사실을 알려주기 위해, abstract override로 표시
  abstract override def put(x: Int) { super.put(2 * x) }
}

class MyQueue extends BasicIntQueue with Doubling

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}

trait Filtering extends IntQueue { 
  abstract override def put(x: Int) { if(x >= 0) super.put(x) }
}

