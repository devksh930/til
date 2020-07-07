package me.devksh930.beanscope.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component @Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)//싱글톤 빈이 프로토타입의 빈을 직접참조하면 안되고 proxy를 이용하여 참조 하여야 되기 때문에 proxy로 감싸라는 뜻임
//@Component @Scope(value = "prototype")
public class Proto {
}
