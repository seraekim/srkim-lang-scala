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
 */
object c24_i04 {
  
}