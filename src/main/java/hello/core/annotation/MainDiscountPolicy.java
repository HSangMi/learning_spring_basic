package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
// @Qualifier에 있는 거 복붙(어노테이션은 상속의 개념이 없음)
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
