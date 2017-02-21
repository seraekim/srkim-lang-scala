package chapter24

/**
 * 24.4 Iterable 트레이트
 * 
 * 이 트레이트의 모든 메소드는 추상 메소드 iterator를 기반으로 한다.
 * iterator는 컬렉션의 원소를 하나하나 돌려 준다. Traversable에 있는
 * foreach 메소드를 iterator를 사용해 정의한다.
 * 
 * def foreach[U](f: Elem => U): Unit = {
 *   val it = iterator
 *   while (it.hasNext) f(it.next())
 * }
 * 
 * foreach가 Traversable의 모든 연산을 만드는 기반임을 생각하면, 그 성능이 매우 중요하다.
 * 
 * Iterable에는 iterator를 반환하는 메소드가 두 가지 더 있다. grouped, sliding 이다.
 * 
 * 1. Traversable과 Iterable이 각각 존재하는 이유는 무엇인가?
 * 
 * 때때로 iterator를 구현하는 것보다 foreach 메소드의 구현을 효율적으로 하는 편이 쉬운 경우가
 * 있기 때문이다.
 * 
 * 잎(leaf, terminal node)에 정수 원소를 저장하는 이진 트리의 클래스 계층을 원한다고 해보자.
 * 한눈에 봤을 때는, Iterable 규현이 foreach 해법보다 어려운 것 같지 않다. 하지만
 * 이터레이터를 서로 결합하는 연산인 ++에는 효율성 문제가 있다.
 * 
 * 트리에 백만개의 원소가 있다면 foreach는 2백만 단계, 이터레이터는 2천만 단계를 거쳐야 한다.
 * 
 * 2. Iterable의 하위 분류
 * 
 * 상속계층에서 Iterable 아래에는 Seq, Set, Map 이렇게 세 가지 트레이트가 있다. 이 세
 * 트레이트의 공통점은 PartialFunction(부분함수, 15.7절 참고)을 구현해서 apply와 isDefinedAt 메소드를
 * 제공한다는 점이다. 하지만 각 트레이트가 PartialFunction을 구현하는 방식은 각기 다르다.
 * 
 * 시퀀스의 apply는 위치를 인덱스로 사용한다. 인덱스는 항상 0부터 시작한다.
 * 
 * Seq(1,2,3)(1) == 2
 * Map('a' -> 1, 'b' -> 10)('b') == 10
 */
sealed abstract class Tree extends Traversable[Int] {
  def foreach[U](f: Int => U) = this match {
    case Node(elem) => f(elem)
    case Branch(l, r) => l foreach f; r foreach f
  }
}
/*
sealed abstract class Tree2 extends Iterable[Int] {
  def iterator: Iterator[Int] = this match {
    case Node(elem) => Iterator.single(elem)
    case Branch(l, r) => l.iterator ++ r.iterator
  }
}
*/
case class Branch(left: Tree, right: Tree) extends Tree
case class Node(elem: Int) extends Tree
object c24_i04 extends App {
  val xs = List(1,2,3,4,5)
  val git = xs grouped 3
  git.next() // List(1,2,3)
  git.next() // List(4,5)
  
  val sit = xs sliding 3
  sit.next() // List(1,2,3)
  sit.next() // List(2,3,4)
  sit.next() // List(3,4,5)
}