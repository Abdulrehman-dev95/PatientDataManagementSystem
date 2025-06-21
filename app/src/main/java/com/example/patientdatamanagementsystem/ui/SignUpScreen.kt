package com.example.patientdatamanagementsystem.ui


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.patientdatamanagementsystem.R
import com.example.patientdatamanagementsystem.ui.theme.bodyFontFamily
import kotlinx.coroutines.delay


@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    signUpViewModel: AuthenticationViewModel,
    onSignUpClick: (String, String, String) -> Unit,
    onSignUpRetryClick: () -> Unit,
    onLoginClick: () -> Unit,
    navHostController: NavHostController

) {
    val signupUiState = signUpViewModel.uiState
    val authState = signUpViewModel.authState.collectAsState()
    Column(modifier = modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(
                    R.drawable.image_doctor_female

                ), contentDescription = null
            )
        }
        when (authState.value) {
            0 -> LoadingScreen()
            1 -> SignupSuccessScreen {
                signUpViewModel.changeAuthSate(3)
                navHostController.navigate(
                    Screen.LogIn.route
                ) {
                    popUpTo(Screen.SignUp.route) {
                        inclusive = true
                    }
                }
            }


            2 -> ErrorScreen {
                onSignUpRetryClick()
            }
        }
        AnimatedVisibility(authState.value == 3) {
            SignupForm(newUser = signupUiState, onValueChange = {
                signUpViewModel.updateUiState(it)
            }, onSignUpClick = onSignUpClick, onLoginClick = onLoginClick)


        }
    }
}

@Composable
fun SignupForm(
    modifier: Modifier = Modifier,
    newUser: User,
    onSignUpClick: (String, String, String) -> Unit,
    onValueChange: (User) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "Sign Up",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = newUser.email,
            onValueChange = {
                onValueChange(
                    newUser.copy(
                        email = it
                    )
                )
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ), textStyle = LocalTextStyle.current.copy(
                fontFamily = bodyFontFamily,
            ),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newUser.password,
            onValueChange = {
                onValueChange(
                    newUser.copy(
                        password = it
                    )
                )
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                autoCorrectEnabled = true
            ), textStyle = LocalTextStyle.current.copy(
                fontFamily = bodyFontFamily,
            ), shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        UserTypeDropdown(newUser, onValueChange)

        Spacer(modifier = Modifier.height(32.dp))


        FilledTonalButton(
            onClick = {
                onSignUpClick(
                    newUser.email,
                    newUser.password,
                    newUser.userType
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SignUp", fontFamily = bodyFontFamily)
        }

        TextButton(
            onClick = {
                onLoginClick()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                "Already have an account? Login",
                fontFamily = bodyFontFamily,
                fontSize = 16.sp
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun UserTypeDropdown(user: User, onValueChange: (User) -> Unit) {
    // Define the options for the dropdown
    val userTypes = listOf("Patient", "Doctor")

    // State to track the expanded state of the dropdown
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
            readOnly = true,
            value = user.userType,
            onValueChange = {},
            shape = RoundedCornerShape(16.dp),
            label = { Text("Select User Type") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            textStyle = LocalTextStyle.current.copy(
                fontFamily = bodyFontFamily,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )

        // Dropdown menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            matchTextFieldWidth = false
        ) {
            userTypes.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onValueChange(
                            user.copy(
                                userType = selectionOption
                            )
                        )
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Internet Slow or there is something error",
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = onRetryClick
        ) {
            Text(
                text = "Please Retry"
            )
        }

    }

}

@Composable
fun SignupSuccessScreen(onNavigate: () -> Unit) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.check_mark_animation))
    val progress by animateLottieCompositionAsState(composition = composition)

    LaunchedEffect(Unit) {
            delay(3000) // Show success screen for 3 seconds
            onNavigate()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = "Account Created!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Redirecting to login...",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            CircularProgressIndicator(
                modifier = Modifier.padding(top = 24.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignupScreen() {
    SignupScreen(
        onSignUpClick = { _, _, _ -> },
        signUpViewModel = TODO(),
        onSignUpRetryClick = {},
        onLoginClick = {},
        navHostController = TODO()
    )

}
