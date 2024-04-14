package mirror

import java.lang.reflect.Field

// done
class RefFloat(cls: Class<*>, field: Field) {
    private val field: Field = cls.getDeclaredField(field.name).apply {
        isAccessible = true
    }

    fun get(obj: Any): Float {
        return try {
            field.getFloat(obj)
        } catch (e: Exception) {
            0f  // 在 Kotlin 中，浮点数常量可以直接用 'f' 表示
        }
    }

    fun set(obj: Any, value: Float) {
        try {
            field.setFloat(obj, value)
        } catch (e: Exception) {
            // 忽略异常
        }
    }
}
