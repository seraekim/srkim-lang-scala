package chapter15

import scala.reflect.runtime.{ universe => ru }
import scala.reflect.runtime.universe.{ typeTag, TypeTag }

/**
 * 15장 케이스 클래스와 패턴 매치
 *
 * 쌍둥이 같은 구성요소로서, 일반적이며 캡슐화하지 않은 데이터 구조를 지원
 * 특히 재귀적 데이터에 유용. 케이스 클래스는 얼개 코드를 많이 사용하지 않고도
 * 객체에 대한 패턴 매치를 하기 위한 스칼라 구성요소
 *
 * 15.1 간단한 예
 *
 * 산술 표현식을 조작하는 라이브러리를 만들고 싶다고 치자.
 */
/*
 * 추상 기본 클래스 하나와, 그 클래스를 상속한 4개의 서브클래스 포함.
 * 각 서브클래스 앞엔 case가 있는데 이것이 케이스 클래스.
 * 
 * case 수식자는 스칼라 컴파일러에게 해당 클래스에 문법적으로 편리한 기능 몇가지를 추가하라 지시
 *  - 클래스 이름과 같은 이름의 팩토리 메소드 추가. 따라서 new Var("x") 대신에 Var("x")
 *    로 객체를 생성. 팩토리 메소드는 중첩해서 객체 생성 시 특히 좋다. 산술식 구조를 한번에 본다.
 *  - 각 클래스파라미터에 암시적으로 val 접두사를 붙이기 때문에, 클래스의 필드가 된다.
 *  - 자연스러운 toString, hashCode, equals 구현
 *    케이스클래스와 그 안의 모든 파라미터로 이뤄진 전체 트리를 재귀적으로 문자열로 만들고, 해시계산하고
 *    비교한다.
 *  - 케이스 클래스에서 일부를 변경한 복사본을 생성하는 copy 메소드를 추가. 기존 인스턴스에서 하나
 *    이상의 속성을 바꾼 새로운 인스턴스 생성 시 매우 유용. 이 copy 메소드는 디폴트와 이름 있는
 *    파라미터(8.8절)를 제공.
 *  - 마지막으로 가장 큰 장점은, 패턴 매치를 지원한다.
 */
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
object c15_i01 extends App {
  val v = Var("x")
  val op = BinOp("+", Number(1), v)
  println(v.name)
  println(op.right)
  println(op.right == Var("x"))
  val op2 = op.copy(operator = "-")
  println(op)
  println(op2)

