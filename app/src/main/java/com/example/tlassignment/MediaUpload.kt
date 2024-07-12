package com.example.tlassignment

//noinspection UsingMaterialAndMaterial3Libraries
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MediaUploadScreen(mediaUpload: MediaUpload) {
    var mediaUri by remember { mutableStateOf<Uri?>(null) }



    val mediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) {
        uri :Uri? -> mediaUri = uri
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

        mediaUpload.inputData.forEachIndexed { index, inputData ->
            if (inputData.dataType == InputDataType.dropdown) {
                DropdownMenu(
                    options = listOf("Option 1", "Option 2", "Option 3"),

                    selectedOption = inputData.dataName,

                    onOptionSelected = { selectedOption ->
                        mediaUpload.inputData[index] = inputData.copy(dataValue = selectedOption)
                    },
                    //label =  inputData.dataName
                )
            } else {
                OutlinedTextField(
                    value = inputData.dataValue,
                    onValueChange = { newValue ->
                        mediaUpload.inputData[index] = inputData.copy(dataValue = newValue)
                    },
                    label = { Text (inputData.dataName) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(250.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
                .clickable { mediaPickerLauncher.launch("image/*") },

            contentAlignment = Alignment.Center
        ) {
            Text(text = "+ Upload Media", color = Color.Black)
        }

        mediaUri?.let {
            Text(text = "Selected media: ${it.lastPathSegment}")
        }


        Text(text = "Selected Co-ordinates: 0.0 0.0")

        Spacer(modifier = Modifier.height(70.dp))

        Button(
            onClick = {
                mediaUpload.onUploadDone("Upload Completed")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(20.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(16.dp)


        ) {
            Text(text = "Submit", color = Color.White)
        }
    }
}
@Composable
fun DropdownMenu(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
   // label: String
) {
    Spacer(modifier = Modifier.height(10.dp))
    var expanded by remember { mutableStateOf(false) }

        Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
            .padding(10.dp, vertical = 1.dp)
            .height(50.dp)
            .background(shape = RoundedCornerShape(16.dp), color = Color.White)

    )

    {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedOption,
            onValueChange = {},
            readOnly = true
            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            //  label

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color(0xFF808080),
                    modifier = Modifier
                        .padding(start = 270.dp)
                        .size(100.dp)
                        .clickable { expanded = true }
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, color = Color(0xFF333333)) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }

        }

    }



