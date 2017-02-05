package chapter16

/**
 * 16.9 여러 리스트를 함께 처리하기
 * 
 * 튜플의 zipped로 여러리스트의 공통 연산을 일반화하고, map을 호출하면
 * 원소의 순서쌍에 대해 매핑을 수행한다.
 */
object c16_i09 extends App {
  val tuple = (List(10, 20), List(3,4,5))
  println(tuple.zipped)
  println(tuple.zipped.map(_ * _))
  
  val tuple2 = (List("abc", "de"), List(3,2))
  println(tuple2.zipped)
  println(tuple2.zipped.forall(_.length == _))
  println(tuple2.zipped.exists(_.length != _))
}