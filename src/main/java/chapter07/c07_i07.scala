package chapter07

/**
 * 7.7 변수 스코프
 *
 * 스칼라의 스코프 규칙은 자바와 거의 동일하다.
 * 스칼라에서는 내부 스코프에 동일한 이름의 변수를 정의해도 된다는 점이다.
 * 중괄호를 사용하면 새로운 스코프가 생기고,
 * 그 안의 것들은 중괄호를 닫으면 모두 스코프를 벗어나 사라진다.
 */
object c07_i07 extends App {
  // 절차형 스타일인데 다음절에서 함수형으로 리팩토링 할 것이다.
  def printMultiTable() {
    var i = 1
    // i만이 스코프 안에 있다.
    while (i <= 10) {
      var j = 1
      // 여기서는 i와 j가 스코프 안에 있다
      while (j <= 10) {
        val prod = (i * j).toString
        //i, j, prod가 스코프 안에 있다
        var k = prod.length
        // i, j, prod, k가 보두 스코프 안에 있다.
        while (k < 4) {
          print(" ")
          k += 1
        }
        print(prod)
        j += 1
      }
      println()
      i += 1
    }
  }
  printMultiTable
  
  // 자바와 달리 다른 스코프라면 변수의 이름이 같아도 동작한다.
  val a = 1; // 중괄호 등장으로 세미콜론 추론매커니즘 동작 안함
  {
    // 안쪽의 변수가 바깥 스코프의 변수를 가렸다(shadow)고 표현한다.
    val a = 2
    println(a)
  }
  println(a)
  
  // 인터프리터에서는 동일한 이름의 변수를 마음대로 재사용할 수 있다.
  // 이것이 가능한 이유는 인터프리터가 작성한 구문마다 새로운 스코프를 만들기 때문이다.
  /*
   * scala> val a = 1
   * scala> val a = 2
   * scala> println(a)
   * 
   * 는
   * 
   * val a = 1;
   * {
   *   val a = 2;
   *   {
   *     println(a)
   *   }
   * } 와 같다.
   * 
   * 혼란을 줄 수 있으므로 되도록이면 안쪽 스코프의 변수명은 새롭게 짓는 것이 좋다.
   * 
   */
}