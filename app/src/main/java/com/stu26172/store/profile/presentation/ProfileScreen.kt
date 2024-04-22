package com.stu26172.store.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.stu26172.store.core.presentation.components.CustomOutlinedTextField

@Composable
fun ProfileScreen() {

    var name by remember {
        mutableStateOf("kevinryan")
    }
    var email by remember {
        mutableStateOf("kevin@gmail.com")
    }
    var password by remember {
        mutableStateOf("kev02937@")
    }



    Column(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Spacer(modifier = Modifier.height(56.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp)
                    .clip(RoundedCornerShape(25.dp)),
                model = "https://thispersondoesnotexist.com/",
                contentDescription = null
            )
        }

        Text(
            text = "Name",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color(0xFF707B81)
        )

        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            onValueChange = { name = it })

        Text(
            text = "Email Address",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color(0xFF707B81)
        )

        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email)

        Text(
            text = "Password",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color(0xFF707B81)
        )

        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            text = password,
            onValueChange = { password = it },
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation())
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Recovery Password",
            textAlign = TextAlign.End,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color(0xFF707B81)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(text = "Save Now")
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color(0xFF707B81),
            text = "As it is a fake API, this data will not be saved or processed.")
    }
}