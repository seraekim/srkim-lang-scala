package chapter25
/**
 * 25.3 새 컬렉션 통합
 * 
 * 새로운 컬렉션 클래스를 만들면서, 이미 정의되어 있는 연산이 새 타입 위에 잘 동작하도록 통합하고 싶다면?
 * 
 * 1. 시퀀스 통합
 * 
 * RNA 가닥을 표현하는 새로운 시퀀스 타입을 만들고 싶다고 하자. RNA 가닥은
 * A 아데닌,
 * C 시토신,
 * G 구아닌,
 * U 우라실 염기의 시퀀스다.
 * 
 * (참고로 DNA에서는 U가 T(티민)으로 바뀐다. 생명체를 만들어내는 설계도인 유전 정보가 이렇게 극도로 단순한 디지털 코드라는
 * 사실에 놀라지 않을 수 없다.)
 */
abstract class Base
case object A extends Base
case object C extends Base
case object G extends Base
case object U extends Base
/*
 * 각 염기는 공통 추상 클래스인 Base를 상속하는 "케이스 객체"로 만들 수 있다.
 * Base 클래스의 동반 객체에는 각 염기와 정수 0~3을 매핑해주는 두 가지 메소드가 있다.
 * toInt(Map)에 대한 역함수로 fromInt(Array)를 쓸 수 있는 이유는
 * 맵이나 배열 모두 Function1 트레이트를 상속한 함수이기 때문이다.
 */
object Base {
  val fromInt: Int => Base = Array(A, C, G, U)
  val toInt: Base => Int = Map(A -> 0, C -> 1, G -> 2, U -> 3)
}
/*
 * 다음 과제는 RNA 가닥에 대한 클래스를 정의하는 것이다. 개념적으로 RNA 가닥은 단지 Seq[Base]이다.
 * 하지만, RNA 가닥의 길이가 아주 길 수도 있으므로, 더 간단한 표현 방법을 사용할 수 있게 추가 작업을 하는 것도
 * 납득할 만한 일이다. 네 가지 염기밖에 없기 때문에, 2비트만 사용하면 염기를 구별할 수 있다.
 * 
 * 따라서 정수에는 2비트 값인 염기를 16개 저장할 수 있다. 따라서 Seq[Base]의 특화된 서브클래스를
 * 만들어서 이렇게 압축한 내부 표현을 사용하게 하는 것이 기본 아이디어다.
 * 
 * RNA1의 첫번째 인자는 압축한 RNA 정보가 들어가며, 배열의 각 원소는 16개의 염기를 표현한다.
 * 다만 배열의 마지막 원소는 16개 보다 더 적은 RNA 정보를 포함할 수도 있다. 두번째 인자인 length는
 * 배열안의 염기 개수를 지정한다. IndexedSeq를 확장한다. 이것은 두 가지 추상메소드, length와
 * apply를 지원한다. 구체적인 서브클래스는 이 두 메소드를 구현할 필요가 있다.
 * 
 * RNA1클래스에는 파라미터 필드(10.6절 참고) length가 있어서 자동으로 트레이트의 length를 구현한다.
 * 
 * 생성자가 private 이므로 new로 생성할 수 없다. 따라서 클라이언트 코드에 영향을 주지 않고 내부 로직을 변경할
 * 수 있다. 하지만 새 RNA 시퀀스를 만들 다른 방법이 필요하다. 그렇지 않다면 전체 클래스가 쓸모없어져 버린다.
 * 실제로 RNA 시퀀스를 만들 방법은 2가지가 있다. 두 가지 방법 모두 RNA1 동반 객체가 제공한다.
 * 
 * 첫 번째 방법은 어떤 염기의 시퀀스(Seq[Base])를 RNA1의 인스턴스로 바꾸는 fromSeq 메소드다.
 * 인자로 들어온 모든 시퀀스의 모든 염기를 배열에 압축해 넣는다. 그 후 RNA1의 생성자에 그 배열과,
 * 염기 사슬 길이를 넣어서 호출한다. 어떤 클래스의 동반 객체는 그 클래스의 비공개 생성자를 호출할 수 있기 때문에
 * 이것이 가능하다.
 * 
 * 두 번째 방법은 RNA1 객체의 apply 메소드를 사용하는 것이다. 임의 길이 Base를 인자로 받아서 fromSeq에
 * 넘기기만 하면 된다.
 */
