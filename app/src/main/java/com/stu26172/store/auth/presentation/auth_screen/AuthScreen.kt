package com.stu26172.store.auth.presentation.auth_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.stu26172.store.auth.domain.model.LoginRequest
import com.stu26172.store.core.presentation.components.CustomOutlinedTextField

@Composable
fun AuthScreen(
    uiState: AuthUiState, onClickLogin: (LoginRequest) -> Unit, onNavigateToProducts: () -> Unit
) {

    var username by remember {
        mutableStateOf("kevinryan")
    }
    var password by remember {
        mutableStateOf("kev02937@")
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember {
        mutableStateOf("")
    }



    LaunchedEffect(key1 = uiState) {
        uiState.error?.let {
            errorMessage = it.message
            showDialog = true
        }
        if (uiState.loginIsSuccessful) {
            onNavigateToProducts()
        }
    }

    AnimatedVisibility(visible = showDialog) {
        AlertDialog(
            title = {
                Text(text = errorMessage)
            },
            text = {
                Text(text = "Use the default login information")
            },
            onDismissRequest = { showDialog = !showDialog },
            confirmButton = {
                Button(onClick = {
                    showDialog = !showDialog
                    username = "kevinryan"
                    password = "kev02937@"
                }) {
                    Text(text = "Reset to default")
                }
            },
        )
    }


    ConstraintLayout {
        val (textHelloAgain, subtitle, columnTextFields, boxLoading) = createRefs()
        val guide = createGuidelineFromTop(0.2f)


        Text(modifier = Modifier.constrainAs(textHelloAgain) {
                top.linkTo(guide)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, text = "Hello Again!", fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Text(
            modifier = Modifier.constrainAs(subtitle) {
                    top.linkTo(textHelloAgain.bottom, margin = 5.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, text = "Fill your details", color = Color(0xFF707B81)
        )

        Column(modifier = Modifier
            .padding(15.dp)
            .constrainAs(columnTextFields) {
                top.linkTo(subtitle.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text(text = "User name", fontWeight = FontWeight.Medium, fontSize = 19.sp)

            CustomOutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
                text = username,
                onValueChange = { username = it })

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Pasword",
                fontWeight = FontWeight.Medium,
                fontSize = 19.sp
            )
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                text = password,
                onValueChange = { password = it },
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                ),
                shape = RoundedCornerShape(14.dp),
                onClick = {
                    onClickLogin(LoginRequest(username, password))
                }) {

                Text(text = "Login")
            }
        }

        if (uiState.isLoading) {
            Box(modifier = Modifier
                .constrainAs(boxLoading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(Color.Transparent.copy(alpha = 0.2f))
                .fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}