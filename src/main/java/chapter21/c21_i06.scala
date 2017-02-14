package chapter21

/**
 * 21.6 뷰 바운드
 * 
 * 앞의 maxListImpParm 에서 order 조차 지울 수 있다.
 * 
 * 컴파일러가 타입이 맞지 않는 다는 사실을 발견한다. T 타입의 x에는 > 메소드가 없다. 따라서
 * x > maxRest는 작동하지 않는다. 하지만, 컴파일러는 즉시 포기하지 않고, 암시적 값이 있는지
 * 살펴본다. 이 경우, orderer가 존재하기 때문에 orderer(x) > maxRest로 바꾼다.
 * 
 * maxList(rest)도 maxList(rest)(orderer)로 바꿀 수 있다. 
 */
object c21_i06 extends App {
  /*
   * 이제 orderer를 명시적으로 사용하지 않으므로, 아무이름으로 바꿔도 된다. converter...
   * 아무이름이나 넣어도 차이가 없다. 스칼라에서는 이런패턴이 흔하므로, 메소드 헤더를 뷰 바운드(view bound)를 사용해
   * 짧게 쓸 수 있도록 허용한다.
   */
  def maxList[T](elements: List[T])(implicit converter: T => Ordered[T]): T = {
    elements match {
      case List() => throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest)
        if (x > maxRest) x
        else maxRest
    }
  }
  /*
   * T <% Ordered[T]
   * T를 Ordered[T]로 다룰 수 있는 모든 T 타입을 사용할 수 있다는 뜻이다.
   * T가 Ordered[T]의 한 종류(서브타입)라는 상위 바운드 T <: Ordered[T]와는 다르다.
   * 
   * Int는 Ordered[Int]의 서브타입은 아니지만, Int에서 Ordered[Int]로 변환하는 암시적 값이 있는 한,
   * List[Int]를 maxList2에 넘길 수 있다.
   * 
   * 더 나아가, 타입 T가 이미 Ordered[T]라고 해도 여전히 List[Int]를 maxList에 넘길 수 있다.
   * 컴파일러는 Predef에 들어 있는 암시적인 identity 함수를 사용할 것이다.
   * 
   * implicit def identity[A](x: A): A = x
   * 이 경우 변환은 아무 일도 하지 않는다. 단지, 받은 객체를 반환할 뿐이다.
   */
  def maxList2[T <% Ordered[T]](elements: List[T]): T = {
    elements match {
      case List() => throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList2(rest)
        if (x > maxRest) x
        else maxRest
    }
  }
}