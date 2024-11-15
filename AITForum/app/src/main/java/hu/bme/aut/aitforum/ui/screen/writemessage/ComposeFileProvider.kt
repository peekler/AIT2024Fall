package hu.bme.aut.aitforum.ui.screen.writemessage


import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File


class ComposeFileProvider : FileProvider(
    hu.bme.aut.aitforum.R.xml.filepaths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )
            val authority = context.packageName + ".fileprovider"
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}