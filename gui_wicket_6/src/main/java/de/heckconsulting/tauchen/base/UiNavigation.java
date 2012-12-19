package de.heckconsulting.tauchen.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.heckconsulting.tauchen.base.UiTopNavItem.NavItemType;


@Target(value=ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UiNavigation {
	String label() default "NO LABEL SET";
	String[] roles() default {};
	NavItemType itemType();
	String sortOrder();
}