  /*
   * 상수 패턴
   * 변수 패턴
   * 와일드카드 패턴
   * 생성자 패턴
   * 
   * switch와 match의 비교
   *  - match는 표현식이다. 값을 내놓는다.
   *  - 대안(case)은 다음 대안으로 빠지지 않는다.
   *  - 매치에 실패하는 경우 MatcherError 예외 발생. 따라서 아무일 안해도 디폴트 케이스가 필요하다.
   * 
   */
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e))  => e
    case BinOp("+", e, Number(0)) => e
    case BinOp("*", e, Number(1)) => e
    // 디폴트 케이스는 무조건 필요
    case _                        => expr
  }

  println(simplifyTop(UnOp("-", UnOp("+", Var("x")))))
  println(simplifyTop(UnOp("-", UnOp("-", Var("x")))))
  println(simplifyTop(BinOp("*", Number(10), Number(1))))

  /*
   * 15.2 패턴의 종류
   */

  /*
   * 와일드카드패턴
   * 단지 타입이 BinOp인지 아닌지 알거라면 
   */
  def wildcard(expr: Expr) = expr match {
    case BinOp(_, _, _) => println(expr + "is a binary operation")
    case _              => println("It's something else")
  }
  wildcard(op)
  wildcard(v)

  /*
   * 상수 패턴
   * 어떤 종류의 리터럴이든 상수로 사용
   */
  def describe(x: Any) = x match {
    case 5       => "five"
    case true    => "truth"
    case "hello" => "hi!"
    case Nil     => "the empty list"
    case _       => "something else"
  }
  println(describe(5 > 3))

  /*
   * 변수 패턴
   * 
   * 디폴트 매치에 쓰인게 변수패턴이다.
   */
  def variable(expr: Int) = expr match {
    case 0             => "zero"
    case somethingElse => "not zero: " + somethingElse
  }
  println(variable(1))

  import math.{ E, Pi }

  /*
   * E와 Pi는 당연히 매치가 안된다.. 컴파일러는 scala.math로 부터 임포트한 상수인지 알 수 있나?
   * E와 Pi는 Nil과 같이, 상수패턴인데 기호로 이뤄진 경우이다.
   */
  val res = E match {
    case Pi => "strange math? Pi = " + Pi
    case _  => "OK"
  }
  println(res)

  /*
   * 소문자로 시작하는 간단한 이름은, 컴파일러가 변수 패턴으로 취급한다.
   * pi로 대신쓰면 변수 패턴으로 인식된 것임을 알 수 있으며, 변수는  모든 입력과 매치할 수 있기 때문에
   * 컴파일러는 디폴트 케이스를 허락지 않는다.
   */
  val pi = Pi
  val res1 = E match {
    case pi => "strange math? Pi = " + pi
    //case _ => "OK"
  }
  println(res1)

  /*
   * 그렇다면 소문자로 시작하는 상수는 어떻게 처리해야 하는가?
   * 짧은 이름이 아니라 긴 이름(this.pi 등) 쓰면 된다. 그게 힘들다면 역따옴표를 쓰면 된다.
   */
  val res2 = E match {
    case `pi` => "strange math? Pi = " + pi
    case _    => "OK"
  }
  println(res2)

  /*
   * 생성자 패턴
   * 
   * 디폴트 매치에 쓰인게 변수패턴이다.
   * BinOp("+", e, Number(0))를 검사할 때, 클래스 타입, 파라미터, 
   * 그리고 그 파라미터의 파라미터까지.. deep match
   */
  def constr(expr: Expr) = expr match {
    case BinOp("+", e, Number(0)) => println("a deep match")
    case _                        => "OK"
  }
  constr(BinOp("+", Number(-1), Number(0)))

  /*
   * 시퀀스 패턴
   * 
   * 패턴 내무에 원하는 개수만큼 원소 명시
   */
  def seq(expr: List[Int]) = expr match {
    case List(0, _, _) => println("found it")
    case _             =>
  }
  /*
   * 길이가 정해지지 않은..
   * 
   * 끝에 _* 써주면 된다. 시작은 0인 원소이면서, 길이가 0인 경우도 체크한다.
   */
  def seq2(expr: List[Int]) = expr match {
    case List(0, _*) => println("found it")
    case _           =>
  }

  /*
   * 튜플 패턴
   */
  def tupleDemo(expr: Any) =
    expr match {
      case (a, b, c) => println("matched " + a + b + c)
      case _         =>
    }
  tupleDemo(("a ", 3, "-tuple"))

  /*
   * 타입 지정 패턴
   */
  def generalSize(x: Any) = x match {
    case s: String    => s.length
    case m: Map[_, _] => m.size
    case _            => -1
  }
  println(generalSize(Map(1 -> 'a', 2 -> 'b')))

  /*
   * 타입을 검사한 다음 타입을 cast 하는 방법이 있으나 다소 장황하다.
   * 이 방법이 옳지 않기 때문에 이렇게 한거라고 저자는 밝힌다.
   * 
   * 위의 패턴 매치를 한번 쓰면 한번에 해결이 되는 것.
   */
  def generalSize2(x: Any) = {
    if (x.isInstanceOf[String]) {
      val s = x.asInstanceOf[String]
      s.length
    }
  }
  println(generalSize2("abc"))

  def getTypeTag[T: TypeTag](t: T) = typeTag[T].tpe
  def getTypeTag[T: TypeTag] = ru.typeOf[T]

  // context bound T: ru.TypeTag cause typeOf 
  // requires implicit parameter
  def getStrFromOpt[T: TypeTag](opt: Option[T]): String =
    opt match {
      case Some(s: String) => s
      case Some(i: Int) => i.toString()
      case Some(l: Long) => l.toString()
      case Some(m: Map[String, Int] @unchecked) if getTypeTag[T] =:= getTypeTag[Map[String, Int]] => "Int Map"
      case Some(m: Map[String, String] @unchecked) if getTypeTag[T] =:= getTypeTag[Map[String, String]] => "String Map"
      case _ => ""
    }

  // "Int Map"
  println(getStrFromOpt(Some(Map("a" -> 2, "b" -> 3))))
  // "String Map"
  println(getStrFromOpt(Some(Map("a" -> "2", "b" -> "3"))))

  /*
   * 타입소거
   * 
   * 스칼라는 자바와 마찬가지로 제네릭에서 타입소거 모델을 사용한다. 이는 런타임이 타입 인자에 대한
   * 정보를 유지하지 않는다는 뜻이다. 실행 시 어떤  타입으로 생성되는지 알 턱이 없다.
   * 
   * 타입소거의 유일한 예외는 배열이다. 배열은 자바뿐 아니라 스칼라에서도 특별취급 한다. 배열은
   * 원소타입과 함께 값을 저장한다.
   */
  def isIntIntMap(x: Any) = x match {
    case m: Map[Int, Int] => true
    case _                => false
  }
  println(isIntIntMap(Map(1 -> 1)))
  def isStrArr(x: Any) = x match {
    case a: Array[String] => "yes"
    case _                => "no"
  }
  println(isStrArr(Array(1)))

  /*
   * 변수 바인딩
   * 
   * 다른 패턴에 변수를 추가할 수도 있는데, 단순히 변수 이름 다음에 @ 기호를 넣고 패턴 쓰면 된다.
   */
  def variBinding(expr: Expr) = expr match {
    //case UnOp("abs", UnOp("-", e))  => e
    case UnOp("abs", e @ UnOp("abs", _)) => e
    case _                               =>
  }

  /*
   * 15.3 패턴 가드
   * 
   * 아래 매치는 실패한다. 스칼라가 패턴을 선형 패턴으로 제한하기 때문이다.
   * 즉 어떤 패턴 변수가 한 패턴 안에 오직 한번만 나타나야 한다.
   * x is already defined as value x
   * 
   * 이럴 때 패턴가드를 사용한다.
   */
  //BinOp("+", var("x"), var("x")) 을  BinOp("*", var("x"), Number(2)) 러
  /*def simplifyAdd(e: Expr) = e match {
    case BinOp("+", x, x) => BinOp("*", x, Number(2))
  }*/
  def simplifyAdd(e: Expr) = e match {
    case BinOp("+", x, y) if x == y =>
      BinOp("*", x, Number(2))
    case _ => e
  }

  /*
   * 15.4 패턴 겹침
   * 
   * case 문의 순서가 중요하다. 모든 경우와 매치하는 케이스문을 위로 올리면 unreachable이라면서
   * 컴파일이 실패한다.
   */
  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e))  => simplifyAll(e) // -를 두번 적용
    case BinOp("+", e, Number(0)) => simplifyAll(e) // 0은 +연산의 항등원
    case BinOp("*", e, Number(1)) => simplifyAll(e) // 1은 *연산의 항등원
    // 모든 단항 연산과 매치, 변수 패턴 op, e
    case UnOp(op, e)              => UnOp(op, simplifyAll(e))
    // 모든 이항 연산 처리
    case BinOp(op, l, r)          => BinOp(op, simplifyAll(l), simplifyAll(r))
    case _                        => expr
  }
}