final class RNA1 private (val groups: Array[Int],
    val length: Int) extends IndexedSeq[Base] {
  import RNA1._
  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)
  }
}
object RNA1 {
  // Number of bits necessary to represent group
  private val S = 2
  // Number of groups that fit in an Int
  private val N = 32 / S
  // Bitmask to isolate a group
  private val M = (1 << S) - 1 
  def fromSeq(buf: Seq[Base]): RNA1 = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
    new RNA1(groups, buf.length)
  }
  def apply(bases: Base*) = fromSeq(bases)
}

import scala.collection.IndexedSeqLike
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Builder
import scala.collection.generic.CanBuildFrom

final class RNA2 private (
  val groups: Array[Int],
  val length: Int
) extends IndexedSeq[Base] with IndexedSeqLike[Base, RNA2] {
  import RNA2._
  override def newBuilder: Builder[Base, RNA2] =
    // 배열버퍼도 시퀀스의 일종이다.
    new ArrayBuffer[Base] mapResult fromSeq
  
  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)
  }
}
object RNA2 {
  // Number of bits necessary to represent group
  private val S = 2
  // Number of groups that fit in an Int
  private val N = 32 / S
  // Bitmask to isolate a group
  private val M = (1 << S) - 1 
  def fromSeq(buf: Seq[Base]): RNA2 = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
    new RNA2(groups, buf.length)
  }
  def apply(bases: Base*) = fromSeq(bases)
}
final class RNA private (val groups: Array[Int], val length: Int) 
  extends IndexedSeq[Base] with IndexedSeqLike[Base, RNA] {
  
  import RNA._
  
  // Mandatory re-implementation of `newBuilder` in `IndexedSeq`
  override protected[this] def newBuilder: Builder[Base, RNA] = 
    RNA.newBuilder
  
  // Mandatory implementation of `apply` in `IndexedSeq`
  def apply(idx: Int): Base = {
    if (idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx / N) >> (idx % N * S) & M)
  }
  
  // Optional re-implementation of foreach, 
  // to make it more efficient.
  override def foreach[U](f: Base => U): Unit = {
    var i = 0
    var b = 0
    while (i < length) {
      b = if (i % N == 0) groups(i / N) else b >>> S
      f(Base.fromInt(b & M))
      i += 1
    }
  }
}
object RNA {
  private val S = 2            // number of bits in group
  private val M = (1 << S) - 1 // bitmask to isolate a group
  private val N = 32 / S       // number of groups in an Int
  def fromSeq(buf: Seq[Base]): RNA = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    for (i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
    new RNA(groups, buf.length)
  }
  def apply(bases: Base*) = fromSeq(bases)
  def newBuilder: Builder[Base, RNA] = 
    new ArrayBuffer mapResult fromSeq
  implicit def canBuildFrom: CanBuildFrom[RNA, Base, RNA] = 
    new CanBuildFrom[RNA, Base, RNA] {
      def apply(): Builder[Base, RNA] = newBuilder
      def apply(from: RNA): Builder[Base, RNA] = newBuilder
    }
}
object c25_i03 extends App {
  /*
   * 어떻게 돌아가는지 보자..
   */
  val xs = List(A, G, C, A)                       //> xs  : List[Product with Serializable with chapter25.Base] = List(A, G, C, A)
                                                  //| 
  RNA1.fromSeq(xs)                                //> res0: chapter25.RNA1 = RNA1(A, G, C, A)
  val rna1 = RNA1(A, U, G, G, C)                  //> rna1  : chapter25.RNA1 = RNA1(A, U, G, G, C)
  
  /*
   * 1. RNA 메소드의 결과 타입 변환
   */
  rna1.length                                     //> res1: Int = 5
  rna1.last                                       //> res2: chapter25.Base = C
  rna1.take(3)                                    //> res3: IndexedSeq[chapter25.Base] = Vector(A, U, G)
  /*
   * 첫 두 결과는 예상한대로 동작하는데 3번째는 RNA1이 아닌 Vector를 동적타입으로 보여준다.
   * 당연한 일인 것은 RNA1은 IndexedSeq를 확장한 것이 전부이고, IndexedSeq의 구현은 Vector이기 때문이다.
   * 
   * take를 override하여 해결 가능한데, 그렇게 하면 drop, filter, init은 어떻게 해야 할까? 시퀀스를 반환하는
   * 메소드는 50개 넘게 있다. 일관성을 위해서는 이들을 모두 변경해야만 한다. 갈수록 점점 더 해볼 만한 일이 아닌 것 같아
   * 보인다. 다행히, 같은 효과를 거둘 수 있는 훨씬 쉬운 방법이 있다. RNA 클래스는 IndexedSeq뿐 아니라 그 구현
   * 클래스인 IndexedSeqLike도 상속해야만 한다.
   * 
   * IndexedSeqLike의 두 번째 파라미터는 take, drop, filter, init 등의 반환타입이다.
   * 이를 위해 IndexedSeqLike자신은 newBuilder라는 추상에 의존한다. newBuilder는 올바른 빌더를 만든다.
   */
  val rna2 = RNA2(A,U,G,G,C)                      //> rna2  : chapter25.RNA2 = RNA2(A, U, G, G, C)
  rna2 take 3                                     //> res0: chapter25.RNA2 = RNA2(A, U, G)
  rna2 filter (U != _)                            //> res1: chapter25.RNA2 = RNA2(A, G, G, C)
  
