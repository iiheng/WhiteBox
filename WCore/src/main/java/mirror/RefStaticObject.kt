package mirror

import java.lang.reflect.Field

// done
class RefStaticObject<T>(cls: Class<*>, field: Field) {
    private val field: Field = cls.getDeclaredField(field.name).apply {
        isAccessible = true
    }

    fun type(): Class<*> = field.type

    fun get(): T? {
        return try {
            field.get(null) as T?
        } catch (e: Exception) {
            null
        }
    }

    fun set(obj: T?) {
        try {
            field.set(null, obj)
        } catch (e: Exception) {
            // 忽略异常
        }
    }
}
