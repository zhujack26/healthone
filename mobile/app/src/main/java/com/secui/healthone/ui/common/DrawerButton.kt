package com.secui.healthone.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.secui.healthone.R
import com.secui.healthone.constant.AppColors

@Composable
fun DrawerButton(
    text: String,
    icon: Int? = null,
    iconColor: Color? = null,
    onClick: () -> Unit
) {
    val textColor = AppColors.black
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = iconColor ?: Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            if (text == "박싸피") {
                Column(horizontalAlignment = Alignment.Start) {
                    Box(modifier = Modifier.padding(5.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.onboarding_first),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(4.dp)
                                .size(48.dp)
                                .background(AppColors.white)
                                .clip(shape = CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text,
                        fontSize = 20.sp,
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                    )
                }
            } else {
                Text(
                    text,
                    fontSize = 20.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
