//package mirror
//
//import java.lang.reflect.Method
//
//class RefMethod<T>(cls: Class<*>, methodName: String, vararg parameterTypes: Class<*>) {
//    private val method: Method
//
//    init {
//        method = cls.getDeclaredMethod(methodName, *parameterTypes)
//        method.isAccessible = true
//    }
//
//    fun invoke(obj: Any?, vararg args: Any?): T? {
//        return try {
//            @Suppress("UNCHECKED_CAST")
//            method.invoke(obj, *args) as T?
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//    fun callWithException(mainThread: T, context: T, holder: T, providerInfo: T, t: T, t1: T, t2: T): T {
//
//    }
//}
//
//
////package mirror
////
////import mirror.RefStaticMethod.Companion.getProtoType
////import java.lang.reflect.Field
////import java.lang.reflect.InvocationTargetException
////import java.lang.reflect.Method
////
////// done
////class RefMethod<T>(cls: Class<*>, field: Field) {
////    private var method: Method? = null
////
////    init {
////        if (field.isAnnotationPresent(MethodParams::class.java)) {
////            val types = field.getAnnotation(MethodParams::class.java)!!.value.clone()
////            for (i in types.indices) {
////                val clazz = types[i].java
////                if (clazz.classLoader == javaClass.classLoader) {
////                    try {
////                        Class.forName(clazz.name)
////                        val realClass = clazz.getField("TYPE").get(null) as Class<*>
////                        types[i] = realClass
////                    } catch (e: Throwable) {
////                        throw RuntimeException(e)
////                    }
////                }
////            }
////            method = cls.getDeclaredMethod(field.name, *types)
////            method!!.isAccessible = true
////        } else if (field.isAnnotationPresent(MethodReflectParams::class.java)) {
////            val typeNames = field.getAnnotation(MethodReflectParams::class.java)!!.value
////            val types = Array<Class<*>?>(typeNames.size) { null }
////            for (i in typeNames.indices) {
////                var type = getProtoType(typeNames[i])
////                if (type == null) {
////                    try {
////                        type = Class.forName(typeNames[i])
////                    } catch (e: ClassNotFoundException) {
////                        e.printStackTrace()
////                    }
////                }
////                types[i] = type
////            }
////            method = cls.getDeclaredMethod(field.name, *types)
////            method!!.isAccessible = true
////        } else {
////            for (m in cls.declaredMethods) {
////                if (m.name == field.name) {
////                    method = m
////                    method!!.isAccessible = true
////                    break
////                }
////            }
////        }
////        if (method == null) {
////            throw NoSuchMethodException(field.name)
////        }
////    }
////
////    fun call(receiver: Any, vararg args: Any?): T? {
////        try {
////            return method?.invoke(receiver, *args) as T?
////        } catch (e: InvocationTargetException) {
////            e.cause?.printStackTrace() ?: e.printStackTrace()
////        } catch (e: Throwable) {
////            e.printStackTrace()
////        }
////        return null
////    }
////
////    fun callWithException(receiver: Any, vararg args: Any?): T {
////        try {
////            return method?.invoke(receiver, *args) as T
////        } catch (e: InvocationTargetException) {
////            if (e.cause != null) {
////                throw e.cause!!
////            }
////            throw e
////        }
////    }
////
////    fun paramList(): Array<Class<*>>? {
////        return method?.parameterTypes
////    }
////}
