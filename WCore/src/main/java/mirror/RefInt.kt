package mirror

import java.lang.reflect.Field

// done
class RefInt(cls: Class<*>, fieldName: String) {
    private val field: Field = cls.getDeclaredField(fieldName)

    init {
        field.isAccessible = true
    }

    fun get(obj: Any): Int {
        return try {
            field.getInt(obj)
        } catch (e: Exception) {
            0
        }
    }

    fun set(obj: Any, intValue: Int) {
        try {
            field.setInt(obj, intValue)
        } catch (e: Exception) {
            // 忽略异常
        }
    }
}
