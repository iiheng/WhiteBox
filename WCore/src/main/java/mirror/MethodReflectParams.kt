package mirror

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlin.annotation.AnnotationRetention
import kotlin.annotation.AnnotationTarget
import kotlin.annotation.Target

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class MethodReflectParams(val value: Array<String>)
