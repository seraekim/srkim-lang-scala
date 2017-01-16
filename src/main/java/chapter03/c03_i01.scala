package chapter03
/**
 * 7단계: 배열에 타입 파라미터를 지정해보자
 */
object c03_i01 {
  def main(args: Array[String]): Unit = {
    val big = new java.math.BigInteger("12345")
    
    val greetStrings = new Array[String](3)
    // 타입명시
    val greetStrings2: Array[String] = new Array[String](3)
    greetStrings(0)="Hello"
    greetStrings.update(0, "Hello")
    greetStrings(1)=", "
    greetStrings.update(1, ", ")
    greetStrings(2)="world!\n"
    greetStrings.update(2, "world!\n")
    
    // to 메소드는 Int 인자 하나만 받으니 .과 괄호 생략이 가능하다.
    // 원래 모습은 (0).to(2) 와 같다.
    for (i <- 0 to 2)
      // greetStrings.apply(i)
      print(greetStrings(i))
      
    greetStrings(0) = "Hello2"
    // 값 할당에서는 update 메소드가 쓰이고 있다.
    greetStrings.update(0, "Hello2")
    
    val numNames = Array("a","b","c")
    // apply 메소드는 Array의 동반객체(companion object)에 정의.
    val numNames2 = Array.apply("a","b","c")
  }
}