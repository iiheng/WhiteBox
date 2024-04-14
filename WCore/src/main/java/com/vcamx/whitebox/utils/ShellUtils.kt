package com.vcamx.whitebox.utils

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader

object ShellUtils {

    val COMMAND_SU: String = "su"
    val COMMAND_SH: String = "sh"
    val COMMAND_EXIT: String = "exit\n"
    val COMMAND_LINE_END: String = "\n"
    fun execCommand(commands: List<String>?, isRoot: Boolean, isNeedResultMsg: Boolean): CommandResult {
        return execCommand(commands?.toTypedArray(), isRoot, isNeedResultMsg)
    }

    fun execCommand(commands: Array<String>?, isRoot: Boolean, isNeedResultMsg: Boolean): CommandResult  {
        var result = -1
        if (commands == null || commands.size == 0) {
            return CommandResult(result, null)
        }
        var process: Process? = null
        var successResult: BufferedReader? = null
        var successMsg: StringBuilder? = null
        var os: DataOutputStream? = null
        try {
            process = Runtime.getRuntime().exec(if (isRoot) COMMAND_SU else COMMAND_SH)
            os = DataOutputStream(process!!.outputStream)
            for (command in commands) {
                if (command == null) {
                    continue
                }
                os.write(command.toByteArray())
                os.writeBytes(COMMAND_LINE_END)
                os.flush()
            }
            os.writeBytes(COMMAND_EXIT)
            os.flush()
            result = process!!.waitFor()
            if (isNeedResultMsg) {
                successMsg = StringBuilder()
                successResult = BufferedReader(InputStreamReader(process!!.inputStream))
                var s: String
                while ((successResult.readLine().also { s = it }) != null) {
                    successMsg.append(s + "\n")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                os?.close()
                successResult?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            process?.destroy()
        }
        return CommandResult(result, successMsg?.toString())
    }

    fun execCommand(command: String, isRoot: Boolean): CommandResult {
        return execCommand(arrayOf(command), isRoot, true)
    }

    class CommandResult {
        /**
         * result of command
         */
        var result: Int

        /**
         * success message of command result
         */
        var successMsg: String? = null


        constructor(result: Int) {
            this.result = result
        }


        constructor(result: Int, successMsg: String?) {
            this.result = result
            this.successMsg = successMsg
        }
    }


}