package chapter05

/**
 * 5.6 비트 연산
 */
object c05_i06 {
  def main(args: Array[String]): Unit = {
    /*
     * 비트곱&, 비트합|, 배타합^(두비트가 같으면 0, 다르면 1)
     * 단항 비트반전 연산자(~)
     */
    
    // 0001 & 0010 = 0000 = 0
    println(1 & 2)
    // 0001 | 0010 = 0011 = 3
    println(1 | 2)
    // 0001 ^ 0011 = 0010 = 2
    println(1 ^ 3)
    // 0001 반전 = 1111 ~ 11110 = -2
    println(~1)
    
    /*
     * 왼쪽<<, 오른쪽>>, 부호 없는 오른쪽>>> 시프트
     */
    // Int 는 32비트
    // 가장왼쪽이 1(부호 -1) 이므로 1을 채워가며 시프트.. 따라서 처음과 같은 결과
    println(-1 >> 31) // -1
    println(-1 >>> 31) // 1
    println(1 << 2) // 4
  }
}