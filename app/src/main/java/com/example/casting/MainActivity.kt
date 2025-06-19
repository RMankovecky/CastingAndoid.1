package com.example.casting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.casting.ui.theme.CastingTheme
import com.example.casting.ui.theme.Radius
import com.example.casting.ui.theme.Spacing
import com.example.casting.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CastingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InputView()
                }
            }
        }
    }
}

data class PasswordValidationResult(
    val lengthValid: Boolean,
    val hasUpperCase: Boolean,
    val hasNumber: Boolean,
    val hasSpecialChar: Boolean
)

fun validatePassword(password: String): PasswordValidationResult {
    return PasswordValidationResult(
        lengthValid = password.length >= 8,
        hasUpperCase = password.any { it.isUpperCase() },
        hasNumber = password.any { it.isDigit() },
        hasSpecialChar = password.any { !it.isLetterOrDigit() }
    )
}


@Composable
fun InputView(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {

        Text(
            text = "Text input",
            modifier = modifier,
            style = Typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        PasswordInput(modifier = modifier.padding(top= Spacing.l.dp))

    }
}

@Composable
fun PasswordRuleText(rule: String) {
    Text(
        text = "• $rule",
        style = Typography.labelSmall.copy(
            color = colorResource(id = R.color.content_warning)
        ),
        modifier = Modifier.padding(vertical = Spacing.m.dp)
    )
}

@Composable
fun PasswordInput(modifier: Modifier = Modifier){
    var password by remember { mutableStateOf("") }

    val hasStartedTyping = password.isNotEmpty()
    val validation = validatePassword(password)

    val allValid = with(validation) {
        lengthValid && hasUpperCase && hasNumber && hasSpecialChar
    }

    val isError = hasStartedTyping && !allValid

    val labelColor = if (!isError) {
        colorResource(id = R.color.content_xx_high)
    } else {
        colorResource(id = R.color.content_danger)
    }

    val outlineColor = if (!isError) {
        colorResource(id = R.color.content_xx_high)
    } else {
        colorResource(id = R.color.content_danger)
    }

    val statusText = if (!isError) "Enabled state" else "Error state"

    Text(
        text = statusText,
        modifier = modifier,
        style = Typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold)
    )

    Row {

        Text(
            text = "Input",
            modifier = modifier,
            style = Typography.labelSmall.copy(
                fontWeight = FontWeight.ExtraBold,
                color = labelColor)
        )

        Text(
            text = "Optional",
            modifier = modifier.padding(start = Spacing.l.dp),
            style = Typography.labelSmall
        )
    }

    OutlinedTextField(value = password,
        onValueChange = {password = it},
        placeholder = {
            Text(text = "Placeholder",
            style = Typography.labelSmall.copy(
                color = colorResource(id = R.color.content_medium)
            )) },
        shape = RoundedCornerShape(Radius.input.dp),
        modifier = Modifier.fillMaxWidth().padding(top = Spacing.s.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = outlineColor,
            unfocusedBorderColor = outlineColor,
            errorBorderColor = outlineColor
        ),
        isError = !isError)

        if (isError){
            Column(modifier = Modifier.padding(top = Spacing.s.dp)) {
                if (!validation.lengthValid)
                    PasswordRuleText("At least 8 characters")

                if (!validation.hasUpperCase)
                    PasswordRuleText("One uppercase letter")

                if (!validation.hasNumber)
                    PasswordRuleText("One number")

                if (!validation.hasSpecialChar)
                    PasswordRuleText("One special character")
            }
        }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CastingTheme {
        InputView()
    }
}