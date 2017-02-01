package chapter15

import chapter10.Element
import chapter10.Element.elem

/**
 * 15.8 좀 더 큰 예제
 *
 * 산술식을 2차원으로 배열해 보여주는 형식화 클래스(formatter class)를 작성하자.
 * 
 * 15.9 결론
 * 
 * 스칼라 패턴 매치는 15장 상상 이상이다. 클래스 필드를 외부에 노출하고 싶지 않다면
 * 26장의 extractor를 사용할 수 있다.
 * 
 */
class ExprFormatter {
  // 연산자를 우선순위가 커지는 순서로 나열한 ㅐㅂ열
  private val opGrp =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%"))
  // 연산자와 우선순위 간의 맵
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGrp.length
        op <- opGrp(i)
        // op -> i 라는 연관관계는 (op, i) 라는 튜플과 같다.
      } yield op -> i
    assocs.toMap
  }
  // 이항연산자보다 단항연산자의 순위가 높다.
  private val unaryPrecedence = opGrp.length

  // 나눗셈 연산자의 경우 수직 레이아웃을 적용해야 하므로 특별한 값으로 -1을 배정
  private val fractionPrecedence = -1

  // 비공개 format 메소드는 표현식의 종류에 따라 패턴 매치를 통해 작업을 수행한다. 총 5가지 케이스가 있다.
  private def format(e: Expr, enclPrec: Int): Element = {
    e match {
      // 표현식이 변수라면, 형식화한 결과는 변수의 이름으로부터 만들어낸 레이아웃 요소다.
      case Var(name) => elem(name)
      // 표현식이 수이면,.0 접미사 지워서 부동소수점 출력을 보기 좋게 한다.
      case Number(num) =>
        def stripDot(s: String) = {
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s
        }
        elem(stripDot(num.toString))
      // 단항연산자 Unop(op, arg)이면, 가장 높은 우선순위이며 arg를 재귀적으로 다시 넘긴다.
      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)
      // 표현식이 분수라면.. left, right 을 위 + 라인 + 아래로 형식화
      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        // line 너비는 left나 right를 형식화한 결과(위, 아래) 중 더 너비가 긴 쪽에 맞춘다.
        val frac = top above line above bot
        // 이 변수가 다른 분수의 일부가 아니라면, frac을 최종 결과로 반환, 
        if (enclPrec != fractionPrecedence) frac
        // 다른 분수의 일부라면, 양쪽에 공백을 추가 (a/(b/c), (a/b)/c 구분을 위한 것)
        else elem(" ") beside frac beside elem(" ")
      // 나눗셈 외 모든 이항 연산에 대해 이 패턴 적용. 나눗셈 이후 적용되니 나눗셈은 unreachable.
      case BinOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" " + op + " ") beside r
        if (enclPrec <= opPrec) oper
        // 연산자 우선순위 비교하여 소괄호 적용
        else elem("(") beside oper beside elem(")")
    }
  }
  def format(e: Expr): Element = format(e, 0)
}
object c15_i08 extends App {
  val f = new ExprFormatter
  val e1 = BinOp("*", BinOp("/", Number(1), Number(2)), BinOp("+", Var("x"), Number(1)))
  val e2 = BinOp("+", BinOp("/", Var("x"), Number(2)), BinOp("/", Number(1.5), Var("x")))
  val e3 = BinOp("/", e1, e2)
  val e4 = BinOp("/", Var("a"), BinOp("/", Var("x"), Number(1)))
  def show(e: Expr) = println(f.format(e) + "\n\n")
  val arr = Array(e1, e2, e3, e4)
  //for (e <- arr) show(e)
  arr.foreach(show)
/*  
1          
- * (x + 1)
2          


x   1.5
- + ---
2    x 


1          
- * (x + 1)
2          
-----------
  x   1.5  
  - + ---  
  2    x   


 a 
---
 x 
 - 
 1 

*/

}