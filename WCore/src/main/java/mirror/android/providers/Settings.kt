package mirror.android.providers

import android.annotation.TargetApi
import android.os.Build
import android.os.IInterface
import mirror.RefClass
import mirror.RefObject
import mirror.RefStaticObject

object Settings {
    val TYPE: Class<*> = RefClass.load(Settings::class.java, android.provider.Settings::class.java)

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    object Global {
        val TYPE: Class<*> = RefClass.load(Global::class.java, android.provider.Settings.Global::class.java)
        var sNameValueCache: RefStaticObject<Any>? = null
    }

    object NameValueCache {
        val TYPE: Class<*> = RefClass.load(NameValueCache::class.java, "android.provider.Settings\$NameValueCache")!!
        var mContentProvider: RefObject<Any>? = null
    }

    object NameValueCacheOreo {
        val TYPE: Class<*> = RefClass.load(NameValueCacheOreo::class.java, "android.provider.Settings\$NameValueCache")!!
        var mProviderHolder: RefObject<Any>? = null
    }

    object ContentProviderHolder {
        val TYPE: Class<*> = RefClass.load(ContentProviderHolder::class.java, "android.provider.Settings\$ContentProviderHolder")!!
        var mContentProvider: RefObject<IInterface>? = null
    }

    object Secure {
        val TYPE: Class<*> = RefClass.load(Secure::class.java, android.provider.Settings.Secure::class.java)
        var sNameValueCache: RefStaticObject<Any>? = null
    }

    object System {
        val TYPE: Class<*> = RefClass.load(System::class.java, android.provider.Settings.System::class.java)
        var sNameValueCache: RefStaticObject<Any>? = null
    }
}
