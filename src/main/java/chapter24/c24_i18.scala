package chapter24

/**
 * 24.18 자바와 스칼라 컬렉션 변환
 * 
 * 자바도 풍부한 컬렉션 라이브러리를 제공.
 * 스칼라와 유사한 점이 많고, 두 라이브러리 모두 이터레이터, 이터러블, 집합, 맵, 시퀀스가
 * 들어 있다. 자바 컬렉션을 마치 스칼라 컬렉션처럼 접근하고 싶을 수도 있다. 또는 반대의 경우도 말이다.
 * 이런 양방향 변환은 아주 쉽다.
 * 
 * Iterator       <-> java.util.Iterator
 * Iterator       <-> java.util.Enumeration
 * Iterator       <-> java.lang.Iterable
 * Iterator       <-> java.util.Collection
 * mutable.Buffer <-> java.util.List
 * mutable.Set    <-> java.util.Set
 * mutable.Map    <-> java.util.Map
 * 
 */
object c24_i18 extends App {
  /*
   * 이런 변환을 사용하려면, 임포트 하기만 하면 된다..
   */
  import collection.JavaConversions._
  import collection.mutable._
  
  val jul: java.util.List[Int] = ArrayBuffer(1,2,3)
                                                  //> jul  : java.util.List[Int] = [1, 2, 3]
  val buf: Seq[Int] = jul                         //> buf  : scala.collection.mutable.Seq[Int] = ArrayBuffer(1, 2, 3)
  val m: java.util.Map[String, Int] = HashMap("abc" -> 1, "hello" ->2)
                                                  //> m  : java.util.Map[String,Int] = {abc=1, hello=2}
  /*
   * 내부적으로 이모든 연산은 래퍼 객체를 만들기에, 컬렉션을 따로 복사하는 일은 없다. 또한 자바를 스칼라로 바꾼 뒤
   * 다시 자바로 왕복 변환해도, 원래 시작했던 원래 컬렉션을 그대로 얻을 수 있다.
   *
   * 다음과 같은 스칼라 컬렉션은 자바 타입으로만 변환할 수 있고, 반대 방향의 변환은 제공하지 않는다.
   *
   * Seq -> java.util.List
   * mutable.Seq -> java.util.List
   * Set -> java.util.Set
   * Map -> java.util.Map
   *
   * 자바 타입에서는 변경 가능한 컬렉션과 그렇지 않은 컬렉션을 구분할 수 없다.
   */
  val jul2: java.util.List[Int] = List(1,2,3)     //> jul2  : java.util.List[Int] = [1, 2, 3]
  // val 로부터 만들어 진 것이므로..
  jul2.add(7)                                     //> java.lang.UnsupportedOperationException
                                                  //| 	at java.util.AbstractList.add(Unknown Source)
                                                  //| 	at java.util.AbstractList.add(Unknown Source)
                                                  //| 	at test.c24_i111$$anonfun$main$1.apply$mcV$sp(test.c24_i111.scala:24)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at test.c24_i111$.main(test.c24_i111.scala:5)
                                                  //| 	at test.c24_i111.main(test.c24_i111.scala)
}