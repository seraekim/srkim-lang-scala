package chapter24

import chapter20.Color

/*
 * 대부분의 클래스는
 * 
 *  루트 scala.collection
 *  변경 가능 scala.colleciton.mutable
 *  변경 불가능 scala.collection.immutable
 * 
 * 세 가지 버전이 존재하나, 유일한 예외는 Buffer 트레이트로 변경 가능한 컬렉션에만 있다.
 */
import scala.collection.SortedSet
import scala.collection.LinearSeq
import scala.collection.mutable.Buffer
import scala.collection.immutable.HashMap


/**
 * 24.2 컬렉션 일관성
 * 
 * 스칼라 컬렉션들은 공통점이 상당히 많다.
 */
object c24_i02 extends App {
  /*
   * 어떤 종류의 컬렉션이라도 같은 일관된 문법으로 생성할 수 있다.
   * 클래스 이름 다음에 원소를 나열하면 된다.
   */
  Traversable(1, 2, 3)
  Iterable("x", "y", "z")
  Map("x" -> 24, "y" -> 25, "z" -> 26)
  Set(Color.Red, Color.Green, Color.Blue)
  SortedSet("hello", "world")
  Buffer("x", "y", "z")
  IndexedSeq(1.0, 2.0)
  LinearSeq("a", "b", "c")
  
  // 마찬가지로 원칙을 구체적인 컬렉션 구현에도 적용할 수 있다.
  List(1, 2, 3)
  HashMap("x" -> 24, "y" -> 25, "z" -> 26)
  
  /*
   * 모든 컬렉션에 대해 toString 메소드를 호출하면 위에 쓴 생성 방법과 같은 형태의
   * 타입 이름 다음에 괄호 사이에 원소가 나열된 형태의 문자열을 얻을 수 있다.
   * 
   * 모든 컬렉션은 Traverable이 제공하는 API를 지원하지만, 반환하는 타입은
   * 루트 클래스인 Traversable이 아니고 구체적인 개별 클래스다.
   * 
   * 예를 들어, List에 대해 map을 호출하면 List가 나오고, Set에 대해
   * map을 호출하면 Set을 반환한다. 따라서 이런 메소드의 정적인 반환 타입은 꽤 정확하다.
   */
  List(1, 2, 3) map (_ + 1)
  Set(1, 2, 3) map (_ * 2)
}