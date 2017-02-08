package chapter19

/**
 * 19.8 상위 바운드
 * 
 * 16.6절 에서 비교함수를 첫 번째 인자로 받고, 정렬할 리스트를 두 번째 커링한 인자로 받는
 * 병합 정렬 함수를 살펴봤다. 이런 정렬 함수를 구성하는 다른 방법은 리스트에 Ordered 트레이트를
 * 섞어 넣는 것이다.
 * 
 * 12.4절에서 언급했듯이, Ordered를 클래스에 혼합하고 Ordered의 추상 메소드인 compare를
 * 정의하면 그 클래스의 인스턴스를 비교하기 위해 <,>,<=.>=를 사용할 수 있다.
 */
class Person(val firstName: String, val lastName: String) extends Ordered[Person] {
  def compare(that: Person) = {
    val lastNameComparison = lastName.compareToIgnoreCase(that.lastName)
    if (lastNameComparison != 0) lastNameComparison
    else firstName.compareToIgnoreCase(that.firstName)
  }
  override def toString = firstName +" "+ lastName
}
object c19_i08 extends App {
  val robert = new Person("Robert", "Jones")
  val sally = new Person("Sally", "Smith")
  println(robert < sally)
  
  /*
   * 새로운 정렬 함수가 인자로 받는 리스트의 타입이 Ordered를 혼합하도록 요구하기 위해, 상위 바운드(upper bound)를
   * 사용할 필요가 있다.
   * 
   * T <: Ordered[T] 라는 문법을 사용해서 타입 파라미터 T의 상위 바운드가 Ordered[T] 라는 사실을 명시할 수 있다.
   * 이는 orderedMergeSort에 전달하는 리스트 원소의 타입이 Ordered의 서브타입이어야만 한다는 사실을 의미한다.
   * 따라서 List[Person]을 넘길 수 있다. 왜냐하면 Person은 Ordered를 혼합하기 때문이다.
   */
  def orderedMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xsl, y :: ysl) =>
        if(x < y) x :: merge(xsl, ys)
        else y :: merge(xs, ysl)
    }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(orderedMergeSort(ys), orderedMergeSort(zs))
    }
  }
  
  val people = List(new Person("Larry", "Wall"),new Person("Anders", "Hejlsberg")
  ,new Person("Guido", "van Rossum"),new Person("Alan", "Kay"),new Person("srkim", "pati"))
  
  val sortedPeople = orderedMergeSort(people)
  println(sortedPeople)
  
  /*
   * 위 정렬 함수가 상위 바운드를 잘 보여주는 유용한 예이긴 하지만, 실제로 Ordered 트레이트의 이점을 가장 잘 살리면서
   * 정렬 함수를 만드는 방법은 아니다. 예를 들어 Int는 Ordered[Int]의 서브타입이 아니다.
   * 
   * 21.6절에서 암시적 파라미터(implicit parameter)와 뷰 바운드(view bound)를 사용하는
   * 더 일반적인 해결책을 제시할 것이다.
   * 
   * 19.9 결론
   * 
   * 정보은닉 기법
   *  - 비공개 생성자
   *  - 팩토리 메소드
   *  - 객체 전용 멤버
   * 
   * 데이터 타입의 변성 지정, 변위의 의미
   * 
   * 유연한 변성 표기
   *  - 메소드 타입 파라미터에 대해 사용하는 하위 바운드
   *  - 지역 필드와 메소드에 사용할 수 있는 private[this] (객체 전용 선언)
   */
}