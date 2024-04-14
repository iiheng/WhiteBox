package mirror

import java.lang.reflect.Field

// done
class RefDouble(cls: Class<*>, field: Field) {
    private val field: Field = cls.getDeclaredField(field.name).apply {
        isAccessible = true
    }

    fun get(obj: Any): Double {
        return try {
            field.getDouble(obj)
        } catch (e: Exception) {
            0.0
        }
    }

    fun set(obj: Any, value: Double) {
        try {
            field.setDouble(obj, value)
        } catch (e: Exception) {
            // 忽略异常
        }
    }
}
