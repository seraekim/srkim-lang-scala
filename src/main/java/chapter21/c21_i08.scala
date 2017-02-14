package chapter21

/**
 * 21.8 암시 디버깅
 * 
 * 암시는 스칼라의 강력한 기능이다. 그러나 때때로 제대로 사용하기 힘든 경우도 있다. 이 절에서는
 * 암시를 디버깅할 때 도움이 될 힌트를 제공한다.
 * 
 * 21.9 결론
 * 
 * 스칼라에서 암시는 강력하며, 코드를 압축해주는 기능이다.
 * 
 * 너무 자주 사용하면 코드가 이해하기 어려워질 수 있으므로, 새 암시적 변환을 추가하기 전에
 * 상속, 믹스인 조합, 메소드 오버로드 등 다른 방식으로 비슷한 효과를 거둘 수 없는지 봐야 한다.
 */
object c21_i08 extends App {
  class PreferredDrink(val preference: String)
  implicit val pref = new PreferredDrink("mocha")
  def enjoy(name: String)(implicit drink: PreferredDrink) {
    print("Welcome, "+ name)
    print(". Enjoy a ")
    print(drink.preference)
    println("!")
  }
  enjoy("reader")
/*  $ scalac -Xprint:typer c21_i08.scala
[[syntax trees at end of                     typer]] // c21_i08.scala
package chapter21 {
  object c21_i08 extends AnyRef with App {
    def <init>(): chapter21.c21_i08.type = {
      c21_i08.super.<init>();
      ()
    };
    class PreferredDrink extends scala.AnyRef {
      <paramaccessor> private[this] val preference: String = _;
      <stable> <accessor> <paramaccessor> def preference: String = PreferredDrink.this.preference;
      def <init>(preference: String): chapter21.c21_i08.PreferredDrink = {
        PreferredDrink.super.<init>();
        ()
      }
    };
    private[this] val pref: chapter21.c21_i08.PreferredDrink = new c21_i08.this.PreferredDrink("mocha");
    implicit <stable> <accessor> def pref: chapter21.c21_i08.PreferredDrink = c21_i08.this.pref;
    def enjoy(name: String)(implicit drink: chapter21.c21_i08.PreferredDrink): Unit = {
      scala.Predef.print("Welcome, ".+(name));
      scala.Predef.print(". Enjoy a ");
      scala.Predef.print(drink.preference);
      scala.Predef.println("!")
    };
    c21_i08.this.enjoy("reader")(c21_i08.this.pref)
  }
}*/

}