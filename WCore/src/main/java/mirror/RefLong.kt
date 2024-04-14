package mirror

import java.lang.reflect.Field

// done
class RefLong(cls: Class<*>, field: Field) {
    private val field: Field = cls.getDeclaredField(field.name).apply {
        isAccessible = true
    }

    fun get(obj: Any): Long {
        return try {
            field.getLong(obj)
        } catch (e: Exception) {
            0L  // 在 Kotlin 中，长整型常量用 'L' 后缀表示
        }
    }

    fun set(obj: Any, value: Long) {
        try {
            field.setLong(obj, value)
        } catch (e: Exception) {
            // 忽略异常
        }
    }
}
