package chapter24

/**
 * 24.7 맵
 * 
 * 맵은 키와 값의 쌍(mapping, association)의 Iterable이다. 21.4절에서 설명했듯이 스칼라의 Pref 클래스에는
 * key => value를 (key, value) 쌍 대신 사용할 수 있는 암시적 변환이 들어 있다.
 * Map("x" -> 24) 는 Map(("x", 24))와 같다.
 * 
 *  - 검색 연산: apply, get, getOrElse, contains, isDefinedAt. 맵을 키에서 값으로 가는 부분 함수
 *  로 만든다. m get key 연산은 key와 관련된 값이 있다면 Some으로 묶어서 반환한다. 없다면 None을 반환한다. Option
 *  으로 감싸지 않고 바로 반환하는 apply 메소드도 있다. 만약 키가 맵 안에 들어 있찌 않으면 예외가 발생한다.
 *  
 *  - 추가와 변경 연산: +, ++, updated.
 *  - 제거 연산: -, --.
 *  - 하위 컬렉션 생성 메소드: key, keySet, keysIterator, valuesIterator, values. 맵의 키나
 *  값들을 다양한 형태로 돌려준다.
 *  - 변환 연산: filterKeys, mapValues. 기존 맵의 바인딩을 필터링하거나 변환한 새 맵을 만든다.
 *  
 * 맵에 대한 추가와 변경 연산은 집합에서와 마찬가지다. 집합처럼 변경 가능한 맵도 +, -, updated 같은 파괴적이지 않은
 * 추가/변경 연산을 제공하지만, 변경 가능 맵을 복사해야하기 때문에 자주 사용하지는 않는다.
 * 변경 가능한 맵은 보통 m(k) = value, m += (key -> value)를 사용해 그 자리에서 변경한다.
 * 또한 m put (key, value) 라는 변형도 있다.연관 값이 맵에 있다면 Option에 담아 반환하고 없다면 None을 반환한다.
 * 
 * 맵을 캐시로 사용하는 경우 getOrElseUpdate가 유용하다.
 */
object c24_i07 extends App {
  /*
   * 다시 f를 호출하면, 항상 같은 결과가 나올 것이다. 이런 경우 캐시를 해두면 더 시간을 절약할 수 있다.
   */
  def f(x: String) = {
    println("taking my time."); Thread.sleep(100)
    x.reverse
  }
  /*
   * 이제 더 효율적인 f를 캐시를 사용해 다음과 같이 만들 수 있다.
   * getOrElseUpdate에 대한 인자가 이름에 의한 호출임에 유의하라. 따라서
   * getOrElseUpdate가 실제 두 번째 인자의 값을 쓰는 경우에만 f("abc")를 호출한다.
   * f 가 호출되는 경우는 s가 캐시가 안된 경우만 해당한다.
   */
  val cache = collection.mutable.Map[String, String]()
  def cachedF(s: String) = cache.getOrElseUpdate(s, f(s))
  /*
   * 직접 구현해 본다면.. 코드가 더 필요하다.
   */
  def cachedF2(arg: String) = cache get arg match {
    case Some(result) => result
    case None =>
      val result = f(arg)
      cache(arg) = result
      result
  }
}