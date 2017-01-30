package chapter15

/*
 * 15.5 봉인한 클래스
 * 
 * 어떻게 해야 모든 가능성을 다 처리했다는 안전한 느낌을 받을 수 있는가?
 * 
 */
sealed abstract class Expr2
case class Var2(name: String) extends Expr2
case class Number2(num: Double) extends Expr2
case class UnOp2(operator: String, arg: Expr2) extends Expr2
case class BinOp2(operator: String, left: Expr2, right: Expr2) extends Expr2
object c15_i05 extends App {
  // 
  // 
  // 
  /*
   * match may not be exhaustive
   * 이 경고는 잠재적인 실행 시점 오류의 근원을 알려준다.
   * 그러나 코드 흐름 안에서, 결구 두가지 대안 이외의 일이 없다고 생각해보자..
   * 컴파일러를 조용하게 하려면 match의 셀렉터에 @unchecked를 써 
   * 디폴트 케이스는 어차피 논리적으로 unreachable이다.
   */
  def describe(e: Expr2): String = (e: @unchecked) match {
    case Number2(_) => "a number"
    case Var2(_)    => "a variable"
  }
}