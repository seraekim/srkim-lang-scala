package chapter03

/**
 * 10단계: 집합과 맵을 써보자
 * 
 * 배열은 항상 변경 가능, 리스트는 항상 변경 불가능
 * 그러나 집합과 맵에 대해서는 변경 가능과 변경 불가능 모두 제공
 * 변경 가능, 변경불가능은 다른 패키지에 있고, 해당 트레이트를 확장한다.
 */
object c03_i04 {
  def main(args: Array[String]): Unit = {
    // 변경 불가능 객체는 재할당이 불가능하니 var로 선언한다.
    var jetSet = Set("Boeing", "Airbus") //scala.collection.immutable.apply() 호출
    /*
     * 변경 불가능한 Set[String] 추론
     * +=는 변경 가능만 제공
     * a += b 는 a = a + b 로 해석 됨.
     * 새로운 집합으로 재 할당
     * 
     * 변경가능한 집함을 사용하고 싶다면
     * import scala.collection.mutable.Set
     */
    jetSet += "Lear"
    println(jetSet.contains("Cessna"))
    
    // 변경가능객체를 val로 할당, 변경가능 객체는 재 할당이 필요 없기 때문에 val로 해야 한다.
    val movieSet = scala.collection.mutable.Set("Hitch","Poltergeist")
    movieSet += "Shrek"
    movieSet.+=("A")
    println(movieSet)
    
    // HashMap 도 변경가능이 존재. scala.collection.mutable.HashMap
    // 타입[]을 반드시 지정해야 한다. 왜냐하면 팩토리 메소드에 아무 값도 전달하지 않아서 컴파일러가 맵의 인자 타입을 추론할 수 없기 때문이다.
    val treasureMap = scala.collection.mutable.Map[Int, String]()
    val treasureMap2: scala.collection.mutable.Map[Int, String] = scala.collection.mutable.Map()
    // a -> b 는 원소가 2개인 튜플을 만든다.
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += ((3).->("Dig."))
    println(treasureMap(2))
    
    // 변경 불가능이 디폴트이다.
    val romanNumeral = Map(1->"I",2->"II",5->"V")
    println(romanNumeral(5))
  }
}