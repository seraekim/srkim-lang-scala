package chapter27

/**
 * 27.2 애노테이션 문법
 * 
 * @deprecated def bigMistake() = //...
 * 애노테이션을 bigMistake 전체에 대해 적용했다.
 * 
 * 애노테이션은 val, var, def, class, object, trait, type 등
 * 모든 종류의 선언이나 정의에 사용 가능하다. 표현식에도 사용할 수 있는데 패턴 매치에서
 * @unchecked 를 사용한 적이 있다
 * (e: @unchecked) match { ... }
 * 
 * 일반적인 애노테이션 형식은 다음과 같다.
 * @annot(exp1, exp2, ...)
 * annot는 애노테이션 클래스를 지정한다. 모든 애노테이션은 꼭 클래스가 있어야 한다.
 * exp는 애노테이션의 인자다. @deprecated 같은 경우 인자가 필요 없다. 보통 그런 경우
 * 괄호를 생략한다. 하지만 원한다면 @deprecated() 라고 쓸 수 있다.
 * 인자가 있다면 @serial(1234)처럼 괄호안에 인자를 넣어야 한다.
 * 
 * 대부분의 애노테이션 처리기는 123이나 "hello"처럼 직접적인 리터럴 상수만을 지원한다.
 * 컴파일러 자체는 타입 검사를 통과하는 한 임의의 식을 지원하며, 몇 애노테이션 클래스는
 * 이를 활용하여, 스코프에서 보이는 다른 변수를 참조한다.
 * @cool val normal = "hello"
 * @coolerThan(normal) val fonzy = "heeyyyy"
 * 
 * 내부적으로 스칼라는 애노테이션을 그냥 애노테이션 클래스에 대한 생성자 호출로 다룬다.
 * @를 new로 바꾸면 올바른 인스턴스 생성 표현식이 된다.
 * 
 * 애노테이션은 인자로 또 다른 애노테이션을 취할 수 있다. 그러나 직접 인자로는 못쓰는데
 * 올바른 표현식이 아니기 때문에 new로 바꿔야 한다.
 */
import annotation._
class strategy(arg: Annotation) extends Annotation
class delayed extends Annotation
object c27_i02 extends App {
  // @strategy(@delayed) def f() {} // illegal start of simple expression
  @strategy(new delayed) def f() {}
}