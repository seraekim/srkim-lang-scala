package chapter25

/**
 * 25.2 공통 연산 한데 묶기
 * 
 * 컬렉션 라이브러리 재설계의 주 목적은 타입을 자연스럽게 만드는 동시에 구현 코드를
 * 최대한 공유하는 것이었다. 특히 스칼라의 컬렉션은 동일 결과타입(same result type) 원칙을
 * 따른다. 가능하면, 어떤 컬렉션에 대해 실행한 변환 연산의 결과가 같은 타입의 컬렉션이어야 한다는 것이다.
 * 
 * 이 절의 내용은 다른 부분보다 조금 심오하기 때문에 흡수하는데 시간이 걸릴 것이다.
 * 
 * 구현 트레이트(implementation trait)라 불리는 제네릭 빌더와 순회를 사용해 코드 중복을 줄이고
 * 동일 결과 타입 원칙을 달성한다. 이런 트레이트에는 Like라는 접미사가 붙는다.
 * 
 * IndexedSeqLike는 IndexedSeq의 구현 트레이트이고
 * Traversable의 구현 트레이트는 TraversableLike이다.
 * 
 * 일반 컬렉션에는 하나의 타입 파라미터가 있는 반면, 구현 트레이트에는 2개가 있다. 이는 컬렉션 원소의 타입과
 * 컬렉션이 표현하는 타입을 지정한다.
 * 
 * trait TraversableLike[+Elem, +Repr] { ... }
 * Repr에는 제약이 없는데, Traversable의 서브타입이 아닌 타입으로 인스턴스화도 가능하여,
 * String이나 Array 같이 컬렉션 계층구조에 참여하지 않는 클래스들까지도 컬렉션 구현 트레이트가 정의하는
 * 모든 연산을 사용할 수 있다.
 * 
 * filter는 TraversableLike 트레이트 안에 모든 컬렉션 클래스를 위해 정의되어 있다.
 * 트레이트는 두 가지 추상 메소드 newBuilder와 foreach를 정의한다.
 * 
 * newBuilder를 상둉해 Repr에 대한 빌더를 새로 만들고, 모든 원소를 foreach를 사용해 차례로 방문하며
 * 원소 x가 술어를 만족하면 빌더에 추가하고, 마지막으로 result 메소드를 호출해서 빌더에 모은 원소들을
 * Repr 컬렉션 타입의 인스턴스로 반환한다.
 * 
 * map의 경우는 복잡하다.
 * 
 */
object c25_i02 extends App {
  import scala.collection.immutable.BitSet
  val bits = BitSet(5,2,3)                        //> bits  : scala.collection.immutable.BitSet = BitSet(2, 3, 5)
  bits map (_ * 2)                                //> res0: scala.collection.immutable.BitSet = BitSet(4, 6, 10)
  bits map (_.toFloat)                            //> res1: scala.collection.immutable.SortedSet[Float] = TreeSet(2.0, 3.0, 5.0)
  val f:Set[Float] = bits map (_.toFloat)         //> f  : Set[Float] = TreeSet(2.0, 3.0, 5.0)
  /*
   * *2를 하면 비트집합이 나오지만, toFloat를 하면, 더 일반적인 집합인 SortedSet가 된다. 비트 집합에는 Int만 들어갈 수 있기 때문에
   * Float가 들어가야 하는 이 결과 집합은 결코 비트 집합일 수 없다. 어떻게 스칼라는 이렇게 유연하게 처리할 수 있을까?
   */
  Map("a" -> 1, "b" -> 2) map { case (x, y) => (y, x) }
                                                  //> res0: scala.collection.immutable.Map[Int,String] = Map(1 -> a, 2 -> b)
  Map("a" -> 1, "b" -> 2) map { case (x, y) => y }//> res1: scala.collection.immutable.Iterable[Int] = List(1, 2)
  /*
   * 두번째 Map의 경우 그대로 Map을 만들 순 없지만 여전히 Map의 서브트레이트인 Iterable을 만들 수는 있다.
   * 
   * 왜 map을 제한해서 항상 같은 종류의 컬렉션을 반환하게 만들지 않느냐 할 수 있는데, 그런식의 제한은 객체지향 모델링 관점에서 바람직하지도 않고
   * 리스코프 치환 법칙을 어기기 때문에 올바르지도 않다.
   * 
   * Map은 ITerable이기도 하므로 Iterable에서 할 수 있는 일은 Map에서도 할 수 있어야 한다.
   * 
   * 스칼라는 이 문제를 오버로드를 사용해 풀었다. 다만, 자바에서 가져온 간단한 오버로드(유연하지 못한)가 아닌,
   * 암시적 파라미터가 제공하는 더 시스템적인 방식을 사용했다.
   * 
   * TraversableLike의 map 구현
   * def map[B, That](p: Elem => B)(implicat bf: CanBuildFrom[This, B, That]): That = {
   *   val b = bf(this)
   *   for (x <- this) b += f(x)
   *   b.result
   * }
   * 
   * 위 구현은 filter 구현과 아주 비슷한데, 큰 차이는 추상 메소드 newBuilder가 아닌,
   * 암시적 파라미터로 넘어오는 CanBuildFrom 타입의 빌더 팩토리(builder factory)를 사용한다는 점에 있다.
   * 
   * trait CanBuildFrom[-From, -Elem, +To[ {
   *   // 새로운 빌더를 만든다.
   *   def apply(from: From): Builder[Elem, To]
   * }
   * 
   * From - 빌더 팩토리를 적용할 타입
   * Elem - 만들 컬렉션의 원소 타입
   * To - 만들 컬렉션의 타입
   * 
   * BitSet의 동반객체에는 CanBuildFrom[BitSet, Int, BitSet]이 들어 있을 것이다.
   * 이는 map 연산 적용 시, 만들려는 결과 컬렉션의 원소 타입이 Int라면 새 BitSet을 만들 수 있다는 뜻이다.
   * 만일 만족 못한다면, mutable.Set의 동반 객체에 있는 다른 값을 찾아볼 것이다.
   * 
   * CanBuildFrom[Set[_], A, Set[A]]
   * 임의의 집합(Set[_])에 대해 연산을 수행할 때, A 타입과 관계없이 다시 Set을 만들 수 있다는 의미다.
   * 난해한 컬렉션 연산에도 올바르게 정적인 타입을 정의할 수 있는데, 동적인 경우는 어떨까?
   */
  
  val xs2 = List(1,2,3)                           //> xs2  : List[Int] = List(1, 2, 3)
  val ys2 = xs2 map (x => x * x)                  //> ys2  : List[Int] = List(1, 4, 9)
  
  val xs: Iterable[Int] = List(1,2,3)             //> xs  : Iterable[Int] = List(1, 2, 3)
  val ys = xs map (x => x * x)                    //> ys  : Iterable[Int] = List(1, 4, 9)
  /*
   * List -> Iterable 의 map 결과가 List이며 ys에 다시 Iterable로 할당된다.
   * 위 ys의 정적 타입은 Iterable인데, 동적 타입은 List이다. CanBuildFrom의 apply 메소드에는
   * 원래의 컬렉션이 인자로 넘어간다. 제네릭 순회 가능 클래스의 대부분의 빌더 팩토리는 이 호출을 컬렉션의 genericBuilder에 있는 메소드에 넘긴다.
   * 이 메소드는 실제로 그 메소드를 정의한 컬렉션의 빌더를 호출한다. 따라서 이런 제약을 만족하는 가장 좋은 동적인 타입을 가져온다.
   */
  
}