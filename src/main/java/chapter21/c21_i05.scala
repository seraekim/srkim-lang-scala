package chapter21

/**
 * 21.5 암시적 파라미터
 * 
 * 컴파일러가 암시적 요소를 추가하는 다른 위치로는 인자 목록을 들 수 있다.
 * 컴파일러는 때때로 someCall(a) 호출을 someCall(a)(b)로 바꾸거나, new SomeClass(a)를
 * new SomeClass(a)(b)로 바꿔서 함수 호출을 완선하는 데 필요한, 빠진 파라미터 목록을 채워 넣어준다.
 * 만일 세 가지 파라미터를 받아야 한다면 someCall(a)(b,c,d)로 변경한다.
 */
/*
 * 사용자가 선호하는 셸 프롬프트 문자열 $, > 등을 나타내는 클래스
 */
class PreferredPrompt(val preference: String)
class PreferredDrink(val preference: String)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink) {
    println("Welcome, "+ name +". The system is ready.")
    print("But while you work, ")
    println("why not enjoy a cup of "+ drink.preference +"?")
    println(prompt.preference)
  }
}

object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master > ")
  implicit val drink = new PreferredDrink("tea")
}
object c21_i05 extends App {
  import JoesPrefs._
  // 명시적으로 지정
  val bobsPrompt = new PreferredPrompt("relax> ")
  Greeter.greet("Bob")(bobsPrompt,drink)
  
  // 빠진 파라미터 목록 채워넣기
  // Greeter.greet("Joe") // could not find implicit value for parameter prompt: chapter21.PreferredPrompt
  Greeter.greet("Joe")
  Greeter.greet("Joe")(prompt,drink)
  
  /*
   * 암시적 파라미터에 대해, 가장 자주 쓰이는 경우는, 앞쪽 파라미터 목록에서 명시적으로 사용한 인자 타입에 대한 정보를
   * 제공하고 싶은 경우이다. 하스켈의 타입 클래스와 비슷하다.
   * 
   * 상위 바운드가 있는 함수. T가 Ordered[T]의 서브타입이어야만 한다는 사실을 상위바운드를 사용해 지정.
   * 19.8절의 마지막에서 말한 것처럼 이 함수를 원소의 타입이 Ordered의 서브타입이 아닌 리스트에는 사용할 수
   * 없다는 것이 이런 접근 방법의 약점이다. 즉 정수 리스트의 최댓값은 구할 수 없다. Int는 Ordered[Int]의
   * 서브타입이 아니기 때문이다.
   * 
   * maxListUpBound를 조직적으로 만들 수 있는 더 일반적인 방법은 List[T] 인자와 별도로 T를
   * Ordered[T]로 변환하는 함수를 두 번째 인자로 추가하는 것이다.
   */
  def maxListUpBound[T <: Ordered[T]](elements: List[T]): T =
    elements match {
    case List() =>
      throw new IllegalArgumentException("empty list!")
    case List(x) => x
    case x :: rest =>
      val maxRest = maxListUpBound(rest)
      if (x > maxRest) x
      else maxRest
  }
  /*
   * 두 번째 인자인 orderer에 implicit 표시를 해서 별도의 인자 목록에 넣는다.
   * orderer 파라미터는 T들의 순서를 설명할 때 쓰인다. 타입에 대한 추가 정보를 제공하기 위해 암시적 파라미터를 사용.
   * 
   * T => Ordered[T] 타입의 암시적 파라미터인 orderer는 T에 대한 추가 정보(즉, T의 순서를 어떻게 정할 수 있는가)를 제공한다.
   * T타입은 더 앞에 있는 파라미터 목록인 elements의 타입인 List[T]에 나온 타입이다. maxListImpParm을 호출하려면
   * elements를 명시적으로 지정해야 한다. 따라서 컴파일러는 T의 타입이 어떤 것인지 컴파일 시점에 알 수 있다.
   * 따라서 T => Ordered[T]에 대한 암시적 정의가 스코프 안에 있는지도 컴파일 시 결정할 수 있다.
   * 
   * 이런 패턴은 너무나도 흔하기 때문에 표준 스칼라 라이브러리에도 여러 흔한 타입에 대해 암시적인 순서지정(orderer) 메소드를
   * 정의해뒀다.
   */
  def maxListImpParm[T](elements: List[T])(implicit orderer: T => Ordered[T]): T = {
    elements match {
      case List() => throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        println("orderer : " +orderer)
        val maxRest = maxListImpParm(rest)(orderer)
        println("orderer(x) > maxRest : "+x+" "+orderer(x) +" "+ maxRest)
        if(orderer(x) > maxRest) x
        else maxRest
    }
  }
  println(maxListImpParm(List(1, 5, 10, 3))) // Int에 대한 orderer
  println(maxListImpParm(List(1.5, 5.2, 3.14159, 10))) // Double에 대한 orderer
  println(maxListImpParm(List("one", "two", "three"))) // String에 대한 orderer
  
  /*
   * 1. 암시 파라미터에 대한 스타일 규칙
   * 
   * 스타일 규칙으로, 암시적 파라미터의 타입 안에는 일반적이지 않은 특별한 이름의 타입을 사용하는게 가장 좋다.
   */
}