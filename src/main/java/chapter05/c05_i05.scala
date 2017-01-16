package chapter05

/**
 * 5.5 관계 및 논리 연산
 */
object c05_i05 {
  def main(args: Array[String]): Unit = {
    /*
     * 관계연산
     * >, <, >=, <=
     * 단항연산자로 !
     * 
     * 논리연산
     * &&, ||
     * short circuit 연산이므로
     * 논리연산의 좌항으로만 판단이 되는 경우 우항은 연산하지 않는다.
     */
    
    println(pepper() && salt())
    // salt 만 실행하고 끝낸다.
    println(salt() && pepper())
    
    /*
     * 연산자 && / || 는 단순한 메소드라고 했는데 어떻게 두 번째 피연산자를 계산 안할까?
     * 스칼라 메소드에는 인자 계산을 미루는 기능이 있다는 것이다.
     * 
     * 이런 기능을 이름에 의한 호출 (call by name) 파라미터라 부른다. 9.5절 참고 
     */
  }
  
  def salt() = { println("salt"); false}
  def pepper() = { println("pepper"); true}
}