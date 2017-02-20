package chapter23

/**
 * 23.5 역방향 적용
 * 
 * for 표현식을 map, flatMap, withFilter 고차 함수 호출로 변환할 수 있는 사실을 보았는데,
 * 그 반대 방향도 마찬가지로 가능하다.
 */
object c23_i05 {
  def map[A, B](xs: List[A], f: A => B): List[B] = for (x <- xs) yield f(x)
  def flatMap[A, B](xs: List[A], f: A => List[B]): List[B] = for (x <- xs; y <- f(x)) yield y
  def filter[A](xs: List[A], p: A => Boolean): List[A] = for (x <- xs if p(x)) yield x
}