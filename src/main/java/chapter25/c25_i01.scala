package chapter25


/**
 * 25장 스칼라 컬렉션의 아키텍처
 * 
 * 참고 : http://docs.scala-lang.org/overviews/core/architecture-of-scala-collections.html
 * 
 * 이번 장에서는 프레임워크의 내부 동작에 대해 더 많이 알게 될 것이다.
 * 또한 이런 아키텍처를 사용해 프레임워크가 제공하는 엄청난 컬렉션 기능을
 * 재활용해 단 몇 줄의 코드로 직접 컬렉션을 만드는 방법도 배울 것이다.
 * 
 * 새 컬렉션 프레임워크의 주 설계 목표는 모든 연산을 가능한 한 적은 위치에 정의해서
 * 중복을 피하는 것이다. 대부분의 컬렉션 연산을 템플릿에 정의해서 개별 기반 클래스나 구현을
 * 필요에 따라 유연하게 상속할 수 있게 제공하는 것이다. 컬렉션 프레임워크를 구성하는 건축 블록을
 * 구성하는 그 밖의 클래스나 트레이트도 설명할 것이다. 또한 이런 요소들이 지원하는 컬렉션 구성 원칙도 설명한다.
 * 
 * 25.1 빌더
 * 
 * 거의 대부분 컬렉션 연산이 빌더(builder)와 순회(traversal)를 가지고 구현된다.
 * 순회는 Traversable의 foreach 메소드를 통해 처리하며,
 * 클래스  Builder의 인스턴스는 새 컬렉션 구축을 처리한다.
 * 
 * package scala.collection.mutable
 * 
 * class Builder[-Elem, +To] {
 *   def +=(elem: Elem): this.type
 *   def result(): To
 *   def clear()
 *   def mapResult(f: To => NewTo): Builder[Elem, NewTo] = ...
 * }
 * 
 * b += x를 사용해 원소 x를 빌더 b에 넣는다.
 * b +=(x, y), b ++= xs 를 버퍼에 사용, 버퍼는 빌더를 확장한 것
 */
object c25_i01 extends App {
  /*
   * 배열 버퍼 또한 빌더다. 배열 버퍼의 result()는 같은 버퍼를 반환한다.
   * 이 버퍼를 사용해 배열을 만드는 빌더를 만들고 싶다면 mapResult를 쓸 수 있다.
   * 
   * bldr 의 result를 호출하면 buf의 result를 계산한다.
   */
  import scala.collection.mutable.ArrayBuffer
  val buf = new ArrayBuffer[Int]                  //> buf  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  val bldr = buf mapResult (_.toArray)            //> bldr  : scala.collection.mutable.Builder[Int,Array[Int]] = ArrayBuffer()
  bldr result()                                   //> res0: Array[Int] = Array()
}