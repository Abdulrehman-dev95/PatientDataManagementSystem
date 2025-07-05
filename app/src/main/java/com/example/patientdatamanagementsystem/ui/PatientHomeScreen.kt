package com.example.patientdatamanagementsystem.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.patientdatamanagementsystem.R
import com.example.patientdatamanagementsystem.ui.theme.CardBackgroundColor
import com.example.patientdatamanagementsystem.ui.theme.LightBlueBackground
import com.example.patientdatamanagementsystem.ui.theme.PatientDataManagementSystemTheme
import com.example.patientdatamanagementsystem.ui.theme.TextPrimaryColor
import com.example.patientdatamanagementsystem.ui.theme.TextSecondaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthDashboardScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface // Or a specific light color
                )
            )
        },
        bottomBar = {
            //AppBottomNavigation()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            WelcomeCard(name = "John Doe")


            ActionGrid()



            UploadFileCard()
            RecentActivitySection()


            Spacer(modifier = Modifier.height(16.dp)) // Space at the bottom before nav bar overlap

        }
    }
}

@Composable
fun WelcomeCard(name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = LightBlueBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp, vertical = 36.dp), // Increased vertical padding
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Welcome,",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            // Replace R.drawable.user_avatar with your actual avatar image
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.LightGray, CircleShape),
            ) {

            }
        }
    }
}

@Composable
fun ActionGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ActionCard(
                icon = R.drawable.ic_health_records, // Replace with your specific icon
                text = "Health Records",
                color = Color(0xFF4CAF50),
                modifier = Modifier.weight(1f)
            )
            ActionCard(
                icon = R.drawable.ic_appointments,
                text = "Appointments",
                color = Color(0xFF2196F3),
                modifier = Modifier.weight(1f)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ActionCard(
                icon = R.drawable.ic_doctor, // Replace with your specific icon for doctors
                text = "Doctors",
                color = Color(0xFF9C27B0),
                modifier = Modifier.weight(1f)
            )
            ActionCard(
                // It's best to use an SVG or PNG for the blockchain icon
                icon = R.drawable.ic_blockchain, // Placeholder, replace with your blockchain icon
                text = "Blockchain",
                color = Color(0xFFFF9800),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ActionCard(
    @DrawableRes icon: Int,
    color: Color,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.size(width = 200.dp, height = 120.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = text,
                tint = color, // Use the defined icon color
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = TextPrimaryColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun RecentActivitySection() {
    Column {
        Text(
            text = "Recent Activity",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp, top = 8.dp)
        )
        ActivityItem(title = "Diagnosis: Hypertension", date = "Feb 15, 2024")
        Spacer(modifier = Modifier.height(10.dp))
        ActivityItem(title = "Appointment with Dr. Smith", date = "Feb 20, 2024")
        // Add more ActivityItems or use a LazyRow if there are many
    }
}

@Composable
fun ActivityItem(title: String, date: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = TextPrimaryColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondaryColor
            )
        }
    }
}

@Composable
fun UploadFileCard(modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Text(
            text = "Upload your file",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, bottom = 8.dp)
        )


    }


}


//@Composable
//fun AppBottomNavigation() {
//    NavigationBar(
//        containerColor = LightBlueBackground, // Match the welcome card or a slightly different shade
//        contentColor = MaterialTheme.colorScheme.onPrimaryContainer // Adjust for good contrast
//    ) {
//        // You'd typically have a selected state and handle navigation
//        NavigationBarItem(
//            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
//            selected = true, // Current screen
//            onClick = { /* Navigate to Home */ },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = MaterialTheme.colorScheme.primary,
//                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
//                indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f) // Subtle indicator
//            )
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.AutoMirrored.Outlined.List, contentDescription = "Records") }, // Placeholder
//            selected = false,
//            onClick = { /* Navigate to Records */ },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = MaterialTheme.colorScheme.primary,
//                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Outlined.AccountCircle, contentDescription = "Profile") },
//            selected = false,
//            onClick = { /* Navigate to Profile */ },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = MaterialTheme.colorScheme.primary,
//                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Outlined.Settings, contentDescription = "Settings") },
//            selected = false,
//            onClick = { /* Navigate to Settings */ },
//            colors = NavigationBarItemDefaults.colors(
//                selectedIconColor = MaterialTheme.colorScheme.primary,
//                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        )
//    }
//}


@Preview(showBackground = true)
@Composable
fun HealthDashboardScreenPreview() {
    // Wrap with your app's theme for accurate preview
    // YourAppTheme { // If you have a custom theme
    PatientDataManagementSystemTheme { // Using default MaterialTheme for this example
        HealthDashboardScreen()
    }
    // }
}