  /*
   * 2. map과 친구들 다루기
   * 
   * 아직 제대로 처리하지 않은 것들이 있다. 이런 메소드는 항상 원래의 컬렉션 타입을 반환하진 않는다. 컬렉션은 같아도
   * 원소타입은 바뀔 수 있다. 가장 전형적인 예가 map이다. 이 외에도, flatMap, collect, ++ 등이 있다.
   * 문자열의 리스트에 Int의 리스트를 덧붙이면 Any의 리스트가 생긴다. 이런걸 어떻게 RNA 가닥에 적용할 수 있을까?
   * 
   * RNA 가닥에 Base 타입이 아닌 다른 원소를 덧 붙이면 더 이상 RNA 가닥이 될 수 없기이 일반적 시퀀스가 나온다.
   */
	rna2 map { case A => C case b => b }      //> res0: IndexedSeq[chapter25.Base] = Vector(C, U, G, G, C)
	rna2 ++ rna2                              //> res1: IndexedSeq[chapter25.Base] = Vector(A, U, G, G, C, A, U, G, G, C)
	rna2 map Base.toInt                       //> res2: IndexedSeq[Int] = Vector(0, 3, 2, 2, 1)
	rna2 ++ List("missing", "data")           //> res3: IndexedSeq[Object] = Vector(A, U, G, G, C, missing, data)
	/*
	 * 따라서 map과 ++의 결과는 결코 RNA 가닥이 아니다. 심지어 원소가 Base 타입인 경우에도 그렇다..
	 * 
	 * def map[B, That](f: A => B)(implicit cbf: CanBuildFrom[Repr, B, That]): That
	 * Repr 타입의 컬렉션을 받아서, B 타입의 원소를 갖는 컬렉션 That을 만드는 방법이 여기 있다 라고 말하는 것이다.
	 * RNA2 시퀀스를 만들 때 CanBuildFrom 인스턴스를 제공하지 않았기 때문에, 그 다음으로 사용 가능한
	 * CanBuildFrom 타입의 객체를 부모 트레이트인 IndexedSeq 동반 객체에서 찾았다.
	 * 
	 * 다음 구현에서 차이점은, 첫째, newBuilder구현을 RNA 클래스에서 동반 객체로 옮겼다는 점이다.
	 * 두 번째는 이제 RNA 동반 객체 안에 CanBuildFrom 타입의 새로운 암시적 값이 있다는 점이다.
	 * 
	 * 최종 RNA 클래스는 모든 컬렉션 메소드와 자연스러운 타입을 구현한다. 구현 시 몇 가지 절 차를 지켜야 한다.
	 * 어디에 newBuilder 팩토리와 canBuildFrom 암시 객체를 넣어야 할지 알아야 한다.
	 * 더 나아가 foreach 메소드 오버라이드도 중요하다. 컬렉션에 대한 루프를 구현하고, 다른 여러 컬렉션
	 * 메소드들이 foreach를 바탕으로 하기 때문이다.
	 * InexedSeq의 표준 foreach 구현은 단지 컬렉션에 apply를 호출해 0부터 컬렉션의 length - 1 까지
	 * 모든 i번째 원소를 가져온다.
	 * 
	 * 이 표준 foreach를 쓰면 RNA 모든 가닥의 원소에 대해 배열 원소를 선택해 염기를 푼다(마스크와 2진 연산). 그러나
	 * 새로 정의한 foreach는 전달받은 함수를 바로 적용하여, 배열 선택과 비트 풀기의 비용을 많이 줄인다.
	 * 
	 */
  val rna = RNA(A,U,G,G,C)                        //> rna  : chapter25.RNA = RNA(A, U, G, G, C)
	rna map { case A => C case b => b }       //> res0: chapter25.RNA = RNA(C, U, G, G, C)
	rna ++ rna                                //> res1: chapter25.RNA = RNA(A, U, G, G, C, A, U, G, G, C)
	rna map Base.toInt                        //> res2: IndexedSeq[Int] = Vector(0, 3, 2, 2, 1)
	rna ++ List("missing", "data")            //> res3: IndexedSeq[Object] = Vector(A, U, G, G, C, missing, data)
	
