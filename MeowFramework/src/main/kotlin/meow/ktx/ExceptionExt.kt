/*
 * Copyright (C) 2020 Hamidreza Etebarian & Ali Modares.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package meow.ktx

import com.google.i18n.phonenumbers.NumberParseException
import meow.controller

/**
 * [Exception] Extensions.
 *
 * @author  Hamidreza Etebarian
 * @version 1.0.0
 * @since   2020-03-04
 */

fun allowReport(
    @Suppress("UNUSED")
    exp: Exception
): Boolean {
    return when (exp) {
        is NumberParseException -> false
        else -> true
    }
}

inline fun <T> avoidException(
    allowPrint: Boolean = controller.isDebugMode,
    exceptionBlock: (e: Exception) -> T? = { null },
    finallyBlock: () -> T? = { null },
    tryBlock: () -> T? = { null }
) =
    try {
        tryBlock()
    } catch (e: Exception) {
        if (allowPrint)
            e.printStackTrace()
        if (allowReport(e))
            controller.onException(e)
        exceptionBlock(e)
    } finally {
        finallyBlock()
    }