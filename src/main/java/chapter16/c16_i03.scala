package chapter16

/**
 * 16.3 리스트 생성
 *
 * 모든 리스트는 Nil / :: (콘즈cons) 가지고 만들 수 있다.
 * Nil은 빈 리스트, 중위연산자 ::는 리스트의 앞에 원소를 추가 x :: xs
 */
object c16_i03 extends App {
  // 앞의 리스트들은 다음과 같이 바꿔 표현이 가능하다.
  val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
  val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
  val diag3 = 
    (1 :: (0 :: (0 :: Nil))) :: 
    (1 :: (0 :: (0 :: Nil))) :: 
    (1 :: (0 :: (0 :: Nil))) :: Nil
  val empty = Nil
  // :: 연산자는 오른쪽 결합 법칙을 적용하므로 괄호를 없앨 수 있다.
  val nums2 = 1 :: 2 :: 3 :: 4 :: Nil
}