package mirror

import java.lang.reflect.Field

// done
class RefObject<T>(cls: Class<*>, field: Field) {
    private val field: Field = cls.getDeclaredField(field.name).apply {
        isAccessible = true
    }

    fun get(obj: Any): T? {
        return try {
            field.get(obj) as T?
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun set(obj: Any, value: T): Boolean {
        return try {
            field.set(obj, value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
