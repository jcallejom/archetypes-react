/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.aop.log;

import java.util.Arrays;

//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class LogAspect.
 */
//@Aspect
public class LogAspect {

//	/** The log. */
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//	/**
//	 * Pointcut that matches all repositories, services and Web REST endpoints.
//	 */
//	@Pointcut("@within(org.springframework.stereotype.Repository)"
//			+ " || @within(org.springframework.stereotype.Service)"
//			+ " || @within(org.springframework.web.bind.annotation.RestController)")
//	public void springBeanPointcut() {
//		// Method is empty as this is just a Pointcut, the implementations are in the
//		// advices.
//	}
//
//	/**com.archetype.app.infrastructure.out.db.jpa.repository
//	 * Pointcut that matches all Spring beans in the application's main packages.
//	 */
//	@Pointcut("within(com.archetype.*.infrastructure.out.db.jpa.repository..*)" 
//			+ " || within(com.archetype.*.application..*)"
//			+ " || within(com.archetype.*.infrastructure.in.rest..*)" + " || within(com.archetype..*)")
//	public void applicationPackagePointcut() {
//		// Method is empty as this is just a Pointcut, the implementations are in the
//		// advices.
//	}
//
//	/**
//	 * Advice that logs when a method is entered and exited.
//	 *
//	 * @param joinPoint join point for advice.
//	 * @return result.
//	 * @throws Throwable throws {@link IllegalArgumentException}.
//	 */
//	@Around("applicationPackagePointcut() && springBeanPointcut()")
//	public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
//		if (log.isDebugEnabled()) {
//			log.debug("# Enter: {}.{}() with argument[s] = {}",
//					joinPoint.getSignature().getDeclaringTypeName(),
//					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
//		}
//		try {
//			Object result = joinPoint.proceed();
//			if (log.isDebugEnabled()) {
//				log.debug("# Exit: {}.{}() with result = {}",
//						joinPoint.getSignature().getDeclaringTypeName(),
//						joinPoint.getSignature().getName(), result);
//			}
//			return result;
//		} catch (IllegalArgumentException e) {
//			log.error("# Illegal argument: {} in {}.{}()",
//					Arrays.toString(joinPoint.getArgs()),
//					joinPoint.getSignature().getDeclaringTypeName(),
//					joinPoint.getSignature().getName());
//
//			throw e;
//		}
//	}

}
