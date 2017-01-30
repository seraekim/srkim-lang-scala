package chapter15
import scala.actors.Actor
import scala.actors.Actor._
/**
 * 15.7 패턴은 어디에나
 *
 * 독립적인 match 표현식뿐 아니라, 스칼라의 여러 곳에서 패턴을 사용할 수있다.
 */
object c17_i07 extends App {

  /*
   * 변수 정의에서 패턴 사용하기
   * 
   * 튜플의 각 원소를 각 변수에 할당한다. 케이스 클래스와 사용할 때도 매우 유용.
   */
  val myTuple = (123, "abc")
  val (number, string) = myTuple
  val exp = new BinOp("*", Number(5), Number(1))
  val BinOp(op, left, right) = exp
  
  /*
   * case 시퀀스로 부분 함수 만들기
   * 
   * 케이스 시퀀스도 함수 리터럴이다.
   * 케이스의 시퀀스는 부분 함수다.
   */
  val widthDefault: Option[Int] => Int = {
    case Some(x) => x
    case None    => 0
  }
  widthDefault(Some(10))
  widthDefault(None)
  
  // 정수 리스트에서 두 번째 원소를 반환하는 부분 함수
  val second: List[Int] => Int = {
    case x :: y :: _ => y
  }
  //second(List()) //scala.MatchError
  
  /* 
   * 부분 함수에는 isDefinedAt 이라는 메소드가 있는데, 부분 함수가 어떤 값에 대해
   * 결과 값을 정의하고 있는지를 알려준다. second2함수는 최소한 2개 이상의 원소를 포함하는
   * 모든 리스트에 대해 결과를 정의한다.
   */ 
  val second2: PartialFunction[List[Int],Int] = {
    case x :: y :: _ => y
  }
  println(second2.isDefinedAt(List(5,6,7)))
  println(second2.isDefinedAt(List()))
  /*
   * 어떤 함수리터럴의 타입이 PartialFunction 이 아닌, Function 이거나 타입표기가 없으면
   * 함수 리터럴을 완전한 함수로 변환한다. 가능하면 완전한 함수를 사용하는게 좋다. 부분 함수를 사용하면
   * 컴파일러가 도와줄 수 없는 실행 시점 오류가 발생할 수 있다.
   * 
   * second2 는 다음처럼 apply, isDefinedAt 두 번 변환의 과정을 거쳐서 부분함수로 만든다.
   */
  new PartialFunction[List[Int], Int] {
    def apply(xs: List[Int]) = xs match {
      case x :: y :: _ => y
    }
    def isDefinedAt(xs: List[Int]) = xs match {
      case x :: y :: _ => true
      case _ => false
    }
  }
  /*
   * for 표현식에서 패턴 사용하기
   * 
   * for 표현식 안에 패턴을 사용할 수 있다.
   * 각 튜플은 country와 city 변수가 있는 패턴에 매치된다.
   * 
   * 다음 패턴은 매치가 결코 실패하는 일이 없기 때문에 특별하다.
   * 
   * 그러나 패턴이 매치하지 않는 경우도 마찬가지로 가능하다.
   */
  var capitals = Map("US" -> "Washington", "France" -> "Praris")
  for ((country, city) <- capitals)
    println("The capital of "+ country +" is "+city)
    
  val results = List(Some("apple"), None, Some("Orange"))
  for (Some(fruit) <- results) println(fruit)
  
}