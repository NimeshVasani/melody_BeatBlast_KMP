package com.nimesh.vasani.melodybeatblastkmp.player

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SeekBar(
    progress: Float,
    onProgressChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    backgroundColor: Color = Color.LightGray,
    thumbColor: Color = Color.White,
    thumbSize: Dp = 16.dp,
    trackHeight: Dp = 4.dp
) {
    var isDragging by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(trackHeight / 2))
            .height(thumbSize)
            .padding(vertical = (thumbSize - trackHeight) / 2)
            .padding(horizontal = thumbSize / 2)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { isDragging = true },
                    onDragEnd = { isDragging = false },
                    onDrag = { change, dragAmount ->
                        val newValue = (progress + (dragAmount.x / (size.width - thumbSize.toPx()))).coerceIn(0f, 1f)
                        onProgressChanged(newValue)
                        change.consumePositionChange()
                    }
                )
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(trackHeight)
                .background(color = color, shape = RoundedCornerShape(trackHeight / 2))
        )

        Box(
            modifier = Modifier
                .offset(x = with(LocalDensity.current) { (progress * (20 - thumbSize.toPx())).toDp() })
                .size(thumbSize)
                .background(color = thumbColor, shape = CircleShape)
                .align(Alignment.CenterStart)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { isDragging = true },
                        onDragEnd = { isDragging = false },
                        onDrag = { change, dragAmount ->
                            val newValue = (progress + (dragAmount.x / (size.width - thumbSize.toPx()))).coerceIn(0f, 1f)
                            onProgressChanged(newValue)
                            change.consumePositionChange()
                        }
                    )
                }
        )
    }
}
