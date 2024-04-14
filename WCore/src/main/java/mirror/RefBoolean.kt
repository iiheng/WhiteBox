package mirror

import java.lang.reflect.Field

// done
class RefBoolean(cls: Class<*>, field: Field) {
    private val field: Field = cls.getDeclaredField(field.name).apply {
        isAccessible = true
    }

    fun get(obj: Any): Boolean {
        return try {
            field.getBoolean(obj)
        } catch (e: Exception) {
            false
        }
    }

    fun set(obj: Any, value: Boolean) {
        try {
            field.setBoolean(obj, value)
        } catch (e: Exception) {
            // 忽略异常
        }
    }
}
