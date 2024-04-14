package com.vcamx.whitebox.utils

import java.io.*

object FileUtils {
    fun deleteDir(dir: File): Int {
        var count = 0
        if (dir.isDirectory) {
            var link = false
            try {
                link = isSymlink(dir)
            } catch (e: Exception) {
                //ignore
            }
            if (!link) {
                val children = dir.list()
                for (file in children) {
                    count += deleteDir(File(dir, file))
                }
            }
        }
        if (dir.delete()) {
            count++
        }
        return count
    }

    @Throws(IOException::class)
    fun isSymlink(file: File?): Boolean {
        if (file == null) throw NullPointerException("File must not be null")
        val canon: File
        if (file.parent == null) {
            canon = file
        } else {
            val canonDir = file.parentFile.canonicalFile
            canon = File(canonDir, file.name)
        }
        return canon.canonicalFile != canon.absoluteFile
    }

    fun copyFile(inputStream: InputStream, junitJar: File) {
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(junitJar)
            val data = ByteArray(4096)
            var len: Int
            while ((inputStream.read(data).also { len = it }) != -1) {
                outputStream.write(data, 0, len)
            }
            outputStream.flush()
        } catch (e: Throwable) {
            //ignore
        } finally {
            closeQuietly(inputStream)
            closeQuietly(outputStream)
        }
    }

    fun closeQuietly(closeable: Closeable?) {
        if (closeable != null) {
            try {
                closeable.close()
            } catch (ignored: java.lang.Exception) {
            }
        }
    }
}