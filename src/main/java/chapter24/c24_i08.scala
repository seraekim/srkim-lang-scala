package chapter24

import scala.collection.mutable.{Map, HashMap, SynchronizedMap, Set, HashSet, SynchronizedSet}
import java.util.concurrent.ConcurrentHashMap

/**
 * 24.8 동기화한 집합과 맵
 * 
 * 1.1절에서 스레드 안전한 맵이 필요하다면 SynchronizedMap 트레이트를 원하는 특정 맵 구현에 믹스인 하면
 * 된다고 이야기 했다.
 * 
 * http://stackoverflow.com/questions/18660769/best-practices-for-mixing-in-scala-concurrent-map
 * 위 링크를 보면, SynchronizedMap 이 deprecated 되었고, 어떻게 대체코드를 작성하는지, 직접 이 트레이트를 구현한 사람이 설명해준다.
 * 2.11.0 이후에 해당하는 내용인데, 교재는 2.8을 기준으로 만들어 졌으니 그대로 설명을 이어가겠다.
 * 
 * 맵에 특정 키에 대한 값을 물어봤는데 그 키에 대한 매핑을 찾지 못한다면 기본적으로는 NoSuchElementException이 발생한다. 하지만 새 멤버
 * 클래스를 정의하면서 default메소드를 오버라이드 하면, 존재하지 않는 키에 대해 default 값을 돌려준다.
 */
object MapMaker {
  def makeMap: Map[String, String] = {
    new HashMap[String, String] with SynchronizedMap[String, String] {
      /*
       * 스칼라 컴파일러는 SynchronizedMap 트레이트를 믹스인한 복합적인 HashMap의 서브클래스를 생성하고, 그 클래스의 인스턴스를 만든다.
       * 합성한 클래스는 다음 코드 때문에 default 라는 메소드를 오버라이드 할 것이다.
       */
      override def default(key: String) = "Why do you want to know?"
    }
  }
}
object c24_i08 extends App {
  /*
   * makeMap 메소드가 돌려주는 변경 기능 맵에 SynchronizedMap 트레이트를 믹스인했기 때문에, 이를 여러 스레드에서 한꺼번에
   * 사용할 수 있다. 맵 접근은 각각 동기화된다.
   */
  val capital = MapMaker.makeMap
  capital ++= List("US" -> "Washington")
  /*
   * 2.11.x 버전
   */
  import scala.collection.convert.decorateAsScala._
  val capital2: scala.collection.concurrent.Map[String, String] = new ConcurrentHashMap().asScala
  capital2 ++= List("US" -> "Washington")
  /*
   * 동기적 집합도 동기적 맵을 만든 방법과 비슷하게 만들 수 있다.
   */
  val synchroSet = new HashSet[Int] with SynchronizedSet[Int]
  val synchroSet2: scala.collection.concurrent.Map[Int, Unit] = new ConcurrentHashMap().asScala
  
}