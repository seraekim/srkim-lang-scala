package chapter13

/**
 * 13.5 접근 수식자
 * 
 * 대강 자바와 비슷하나, 몇 가지 중요한 차이가 있다.
 */

/*
 * 비공개 멤버
 */
class Outer {
  class Inner {
    private def f() { println("f") }
    class InnerMost {
      f() // 문제없음
    }
  }
  // 자바였다면, 외부 클래스가 자신의 내부클래스에도 접근 가능하다.
  //(new Inner).f() // 오류: f를 찾을 수 없음
}
/*
 * 보호 멤버
 * 
 * 자바보다 약간 더 제한적이다.
 * 자바에서는 서브클래스 및 같은 패키지의 클래스들도 접근이 가능했지만,
 * 스칼라에서는 서브클래스에서만 접근할 수 있다.
 */
package p {
  class Super {
    protected def f() { println("f") }
  }
  class Sub extends Super {
    f()
  }
  class Other {
    // 자바라면 같은 패키지 내라서 접근 가능
    //(new Super).f() // 오류
  }
}
/*
 * 공개 멤버
 * 
 * 공개 멤버를 위한 수식자는 없다. 어디에서나 접근 가능.
 */

/*
 * 보호 스코프
 * 
 * 접근 수식자의 의미를 지정자로 확장. private[X] 처럼
 * 접근이 x까지 비공개/보호라는 뜻. X는 그 접근 수식자를 둘러싸고 있는 패키지나 클래스
 * 또는 싱글톤 객체를 가리킨다. 자바에서 표현 불가능한 접근 제어 규칙도 표현 가능하다.
 */
package bobsrockets {
  package navigation {
    /*
     * private[bobsrockets]란 bobsrockets 패키지 내부에 있는 모든
     * 객체와 클래스에서 Navigator2에 접근이 가능하다는 것.
     * 특히 Vehicle 내부에서도 접근이 가능하다.
     * 
     * 반대로 말하자면, bobsrockets밖의 모든 코드는 접근 못한다.
     */
    private[bobsrockets] class Navigator2 {
      // []를 통해서 자바와 같은 접근 범위를 지니는 protected 정의가 가능
      protected[navigation] def useStarChart() {}
      class LegOfJorney {
        // [C]에서 C가 가장 바깥쪽에서 둘러싸는 클래스라면
        // 자바의 private과  동일하다.
        private[Navigator2] val distance = 100
      }
      /*
       * 마지막으로 스칼라에는 private 보다 더 제한적인 접근 수식자도 있다.
       * private[this] 라고 하면, 그 정의를 포함하는 객체 내부에서만 접근이 가능하다.
       * 이를 객체 전용(object-private)이라 한다.
       */
      private[this] var speed = 200
    }
  }
  package launch {
    import navigation._
    object Vehicle {
      // private[launch] 는 자바의 디폴트 접근지정자 package-private과 같다.
      // 즉 자바에서는 아무 접근 지정자가 없는 경우가 이에 해당한다.
      private[launch] val guide = new Navigator2
    }
  }
}
/*
 * LegOfJourney.distance 에 private 지정자가 붙어 있는 경우의 효과
 * 
 * 수식자 없음 : 전체 접근
 * private[bobsrockets] : 바깥 패키지 내부에서 접근 가능
 * private[navigation]  : 자바의 패키지 접근과 같음 (자바의 디폴트 접근 지정자)
 * private[Navigator2]  : 자바의 private과 같음
 * private[LegOfJorney] : 스칼라의 private과 같음
 * private[this]        : 인스턴스에서는 접근못하고, 클래스 내부에서만.. 아래 C1, C2 예제보면 됨.
 */
object ch13_i05 {
  val c1 = new C1("str1")
  val c2 = new C2("str2")
  //c1.name
  //c2
}

class C1 (val name: String)
class C2 (private[this] val name: String)

/*
 * 가시성과 동반 객체
 * 
 * 스칼라엔 static(정적)멤버가 없지만, 여러 멤버를 포함하며 단 하나만 존재하는 동반 객체가 있다.
 * 동반 객체와 동반클래스의 private은 서로 공유된다.
 * 
 * 한가지 자바와 스칼라의 큰 차이가 있다면
 * 자바의 경우 protected static 멤버를 서브클래스에서 다 접근이 가능하지만
 * 스칼라의 싱글톤 객체는 서브클래스를 만들 수가 없으므로,
 * 동반 객체 안에서 protected 멤버를 선언하는 것은 말도 안 된다.
 */
class Rocket {
  import Rocket.fuel
  private def canGoHomeAgain = fuel > 20
}

object Rocket {
  private def fuel = 10
  def chooseStrategy(rocket: Rocket) {
    if (rocket.canGoHomeAgain)
      goHome()
    else
      pickAStar()
  }
  def goHome() {}
  def pickAStar() {}
}

