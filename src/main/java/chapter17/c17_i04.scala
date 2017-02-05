package chapter17


/**
 * 17.4 컬렉션 초기화
 * 
 * 컬렉션을 초기화하고 생성하는 일반적인 방법은 초기 원소를 컬렉션 동반 객체의 팩토리 메소드에
 * 넘기는 것이다.
 */
object c17_i04 extends App {
  import scala.collection.mutable
  val stuff = mutable.Set(42) // 타입추론
  //stuff += "abracadabra" //type mismatch; found : String("abracadabra") required: Int
  
  val stuff2 = mutable.Set[Any](42)
  stuff2 += "abracadabra"
  
  import scala.collection.immutable.TreeSet
  val colors = List("b", "y", "r", "g")
  //val treeSet = TreeSet(colors) // ◾No implicit Ordering defined for List[String].
  val treeSet = TreeSet[String]() ++ colors
  println(treeSet)
  
  /*
   * 1. 배열이나 리스트로 바꾸기
   * 
   * 한 가지 명심해야할 사항은, 리스트나 배열 변환 시 컬렉션의 모든 원소를 복사해야 하기 때문에
   * 컬렉션 크기가 아주 큰 경우 느릴 수도 있다.
   */
  treeSet.toList
  treeSet.toArray
  
  /*
   * 2. 변경 가능한 집합(맵)과 변경 불가능한 집합(맵) 사이의 변환
   * 
   * empty 컬렉션을 만들고 ++ / ++= 연산자를 활용해서 원소 추가하면 그만이다.
   */
  val mutaSet = mutable.Set.empty ++ treeSet // ++, ++= 차이 없다
  println(mutaSet)
  val immutaSet = Set.empty ++ mutaSet
  println(immutaSet)
  
  
  val muta = mutable.Map("i" -> 1, "ii"->2)
  println(muta)
  val immu = Map.empty ++ muta
  println(immu)
}