package hu.bme.aut.aitforum.ui.screen.writemessage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WriteMessageScreen(
    viewModel: WriteMessageViewModel = viewModel()
) {
    var postTitle by remember { mutableStateOf("") }
    var postBody by remember { mutableStateOf("") }

    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    var hasImage by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )



    Column(
        modifier = Modifier.padding(20.dp)
    ){
        OutlinedTextField(value = postTitle,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Post title") },
            onValueChange = {
                postTitle = it
            }
        )
        OutlinedTextField(value = postBody,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Post body") },
            onValueChange = {
                postBody = it
            }
        )


        if (cameraPermissionState.status.isGranted) {
            //if we have the camera permission then show a "Take photo" button
            Button(onClick = {
                // this code launches the camera
                val uri = ComposeFileProvider.getImageUri(context)
                imageUri = uri
                cameraLauncher.launch(uri)
            }) {
                Text(text = "Take photo")
            }
        } else { // if we do not have the Camera permission yet, we need to ask..
            val permissionText = if (cameraPermissionState.status.shouldShowRationale) {
                "Please reconsider giving the camera persmission " +
                        "it is needed if you want to take photo for the message"
            } else {
                "Give permission for using photos with items"
            }
            Text(text = permissionText)
            Button(onClick = {
                // this code pops up a permission request dialog
                cameraPermissionState.launchPermissionRequest()
            }) {
                Text(text = "Request permission")
            }
        }

        if (hasImage && imageUri != null) {
            AsyncImage(model = imageUri,
                modifier = Modifier.size(200.dp, 200.dp),
                contentDescription = "selected image")
        }


        OutlinedButton(
            onClick = {
                if (imageUri == null) {
                    viewModel.uploadPost(
                        postTitle,
                        postBody
                    )
                } else {
                    viewModel.uploadPostImage(
                        context.contentResolver,
                        imageUri!!, // this is the image file location locally on the phone
                        postTitle,
                        postBody
                    )
                }
            }
        ) {
            Text("Send")
        }

        // UI State machine
        when (viewModel.writePostUiState) {
            is WritePostUiState.Init -> {}
            is WritePostUiState.LoadingPostUpload -> CircularProgressIndicator()
            is WritePostUiState.PostUploadSuccess -> Text("Post uploaded")
            is WritePostUiState.ErrorDuringPostUpload -> {
                Text(
                    text = "Error: ${
                        (viewModel.writePostUiState as
                                WritePostUiState.ErrorDuringPostUpload).error
                    }"
                )
            }

            is WritePostUiState.ErrorDuringImageUpload -> {
                Text(
                    text = "${(viewModel.writePostUiState as
                            WritePostUiState.ErrorDuringImageUpload).error}")
            }
            is WritePostUiState.ImageUploadSuccess -> Text("Image uploaded.")
            is WritePostUiState.LoadingImageUpload -> CircularProgressIndicator()
        }

    }
}