package mirror

import java.lang.reflect.Field

// done
class RefStaticInt(cls: Class<*>, field: Field) {

    private val field: Field = cls.getDeclaredField(field.name).apply {
        isAccessible = true
    }

    fun get(): Int {
        return try {
            field.getInt(null) // 静态字段的访问，使用 null 作为对象参数
        } catch (e: Exception) {
            0
        }
    }

    fun set(value: Int) {
        try {
            field.setInt(null, value) // 设置静态字段的值，使用 null 作为对象参数
        } catch (e: Exception) {
            // 忽略异常
        }
    }
}
