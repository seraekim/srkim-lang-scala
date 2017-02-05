package chapter16
import c16_i06.msort
/**
 * 16.10 스칼라 타입 추론 알고리즘 이해
 */
object c16_i10 extends App {
  val abcde = List('a', 'b', 'c', 'd', 'e')
  println(msort((x: Char, y: Char) => x > y)(abcde))
  println(abcde sortWith (_ > _))
  /*
   * 현재 msort는 타입추론을 할 수 없다.
   * 
   * 스칼라의 타입 추론은 흐름 기반(flow based)으로 동작한다.
   */
  println(msort[Char](_ > _)(abcde))
  //println(msort(_ > _)(abcde))
  
  // [Char] 명시까지 없애고 싶다면 List[T]이 결정되는 두번째 인자가 앞으로 오게끔하면 된다.
  def msortSwappedParam[T](xs: List[T])(less: (T, T) => Boolean): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = 
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xsl, y :: ysl) =>
          if (less(x, y)) x :: merge(xsl, ys)
          else y :: merge(xs, ysl)
      }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }
  
  /*
   * 이미 알려진 인자의 타입을 사용하게 되므로 추론이 가능해짐.
   */
  println(msortSwappedParam(abcde)(_ > _))
  /*
   * 위의 결과로 부터 알 수 있는 사실은, 라이브러리 설계 원칙을 제안할 수 있ㄷ.
   * 함수가 아닌 파라미터와, 함수인 파라미터를 받는 다형성 메소드를 설계한다면,
   * 함수 인자를 별도의 커링한 파라미터 목록으로 맨 마지막에 넣는 것이다. 이로써
   * 타입추론이 가능해진다.
   */
  
  /*
   * 프로그래머가 타입표기를 넣어야만 해결할 수 있는 캐치 22 상황 발생
   */
  def flattenRight[T](xss: List[List[T]]) = (xss :\ List[T]())(_ ::: _)
}