	/* 3. 새로운 집합과 맵 통합
	 * 
	 * 두 번째 예는 새로운 종류의 맵을 컬렉션 프레임워크에 어떻게 통합할 수 있는가를 보여준다. 기본 착상은 문자열이 키인 '패트리샤 트라이(patricia trie)'로
	 * 변경 가능한 맵을 구현하는 것이다. 영숫자 부호화한 정보를 가져오는 실용적인 알고리즘(Practical Algorithm to Retrieve Information
	 * Coded in Alphanumeric)의 줄임말이다. 핵심은 집합이나 맵을 트리로 구성하되 검색 키의 각 문자가 유일한 자식 트리를 구성하게 만드는 것이다.
	 * 
	 * 패트리샤 트라이는 아주 효율적인 검색과 변경을 제공한다. 또한 어떤 접두사를 포함하는 하위 컬렉션을 쉽게 선택할 수 있다는 멋진 특성이 있다.
	 * 
	 * PrefixMap은 키가 어떤 접두사로 시작하는 하위 맵을 선ㅌ낵할 수 있는 withPrefix 메소드를 제공한다.
	 * MapLike를 확장하는 이유는 이미 RNA 예제에서 봤듯이, filter 같은 for 변환에서 올바른 타입의 결과를 얻기 위해서다.
	 * 
	 * PrefixMap에는 newBuilder 메소드가 없다. 정의할 필요가 없는 이유는 맵이나 집합에는 MapBuilder 클래스의 인스턴스인
	 * 디폴트 빌더가 따라오기 때문이다.
	 * 
	 * 정리
	 * 
	 *  - 1. 컬렉션을 변경 가능하게 할지 여부를 결정해야 한다.
	 *  - 2. 컬렉션의 기반 트레이트를 제대로 선택해야 한다.
	 *  - 3. 대부분의 컬렉션 연산을 구현하기 위해 구현 트레이트를 제대로 선택해야 한다.
	 *  - 4. map이나 그와 비슷한 연산을 사용해 컬렉션 타입의 인스턴스를 반환해야 한다면, 암시적인 CanBuildFrom을 동반 객체에 제공해야 한다.
	 *  
	 *  25.4 결론
	 *  
	 *  스칼라 컬렉션을 만드는 방법과 새로운 종류의 컬렉션을 정의하는 방법을 살펴봤다. 스칼라가 다채로운 추상화 기능을 제공하기 때문에, 일일이
	 *  메소드를 다시 구현하지 않아도 아주 많은 메소드를 새 컬렉션에서 제공할 수 있다.
	 */
	PrefixMap.empty[String]                   //> res4: chapter25.PrefixMap[String] = Map()
	
	val pm = PrefixMap("hello" -> 5, "hi" -> 2)
                                                  //> pm  : chapter25.PrefixMap[Int] = Map(hello -> 5, hi -> 2)
	pm map { case (k, v) => (k + "!", "x" * v) }
                                                  //> res5: chapter25.PrefixMap[String] = Map(hello! -> xxxxx, hi! -> xx)
}

import collection._
  
