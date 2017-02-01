package chapter16

/**
 * 16.6 List 클래스의 1차 메소드
 *
 * List 클래스의 first order 메소드를 설명할 것.
 * 어떤 메소드가 함수를 인자로 받지 않는다면, 그 메소드를 1차 메소드라 부른다.
 */
object c16_i06 extends App {
  /*
   * 1. 두 리스트 연결하기
   * 
   * :: 와 달리 :::의 두 인자는 리스트다.
   */
  List() ::: List(1, 2, 3)

  /*
   * 2. 분할 정복 원칙
   * 
   * 패턴매치를 사용해 :::를 직접 구현할 수도 있다.
   * append 구현을 디자인하기 위해, 분할 정복(divide and conquer) 디자인 기법을 기억하면
   * 도움이 된다. 패턴 매치를 통해 더 간단한 경우의 리스트로 쪼개는 것이 분할이며, 분할 각 부분이 빈
   * 리스트가 아니라면, 동일한 알고리즘을 재귀적으로 호출해 결과를 생성하는데 이것이 정복이다.
   */
  def append[T](xs: List[T], ys: List[T]): List[T] = {
    xs match {
      // xs 리스트가 비었을 때, ys
      case List()   => ys
      case x :: xsl => x :: append(xsl, ys)
    }
  }
  println(append(List(2, 4), List(1, 5)))

  /*
   * 3. 리스트 길이 구하기: length
   * 
   * 배열과 달리 리스트의 length는 비교적 비싼 연산이다. 리스트 끝을 찾기 위해 전체 리스트를 순회한다.
   * 따라서 리스트 원소 개수만큼 시간이 걸린다. 따라서 xs.isEmpty 를 xs.length == 0으로
   * 바꾸는 건 좋지 못한 생각.
   */
  val abcde = List('a', 'b', 'c', 'd', 'e')
  abcde.length

  /*
   * 4. 리스트 양 끝에 접근하기: init와 last
   * 
   * head, tail은 쌍대성(dual,거울관계)연산이 있다. 각각 last, init이다.
   * 상수 시간 복잡도인 head 및 tail과 달리 init, last는 결과를 계싼하기 위해 전체 리스트를
   * 순회해야 하므로 리스트 길이만큼 시간이 걸리며 되도록이면 head, tail을 쓰는게 좋다.
   */
  abcde.last
  abcde.init

  /*
   * 5. 리스트 뒤집기: reverse
   * 
   * 어떤 알고리즘이 계산중에 리스트 끝을 자주 참조하면, 먼저 리스트의 방향을 거꾸로 뒤집은 다음, 결과를
   * 가지고 작업하는 편이 때때로 더 낫다.
   * 
   * 다른 모든 리스트와 마찬가지로, 새로운 리스트를 생성해낸다.
   * 
   *  - xs.reverse.reverse == xs
   *  - xs.reverse.init == xs.tail.reverse
   *  - xs.reverse.head == xs.init.reverse
   *  - xs.reverse.head == xs.last
   *  - xs.reverse.last = xs.head
   */
  abcde.reverse
  abcde // 리버스 후 변경 불가능이므로 그대로 임

  /*
   * reverse는 :::연결을 사용해 구현할 수 있다.
   * 
   * xs의 길이를 n이라 할 때 rev를 n번 재귀호출 하므로
   * n(n+1)/2 의 복잡도를 가진다. 변경가능한 표준 연결 리스트를 뒤집을 때 선형 복잡도인 걸 볼 때
   * 실망스럽지만 16.7절에서 향상시키는 법을 본다.
   */
  def rev[T](xs: List[T]): List[T] = xs match {
    case List()   => xs
    case x :: xsl => rev(xsl) ::: List(x)
  }

  /*
   * 6. 접두사와 접미사: drop, take, splitAt
   * 
   * tail, init을 일반화한 것.
   * 
   * xs take n : xs(0) ~ xs(n-1) 가져옴
   * xs drop n : xs(n) ~ xs(끝) 가져옴
   * xs splitAt n : xs take n, xs drop n, 단 두 리스트가 들어있는 튜플을 반환한다.
   */
  abcde take 2
  abcde drop 2
  abcde splitAt 2

