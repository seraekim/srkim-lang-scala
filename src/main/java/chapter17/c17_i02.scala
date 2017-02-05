package chapter17

/**
 * 17.2 집합과 맵
 */
object c17_i02 extends App {

  /*
   * type 키워드는 변경 불가능한 집합과 맵 트레이트의 전체 이름에 대한 별명으로 Set과 Map을
   * 정의한다.
   * 
   * object Predef {
   * type Map[A, +B] = immutable.Map[A, B]
   * type Set[A]     = immutable.Set[A]
   */

  /*
   * 1. 집합의 사용
   * 
   * [ !,.]+ 로 단어를 분리하여 넣자
   */
  val text = "See Spot run. Run, Spot. Run!"
  val wordsArray = text.split("[ !,.]+")

  import scala.collection.mutable.{ Set => MutableSet }
  val words = MutableSet.empty[String]

  println(wordsArray.toList)
  for (word <- wordsArray)
    words += word.toLowerCase // mutable 이므로.. 가능..
  println(words)

  // immutable set
  println("immutable set")
  val nums = Set(1, 2, 3)
  println(nums + 5)
  println(nums - 3)
  println(nums ++ List(5, 6))
  println(nums -- List(1, 2))
  println(nums & Set(1, 3, 5, 7)) // 교집합

  // mutable set
  println("mutable set")
  println(words -= "run")
  println(words ++= List("do", "re", "mi"))
  println(words --= List("do", "re"))
  println(words.clear)

  /*
   * 2. 맵의 사용
   * 
   * 맵은 컬렉션의 각 원소 값에 대해 연관 관계를 만든다.
   */
  import scala.collection.mutable.{ Map => MutableMap }

  def countWords(text: String) = {
    val counts = MutableMap.empty[String, Int]
    for (rawWord <- text.split("[ ,!.]+")) {
      val word = rawWord.toLowerCase
      val oldCount = {
        if (counts.contains(word)) counts(word)
        else 0
      }
      counts += (word -> (oldCount + 1))
    }
    counts
  }

  println(countWords(text))

  // immutable map
  println("immutable map")
  val numMap = Map("i" -> 1, "ii" -> 2)
  println(numMap + ("vi" -> 6))
  println(numMap - "ii")
  println(numMap ++ List("iii" -> 3, "v" -> 5))
  println(numMap -- List("i", "ii"))
  println(numMap.size)
  println(numMap.contains("ii"))
  println(numMap("ii"))
  println(numMap.keys) // 모든 키를 반환
  println(numMap.keySet) // 모든 키를 집합으로 반환 Set(i, ii)
  println(numMap.values) // 모든 값을 반환, 정수 1, 2가 들어 있는 iterable 반환
  println(numMap.isEmpty)

  // mutable map
  println("mutable map")
  val mutableMap = MutableMap.empty[String, Int]
  println(mutableMap += ("one" -> 1))
  println(mutableMap -= "one")
  println(mutableMap ++= List("one" -> 1, "two" -> 2, "three" -> 3))
  println(mutableMap --= List("one", "two"))

  /*
   * 3. 디폴트 집합과 맵
   * 
   * 일반적으로 해시 테이블을 통한 빠른 검색 알고리즘을 사용한다. 변경가능한 Set, Map은 내부적으로
   * HashSet, HashMap을 반환한다.
   * 
   * 변경불가능한 Set, Map의 경우, 5 이상일 때만 Hash를 구현하고 그 이하에서는
   * Set1,2,3,4의 구현체가 존재한다. 이는 성능을 최대화하기 위한 조치다.
   * 
   * EmptySet에 원소 하나 추가하면 Set1을 반환. 하며 추가 제거여부에 따라서
   * Set1 <-> Set2 <-> Set3 <-> Set4 <-> HashSet
   */

  /*
   * 4. 정렬된 집합과 맵
   * 
   * 때때로 정해진 순서대로 원소를 반환하는 이터레이터를 제공하는 맵이나 집합이 필요할 수도 있다.
   * 이를 위해 SortedSet, SortedMap 트레이트가 있다. 구현은 각각 TreeSet, TreeMap.
   * 두 클래스 모두 원소/키를 다루기 위해 적흑 트리(red-black tree)를 사용한다.
   * 순서는 Ordered 트레이트를 따라 결정한다. 이 클래스들은 불변인 것만 있다. 
   */
  import scala.collection.immutable.TreeSet
  val ts = TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
  println(ts)
  val ts2 = TreeSet(ts.toSeq: _*)(ts.ordering.reverse)
  println(ts2)
  val cs = TreeSet('f', 'u', 'n')
  println(cs)

  import scala.collection.immutable.TreeMap
  var tm = TreeMap(3 -> 'x', 1 -> 'x', 4 -> 'x')
  tm += (2 -> 'x')
  println(tm)

  val tm2 = TreeMap(tm.toSeq: _*)(tm.ordering.reverse)
  println(tm2)

}