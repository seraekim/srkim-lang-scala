package chapter24

/**
 * 24.14 동일성
 * 
 * 컬렉션 라이브러리는 동일성과 해시에 대해 한결같은 접근 방법을 취한다. 기본적인 생각은
 * 우선 컬렉션을 집합, 맵, 시퀀스로 구분하는 것이다. 각기 다른 범주에 속하는 컬렉션은
 * 언제나 같지 않다. Set(1,2,3)은 모든 원소가 같음에도 List(1,2,3)과 같지 않다.
 * 반대로 같은 범주에 속한 두 컬렉션이 포함하는 원소가 모두 같으면 두 컬렉션은 서로 같으며
 * 그 역도 성립한다(다만 시퀀스의 경우에는 원소도 같고 순서도 같아야 한다).
 * 
 * 예를 들어 List(1,2,3) == Vector(1,2,3)이고
 * HashSet(1,2) == TreeSet(2,1)이다.
 * 
 * 동일성의 관점에서 볼 때 컬렉션이 변경 가능한지 여부는 중요하지 않다. 변경 가능의 경우
 * 동일성은 비교가 진행된 시점에 컬렉션 안에 잇는 원소에 따라 결정된다.
 */
object c24_i14 extends App {
  import collection.mutable.{HashMap, ArrayBuffer}
  
  val buf = ArrayBuffer(1,2,3)                    //> buf  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
  val map = HashMap(buf -> 3)                     //> map  : scala.collection.mutable.HashMap[scala.collection.mutable.ArrayBuffer
                                                  //| [Int],Int] = Map(ArrayBuffer(1, 2, 3) -> 3)
  
  map(buf)                                        //> res0: Int = 3
  buf(0) += 1
  map(buf)                                        //> java.util.NoSuchElementException: key not found: ArrayBuffer(2, 2, 3)
                                                  //| 	at scala.collection.MapLike$class.default(MapLike.scala:228)
                                                  //| 	at scala.collection.AbstractMap.default(Map.scala:59)
                                                  //| 	at scala.collection.mutable.HashMap.apply(HashMap.scala:65)
                                                  //| 	at test.worksheet$$anonfun$main$1.apply$mcV$sp(test.worksheet.scala:11)
                                                  //| 
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at test.worksheet$.main(test.worksheet.scala:4)
                                                  //| 	at test.worksheet.main(test.worksheet.scala)
  
  /*
   * 이 예에서 마지막 줄의 선택은 대부분 실패할 텐데, 이유는 배열 xs의 해시 코드가 끝에서 두 번째 줄의
   * 변경으로 인해 바뀔 수 있기 때문이다. 따라서 해시 코드를 가지고 검색 하는 경우 xs를 저장한 위치가
   * 아닌 다른 위치를 살펴볼 것이다.
   */
}