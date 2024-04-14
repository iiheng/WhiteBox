package mirror

import mirror.RefStaticMethod.Companion.getProtoType
import java.lang.reflect.Constructor
import java.lang.reflect.Field

// done
class RefConstructor<T>(cls: Class<*>, field: Field) {
    private var ctor: Constructor<*>? = null

    init {
        when {
            field.isAnnotationPresent(MethodParams::class.java) -> {
                val types = field.getAnnotation(MethodParams::class.java)?.value?.map { it.java }?.toTypedArray()
                ctor = cls.getDeclaredConstructor(*types!!)
            }
            field.isAnnotationPresent(MethodReflectParams::class.java) -> {
                val values = field.getAnnotation(MethodReflectParams::class.java)?.value
                val parameterTypes = values?.map { className ->
                    getProtoType(className) ?: Class.forName(className)
                }?.toTypedArray()
                ctor = cls.getDeclaredConstructor(*parameterTypes!!)
            }
            else -> {
                ctor = cls.getDeclaredConstructor()
            }
        }

        ctor?.isAccessible = true
    }

    fun newInstance(): T? = try {
        ctor?.newInstance() as? T
    } catch (e: Exception) {
        null
    }

    fun newInstance(vararg params: Any): T? = try {
        ctor?.newInstance(*params) as? T
    } catch (e: Exception) {
        null
    }
}