class PrefixMap[T]
extends mutable.Map[String, T] 
   with mutable.MapLike[String, T, PrefixMap[T]] {
  // 자식 노드가 몇개 안될 것이므로, 변경 불가능 맵을 쓰는게 훨씬 성능이 낫다. 변경 가능하면 비어있는 맵도 80바이트는 차지한다.
  var suffixes: immutable.Map[Char, PrefixMap[T]] = Map.empty
  var value: Option[T] = None
  
  /*
   * 빈 문자열과 연관 있는 값을 찾으려면 트리 루트의 옵션값을 선택. 그렇지 않은 경우
   * 그 문자열의 첫 글자와 연관 있는 하위 맵을 선택한다. 그리고 하위맵이 있다면
   * 그 키의 첫 문자를 제외한 나머지를 해당 하위맵에서 검색(get)한다. flatMap의 인자는 한번이라도 실패하면
   * None을 리턴한다.
   */
  def get(s: String): Option[T] =
    if (s.isEmpty) value
    else suffixes get (s(0)) flatMap (_.get(s substring 1))
  
  /*
   * 트리를 순회하면서 트리 안에 키 문자열의 경로에 따른 하위 맵이 없는 경우 적절히 노드를 만들어 준다.
   */
  def withPrefix(s: String): PrefixMap[T] = 
    if (s.isEmpty) this
    else {
      val leading = s(0)
      suffixes get leading match {
        case None =>
          suffixes = suffixes + (leading -> empty)
        case _ =>
      }
      suffixes(leading) withPrefix (s substring 1)
    }
  
  /*
   * withPrefix를 불러서 변경할 트리 노드를 찾고, 찾은 노드의 값을 지정한 값으로 바꾼다.
   */
  override def update(s: String, elem: T) =
    withPrefix(s).value = Some(elem)
  
  /*
   * remove 메소드는 get과 아주 비슷하다. 해당 값을 갖고 있는 필드를 None으로 변경한다는 점만 다르다.
   */
  override def remove(s: String): Option[T] =
    if (s.isEmpty) { val prev = value; value = None; prev }
    else suffixes get (s(0)) flatMap (_.remove(s substring 1))
  
  /*
   * 변경 가능 맵에서 구현할 마지막  추상 메소드는 iterator이다.
   * 이 메소드는 맵에 들어있는 키/값 쌍을 돌려주는 이터레이터를 만든다. 루트 value 필드에 정의된 값
   * Some(x)가 있다면, 이터레이터는 ("",x)를 맨 첫 번째로 반환해야 한다. 더 나아가, 그 이터레이터는
   * suffixes 필드에 저장된 모든 하위 맵의 이터레이터를 순회하면서 나오는 키값 쌍의 키 문자열 앞에 문자를 추가
   * 해야 한다. m이 맵의 루트에서 chr 문자를 가지고 도달할 수 있는 하위 맵이고, (s, v)가 m.iterator가
   * 반환하는 키/값 쌍이라면, 루트의 이터레이터는 그 키/값 쌍 대신에 (chr +: s, v)를 반환해야 한다.
   */
  def iterator: Iterator[(String, T)] =
    (for (v <- value.iterator) yield ("", v)) ++
    (for ((chr, m) <- suffixes.iterator; 
          (s, v) <- m.iterator) yield (chr +: s, v))
  
  /*
   * +=, -= 메소드는 update, remove 메소드 사용.
   */
  def += (kv: (String, T)): this.type = { update(kv._1, kv._2); this }
  def -= (s: String): this.type  = { remove(s); this }
  
  /*
   * 제대로된 유형의 집합이나 맵을 만들려면 원하는 타입의 빈 집합이나 빈 맵을 만들 수 있어야 한다. empty 메소드가
   * 그런 기능을 한다.
   */
  override def empty = new PrefixMap[T]
}

/*
 * 이제 PrefixMap 동반 객체를 살펴볼 차례다. 실제로 동반 객체를 꼭 만들어야 하는 건 아니다.
 * PrefixMap 클래스만 가지고도 충분하기 때문이다. PrefixMap 객체의 목적은 편리한 팩토리 메소드를 몇 가지 제공하는
 * 것이다. 또한 타입을 더 잘 추론할 수 있게 돕는 암시적인 CanBuildFrom 객체도 그 안에 들어 있다.
 * 
 * 편의를 위한 두 메소드는 empty와 apply다. 스칼라 컬렉션 프레임워크의 다른 모든 컬렉션도 이 둘을 포함하니 여기에도 포함했다.
 * 이 두메소드를 사용하면 여타 컬렉션을 사용할 때와 마찬가지로 PrefixMap 리터럴을 사용할 수 있다.
 */
import scala.collection.mutable.{Builder, MapBuilder}
import scala.collection.generic.CanBuildFrom
  
object PrefixMap extends {
  def empty[T] = new PrefixMap[T]
  
  def apply[T](kvs: (String, T)*): PrefixMap[T] = {
    val m: PrefixMap[T] = empty
    for (kv <- kvs) m += kv
    m
  }
  
  def newBuilder[T]: Builder[(String, T), PrefixMap[T]] = 
    new MapBuilder[String, T, PrefixMap[T]](empty)
  
  /*
   * 암시적 인스턴스. map 같은 메소드가 가능한 한 가장 정확한 타입을 반환하도록 함.
   */
  implicit def canBuildFrom[T]
    : CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] = 
      new CanBuildFrom[PrefixMap[_], (String, T), PrefixMap[T]] {
        def apply(from: PrefixMap[_]) = newBuilder[T]
        def apply() = newBuilder[T]
      }
}