  /*
   * 7. 원소 선택: apply와 indices
   * 
   * 원소를 임의의 인덱스로 선택하는 것은 apply 메소드를 통해 지원하지만, 배열에 비해 리스트에서는
   * 자주 사용하지는 않는다.
   * 
   * 인덱스로 원소를 선택하는 연산을 리스트에서 덜 사용하는 이유는 xs(n)에 인덱스 n의 값에 비례한
   * 시간이 걸리기 때문이다.
   * xs apply n : (xs drop n).head
   * 
   * indices 메소드는 리스트에서 유효한 모든 인덱스의 리스트를 반환한다.
   */
  abcde apply 2 //스칼라에서는 이렇게 사용하는 경우가 드물다.
  abcde(2) //스칼라에서는 이렇게 사용하는 경우가 드물다.
  println(abcde.indices)

  /*
   * 8. 리스트의 리스트를 한 리스트로 반듯하게 만들기: flatten
   */
  println(List(List(5, 3), List(), List(2, 6)).flatten)
  println("apples" :: ("oranges" :: ("pears" :: Nil)).flatten)

  /*
   * 9. 두 리스트를 순서쌍으로 묶기: zip과 unzip
   * 
   * zip 연산자는 두 리스트를 인자로 받아서 순서쌍의 리스트를 만든다.
   * 인자의 길이가 다르면 긴쪽의 남는 원소는 버린다.
   * 
   */
  val zipped = abcde.indices zip abcde
  // 한 가지, 유용한 특별한 경우는 어떤 리스트의 각 원소를 그 인덱스와 함께 순서쌍으로 묶는 것.
  println(abcde.zipWithIndex)

  // 모든 List(()) 는, unzip을 통해 (List())로 바꿀 수 있다.
  println(zipped unzip)

  /*
   * 10. 리스트 출력하기: toString, mkString
   * 
   * mkString, addString 은 List의 슈퍼트레이트인 Traversable로부터 상속한 메소드이므로
   * 다른 모든 컬렉션에도 사용 가능하다.
   */
  println(abcde mkString ("[", ",", "]"))
  println(abcde mkString ("", ",", ""))
  // toString과 같은 결과
  println(abcde mkString ("List(", ", ", ")"))
  println(abcde mkString "")
  println(abcde mkString)

  // 생성한 문자열을 반환하지 않고 scala.StringBuilder 객체에 추가할 수도 있다.
  val buf = new StringBuilder
  abcde addString (buf, "(", ";", ")")
  
  /*
   * 11. 리스트 변환하기: iterator, toArray, copyToArray
   * 
   * 배열의 평면적인 세상과 리스트의 재귖거인 세상 사이에서 데이터를 변환하고 싶다면...
   */
  val arr = abcde.toArray
  arr.toList
  
  // copyToArray는 리스트 원소를 어떤 배열의 특정 지점부터 연속적으로 복사한다.
  // xs coptyToArray (arr, start), xs 리스트의 모든 원소를 배열 arr의 start 지점에 복사
  val arr2 = new Array[Int](10)
  List(1, 2, 3) copyToArray (arr2, 3)
  arr2.foreach(print)
  
  // 마지막으로 리스트의 원소를 이터레이터를 사용해 접근할 필요가 있다면
  val it = abcde.iterator
  println(it.next)
  println(it.next)
  
  /*
   * 12. 예제: 병합 정렬
   * 
   * 앞에서 설명했던 삽입 정렬은 작성하긴 간단하고 쉽지만, 그렇게 효율적이진 않다. 평균 복잡도가 입력 리스트
   * 길이의 제곱에 비례하기 때문입니다. 더 효율적인 알고리즘은 병합정렬이다.
   * 
   * 이 예제는 분할 정복 전략과 커링을 보여주는 또 다른 예다.
   * 
   * 우선 리스트에 원소가 없거나 1개뿐이라면 이미 정렬된 상태니 그대로 반환한다. 그보다 더 긴 리스트는
   * 2개의 더 작은 하위 리스트로 분할한다. 이때 각 리스트는 원래리스트의 원소를 절반씩 포함한다.
   * 정렬하고 나면, 그 두 정렬 결과 리스트를 merge 연산을 사용해 병합한다.
   * 
   * msort의 복잡도는 (n log(n))이다. n은 입력 리스트의 길이다.
   */
  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = 
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xsl, y :: ysl) =>
          if (less(x, y)) x :: merge(xsl, ys)
          else y :: merge(xs, ysl)
      }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }
  
  println(msort((x: Int, y: Int) => x < y)(List(5,7,1,3)))
  // msort 함수는 9.3절에서 설명했던 커링 개념의 고전적인 예다. 함수로 쉽게 특화 가능.
  val intSort = msort((x: Int, y: Int) => x < y) _
  val reverseIntSort = msort((x: Int, y: Int) => x > y) _
  
}