package mirror

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

object RefClass {
    private val refTypes = hashMapOf<KClass<*>, Constructor<*>>(
        RefObject::class to  RefObject::class.java.getConstructor(Class::class.java, Field::class.java),
        RefMethod::class to  RefMethod::class.java.getConstructor(Class::class.java, Field::class.java),
        RefInt::class to  RefInt::class.java.getConstructor(Class::class.java, Field::class.java),
        RefLong::class to  RefLong::class.java.getConstructor(Class::class.java, Field::class.java),
        RefFloat::class to  RefFloat::class.java.getConstructor(Class::class.java, Field::class.java),
        RefDouble::class to  RefDouble::class.java.getConstructor(Class::class.java, Field::class.java),
        RefBoolean::class to  RefBoolean::class.java.getConstructor(Class::class.java, Field::class.java),
        RefStaticObject::class to  RefStaticObject::class.java.getConstructor(Class::class.java, Field::class.java),
        RefStaticInt::class to  RefStaticInt::class.java.getConstructor(Class::class.java, Field::class.java),
        RefStaticMethod::class to  RefStaticMethod::class.java.getConstructor(Class::class.java, Field::class.java),
        RefConstructor::class to  RefConstructor::class.java.getConstructor(Class::class.java, Field::class.java)
    )
//    private val refTypes: HashMap<KClass<*>, Constructor<*>> = hashMapOf()
//
//    init {
//        try {
//            refTypes[RefObject::class] = RefObject::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefMethod::class] = RefMethod::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefInt::class] = RefInt::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefLong::class] = RefLong::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefFloat::class] = RefFloat::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefDouble::class] = RefDouble::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefBoolean::class] = RefBoolean::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefStaticObject::class] = RefStaticObject::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefStaticInt::class] = RefStaticInt::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefStaticMethod::class] = RefStaticMethod::class.java.getConstructor(Class::class.java, Field::class.java)
//            refTypes[RefConstructor::class] = RefConstructor::class.java.getConstructor(Class::class.java, Field::class.java)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }


    fun load(mappingClass: Class<*>, className: String): Class<*>? {
        return try {
            load(mappingClass, Class.forName(className))
        } catch (e: Exception) {
            null
        }
    }

    fun load(mappingClass: Class<*>, realClass: Class<*>): Class<*> {
        val fields = mappingClass.declaredFields
        fields.forEach { field ->
            try {
                if (Modifier.isStatic(field.modifiers)) {
                    val kClass = field.type.kotlin // 将 Java 的 Class<*> 转换为 Kotlin 的 KClass<*>
                    val constructor = refTypes[kClass]
                    constructor?.newInstance(realClass, field)?.let {
                        field.set(null, it)
                    }
                }} catch (e: Exception) {
                // 忽略异常
            }}
        return realClass
    }

//    fun load(mappingClass: Class<*>, className: String): Class<*>? {
//        return try {
//            load(mappingClass, Class.forName(className))
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//    fun load(mappingClass: Class<*>, realClass: Class<*>): Class<*> {
//        val fields = mappingClass.declaredFields
//        fields.forEach { field ->
//            try {
//                if (Modifier.isStatic(field.modifiers)) {
//                    val kClass = field.type.kotlin // 将 Java 的 Class<*> 转换为 Kotlin 的 KClass<*>
//                    val constructor = refTypes[kClass]
//                    constructor?.newInstance(realClass, field)?.let {
//                        field.set(null, it)
//                    }
//                }
//            } catch (e: Exception) {
//                // 忽略异常
//            }
//        }
//        return realClass
//    }
}
