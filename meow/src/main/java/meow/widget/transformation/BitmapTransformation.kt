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

package meow.widget.transformation

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.util.Util
import java.security.MessageDigest

/**
 * Bitmap Transformation class.
 *
 * @author  Hamidreza Etebarian
 * @version 1.0.0
 * @since   2020-03-18
 */

abstract class BitmapTransformation : Transformation<Bitmap> {

    abstract fun key(): String

    override fun transform(
        context: Context,
        resource: Resource<Bitmap>,
        outWidth: Int,
        outHeight: Int
    ): Resource<Bitmap> {
        require(Util.isValidDimensions(outWidth, outHeight)) {
            ("Cannot apply transformation on width: " + outWidth + " or height: " + outHeight
                    + " less than or equal to zero and not Target.SIZE_ORIGINAL")
        }
        val bitmapPool = Glide.get(context).bitmapPool
        val toTransform = resource.get()
        val targetWidth = if (outWidth == Target.SIZE_ORIGINAL) toTransform.width else outWidth
        val targetHeight = if (outHeight == Target.SIZE_ORIGINAL) toTransform.height else outHeight
        val transformed = transform(
            context.applicationContext,
            bitmapPool,
            toTransform,
            targetWidth,
            targetHeight
        )

        return if (toTransform == transformed) {
            resource
        } else {
            BitmapResource.obtain(transformed, bitmapPool)!!
        }
    }

    protected abstract fun transform(
        context: Context,
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(key().toByteArray())
    }
}