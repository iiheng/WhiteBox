package mirror

import java.lang.reflect.Method

class RefStaticMethod<T>(cls: Class<*>, methodName: String, vararg parameterTypes: Class<*>) {
    private val method: Method

    init {
        method = cls.getDeclaredMethod(methodName, *parameterTypes)
        method.isAccessible = true
    }

    fun invoke(vararg args: Any?): T? {
        return try {
            @Suppress("UNCHECKED_CAST")
            method.invoke(null, *args) as T?
        } catch (e: Exception) {
            null
        }
    }
}
