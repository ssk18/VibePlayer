package com.ssk.vibeplayer.core.presentation.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.ssk.vibepalyer.core.presentation.designsystem.R


val ArrowLeft: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.ic_arrow_left)

val ArrowUp: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.ic_arrow_up)

val ArrowDown: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.ic_chevron_down)

val Pause: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.ic_pause)

val Play: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.ic_play)

val SkipNext: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ic_skip_next)

val SkipPrevious: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.ic_skip_previous)
