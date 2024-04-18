//package mirror.android.app
//
//import android.app.Activity
//import android.app.Application
//import android.app.Instrumentation
//import android.content.ComponentName
//import android.content.Context
//import android.content.Intent
//import android.content.pm.ActivityInfo
//import android.content.pm.ApplicationInfo
//import android.content.pm.ProviderInfo
//import android.content.pm.ServiceInfo
//import android.os.Build
//import android.os.Handler
//import android.os.IBinder
//import android.os.IInterface
//import mirror.*
//import java.lang.ref.WeakReference
//
//object ActivityThread {
//    val TYPE: Class<*>? = RefClass.load(ActivityThread::class.java, "android.app.ActivityThread")
////    lateinit var currentActivityThread : RefStaticMethod
//    lateinit var getProcessName: RefMethod<String>
//
//
//    lateinit var getHandler: RefMethod<Handler>
//    lateinit var installProvider: RefMethod<Any>
//    lateinit var mBoundApplication: RefObject<Any>
//    lateinit var mH: RefObject<Handler>
//    lateinit var mInitialApplication: RefObject<Application>
//    lateinit var mInstrumentation: RefObject<Instrumentation>
//    lateinit var mPackages: RefObject<Map<String, WeakReference<*>>>
//    lateinit var mActivities: RefObject<Map<IBinder, Any>>
//    lateinit var mProviderMap: RefObject<Map<*, *>>
//
//    @MethodParams(IBinder::class, List::class)
//    lateinit var performNewIntents: RefMethod<Void>
//
//    lateinit var sPackageManager: RefStaticObject<IInterface>
//
//    @MethodParams(IBinder::class, String::class, Int::class, Int::class, Intent::class)
//    lateinit var sendActivityResult: RefMethod<Void>
//
//    lateinit var getApplicationThread: RefMethod<IBinder>
//    lateinit var getSystemContext: RefMethod<Any>
//
//    fun installProvider(mainThread: Any, context: Context, providerInfo: ProviderInfo, holder: Any): Any {
//        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
//            installProvider.callWithException(mainThread, context, holder, providerInfo, false, true)
//        } else {
//            installProvider.callWithException(mainThread, context, holder, providerInfo, false, true, true)
//        }
//    }
//
//    class ActivityClientRecord {
//        companion object {
//            val TYPE: Class<*>? = RefClass.load(ActivityClientRecord::class.java, "android.app.ActivityThread\$ActivityClientRecord")
//            val activity = RefObject<Activity>()
//            val activityInfo = RefObject<ActivityInfo>()
//            val intent = RefObject<Intent>()
//            val token = RefObject<IBinder>()
//            val isTopResumedActivity = RefObject<Boolean>()
//        }
//    }
//
//    class ProviderClientRecord {
//        companion object {
//            val TYPE: Class<*>? = RefClass.load(ProviderClientRecord::class.java, "android.app.ActivityThread\$ProviderClientRecord")
//            val ctor = RefConstructor<Any>()
//            val mName = RefObject<String>()
//            val mProvider = RefObject<IInterface>()
//        }
//    }
//
//    class ProviderClientRecordP {
//        companion object {
//            val TYPE: Class<*>? = RefClass.load(ProviderClientRecordP::class.java, "android.app.ActivityThread\$ProviderClientRecord")
//            val ctor = RefConstructor<Any>()
//            val mNames = RefObject<Array<String>>()
//            val mProvider = RefObject<IInterface>()
//        }
//    }
//
//    class ProviderClientRecordJB {
//        companion object {
//            val TYPE: Class<*>? = RefClass.load(ProviderClientRecordJB::class.java, "android.app.ActivityThread\$ProviderClientRecord")
//            val mHolder = RefObject<Any>()
//            val mProvider = RefObject<IInterface>()
//        }
//    }
//
//    class ProviderKeyJBMR1 {
//        companion object {
//            val TYPE: Class<*>? = RefClass.load(ProviderKeyJBMR1::class.java, "android.app.ActivityThread\$ProviderKey")
//            val ctor = RefConstructor<Any>()
//        }
//    }
//
//    class AppBindData {
//        companion object {
//            val TYPE: Class<*>? = RefClass.load(AppBindData::class.java, "android.app.ActivityThread\$AppBindData")
//            val appInfo = RefObject<ApplicationInfo>()
//            val info = RefObject<Any>()
//            val processName = RefObject<String>()
//            val instrumentationName = RefObject<ComponentName>()
//            val providers = RefObject<List<ProviderInfo>>()
//        }
//    }
//
//    class H {
//        companion object {
//            val TYPE: Class<*>? = RefClass.load(H::class.java, "android.app.ActivityThread\$H")
//            val LAUNCH_ACTIVITY = RefStaticInt()
//            val EXECUTE_TRANSACTION = RefStaticInt()
//            val SCHEDULE_CRASH = RefStaticInt()
//        }
//    }
//
//    class CreateServiceData {
//        companion object {
//            val TYPE: Class<*>? = RefClass.load(CreateServiceData::class.java, "android.app.ActivityThread\$CreateServiceData")
//            val compatInfo = RefObject<Any>()
//            val info = RefObject<ServiceInfo>()
//            val intent = RefObject<Intent>()
//            val token = RefObject<IBinder>()
//        }
//    }
//}
