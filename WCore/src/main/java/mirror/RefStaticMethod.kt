package mirror

import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class RefStaticMethod<T : Any>(cls: Class<*>, field: Field) {
    private var method: Method?
    private val name: String = field.name
    private val parent: String = cls.name

    init {
        method = null
        if (field.isAnnotationPresent(MethodParams::class.java)) {
            val types = field.getAnnotation(MethodParams::class.java).value
            for (i in types.indices) {
                val kClass = types[i]
                val clazz = kClass.java
                if (clazz.classLoader == this::class.java.classLoader) {
                    try {
                        Class.forName(clazz.name)
                        val realClass = clazz.getField("TYPE").get(null) as Class<*>
//                        types[i] = realClass
                    } catch (e: Throwable) {
                        throw RuntimeException(e)
                    }
                }
            }
//            method = cls.getDeclaredMethod(name, *types)
        } else if (field.isAnnotationPresent(MethodReflectParams::class.java)) {
            val typeNames = field.getAnnotation(MethodReflectParams::class.java).value
            val types = typeNames.mapNotNull { typeName -> getProtoType(typeName) ?: try {
                Class.forName(typeName)
            } catch (e: ClassNotFoundException) {
                null
            } }.toTypedArray()

            try {
                method = cls.getDeclaredMethod(name, *types)
            } catch (e: Exception) {
                e.printStackTrace()
                // Attempt to use android.util.ArraySet if java.util.HashSet fails
                if (typeNames.contains("java.util.HashSet")) {
                    val types2 = types.map { if (it == HashSet::class.java) try {
                        Class.forName("android.util.ArraySet")
                    } catch (e: ClassNotFoundException) {
                        it
                    } else it }.toTypedArray()
                    method = cls.getDeclaredMethod(name, *types2)
                }
            }
        } else {
            method = cls.declaredMethods.find { it.name == name }
        }
        method?.isAccessible = true

        if (method == null) {
            throw NoSuchMethodException(name)
        }
    }

    companion object {
        fun getProtoType(typeName: String): Class<*>? = when (typeName) {
            "int" -> Int::class.javaPrimitiveType
            "long" -> Long::class.javaPrimitiveType
            "boolean" -> Boolean::class.javaPrimitiveType
            "byte" -> Byte::class.javaPrimitiveType
            "short" -> Short::class.javaPrimitiveType
            "char" -> Char::class.javaPrimitiveType
            "float" -> Float::class.javaPrimitiveType
            "double" -> Double::class.javaPrimitiveType
            "void" -> Void.TYPE
            else -> null
        }
    }

    fun call(vararg params: Any?): T? {
        return try {
            method?.invoke(null, *params) as T?
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun callWithException(vararg params: Any?): T {
        try {
            return method?.invoke(null, *params) as T
        } catch (e: Exception) {
            throw e.cause ?: e
        }
    }

    override fun toString(): String {
        return "RefStaticMethod{$parent@$name find=${method != null}}"
    }
}
