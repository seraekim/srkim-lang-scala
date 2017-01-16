package chapter05

/**
 * 5.3 연산자는 메소드다
 * 연산자외의 일반 메소드도 연산자 표기법을 쓸 수 있다.
 */
object c05_i03 {
  def main(args: Array[String]): Unit = {
    /*
     * Int 에는 파라미터 타입에 따라 오버로드한 + 메소드가 여럿 존재.
     */
    val sum = 1 + 2
    val sum2 = (1).+(2)
    val longSum = 1 + 2L // Long 형이 반환
    // +는 중위연산자(infix operator)
    
    val s = "Hello, world!"
    println(s indexOf 'o')
    println(s indexOf "o")
    println(s.indexOf('o'))
    
    // 여러 인자를 받는 경우 괄호로 묶어준다.
    println(s indexOf('o', 5))
    
    /*  중위 표기법 a +! b
     *  전위 표기법(단항) -7
     *  후위 표기법 7(단항) toLong
     *  
     *  -2.0 => (2.0).unary_-
     *  
     *  전위연산자는 + - ! ~ 4가지 뿐
     */
    val un = (2.0).unary_-
    
    /*
     * 스칼라에서는 메소드의 부수효과가 없다면 관례적으로 괄호를 생략한다. length / length()
     */
    println(s.toLowerCase())
    println(s toLowerCase)
  }
}